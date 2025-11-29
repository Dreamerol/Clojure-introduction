
;;find perimeter of polygon
(defn helper-len-func [x y]
  (pow (- x y) 2))

(helper-len-func 2 5)
(defn helper-len-func-with-pred [fn p1 p2]
  (helper-len-func (fn p1) (fn p2)))


(defn find-length [point1 point2]
  (Math/sqrt (+ (helper-len-func-with-pred first point1 point2) (helper-len-func-with-pred second point1 point2))))
(find-length [1 2] [3 4])

(defn calculate-perimeter-polygon [els]
  (let [helper-polygon (conj els (first els))]
    (loop [acc 0 prev (first helper-polygon) curr (second helper-polygon) col (rest helper-polygon)]
      (if (empty? col) acc
          (recur (+ acc (find-length prev curr)) curr (first (rest col)) (rest col))))))

(calculate-perimeter-polygon [[1 0] [0 1] [0 0]])

;;24.11.2025 - tasks--------------------------------------------------------------------------------------------------------
;;zad 1
;;determinnat B = | x1 x2 | = x1.y2 - x2.y1
;;                | y1 y2 |
;;Area polygon A = 1/2 * | x1 x2 | + | x2 x3 |
;;                       | y1 y2 |   | y2 y3 |.......
;
(defn helper-determinants [point1 point2]
  ;;let x1,x2....
  (- (->
      (first point1)
      (* (second point2)))
     (->
      (first point2)
      (* (second point1)))))

(defn helper-determinants-better [point1 point2]
  ;;let x1,x2...
  (let [x1 (first point1) x2 (first point2) y1 (second point1) y2 (second point2)]
    (- (* x1 y2) (* x2 y1))))

(helper-determinants-better [0 1] [1 2])


(helper-determinants [1 1] [2 2])
(defn area-of-polygon
  [els]
  (let [helper-polygon (conj els (first els))]
    (loop [acc 0 prev (first helper-polygon) curr (second helper-polygon) col (drop 2 helper-polygon)]
      (if (empty? col)
        (* 1/2 acc)
        ;;nth -> second
        (recur (+ acc (helper-determinants prev curr)) curr (first col) (rest col))))))

;;calculate the area of the polygon
(area-of-polygon [[1 2] [2 3] [0 0]])
