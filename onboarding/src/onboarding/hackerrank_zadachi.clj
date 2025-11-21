;;zadachi - 20.11.2025
;;1
;Print hello n times

(defn hello_word_n_times [n]
  (doseq [x (range n)]
    (prn "Hello World")))

;;2
;Repeat each num from a collection s times
(defn repeat-nums [els s]
  (reduce (fn [acc item]
            (doseq  [x (range s)]
              (prn item)))  els))
(repeat-nums [1 2 3 4] 5)

;3 - sumof even els
(defn sum-even-els
  [els]
  (reduce (fn [acc item]
            (if (even? item)
              (+ acc item)
              acc)) 0 els))

(sum-even-els [1 2 3 4])

;4 - remove els from odd positions
(defn remove-els-odd-pos-nth [els]
  (take-nth 2 els))

(remove-els-odd-pos-nth [1 2 3 4 5])


(defn string-compression
  [els]
  (loop [remaining els cnt 0 result "" current (first remaining) prev ""]
    (if (empty? remaining)
      ;;result
      (do
        (if (> cnt 1)
          (format "%s%s%d" result prev cnt)
          (format "%s%s" result prev))

        ;;   (concat result (format "%s%d" prev cnt))
        ;;   (concat result (format "%s" prev))
        ;;    (str result prev cnt)
        ;;    (str result prev))
        )


      (do
        ;; (prn (first remaining))
        ;;    (prn prev current)
        (prn cnt)

        (if (= prev current)
          (recur (rest remaining) (+ cnt 1) result (first (rest remaining)) current)
          (recur (rest remaining) 1 (if (> cnt 1)
                                      ;;(str result prev cnt) 
                                      ;; (str result prev)
                                      (format "%s%s%d" result prev cnt)
                                      (format "%s%s" result prev))
                 (first (rest remaining)) current))))))

(def result "kdjjkd")
(format result "%s" "dhhd")

(format (format "%s%s" "dhghgd" "kjshjs"))
;;see documentation
(string-compression "aaaaaaaaabbbbcccccdsgsgddddd")
(format "%s %s %s" "kaval" "maval" "daval")
(format "%%" result (...))

(str "shs" 23)

(string-compression "aagfaaffa")
;; (defn remove-els-odd-pos-recur[els]
;;   (loop []))

;; (defn remove-els-odd-positions
;;   [els]
;; ;;   (let [cnt 0]
;;   (let [cnt 0]
;;     (reduce (fn [acc item]
;;               (if (even? cnt)
;;                 (conj acc item)
;;                 acc) 
;;               (+ cnt 1)
;;              ;; (swap! cnt inc)
;;               ;;  (prn cnt)
;;               )[] els)))

;; (remove-els-odd-positions [1 2 3 4 5 6])

;5 - abs of all els
(defn find-abs [els]
  (map abs els))

(find-abs [-9 8 7 -9])

;;zadachi 21.11.2025

;; (defn pow[x n]
;;   (reduce (fn [acc item]
;;             (doseq [x (range item)]
;;               (* acc x))
;;             ))1 n)

;; (pow 2 3)
;;   (doseq [x (range n)]
;;    (* x x)
;;     )
;;   )


(prn 2)
;;calculating pow
(defn pow [num n]
  (loop [acc 1 times n]
    (if (zero? times) acc
        (recur (* acc num) (- times 1)))))

(pow 2 3)
(prn 9)


(pow 2 3)

;;calculating factorial
(defn calculate-factorial [n]
  (loop [acc 1 times n]
    (if (zero? times) acc
        (recur (* acc times) (- times 1)))))

(calculate-factorial 5)

;;calculating exp = 1 + x/1! + x/2!.... to the nth number
(defn calculate-exp [x]
  (loop [acc 0 times 10]
    (if (zero? times) acc
        (recur (+ (/ (pow x times) (calculate-factorial times)) acc) (- times 1)))))

(calculate-exp 11)

;https://www.hackerrank.com/challenges/string-o-permute/problem?isFullScreen=true-link kym zadacha
;;swapping characters which are next to each other - abcd -> badc
(defn swap-nbs [els]
  (prn (take-nth 2 els))
  (let [second-els (take-nth 2 (rest els))]
    (loop [acc "" col1 second-els col2 els]
      (if (empty? col1)
        (str acc (first col2))
        (recur (str acc (first col1) (first col2)) (rest col1) (rest (rest col2)))))))

(first [])
(swap-nbs "abcdfge")

;Pascal triangle
;;helper function to calculate next row
(defn compute-pascal-triangle-next-row [prev_row]
  (loop [acc [] prev_num 0 nums prev_row]
    (if (empty? nums) (conj acc prev_num)
        (recur (conj acc (+ (first nums) prev_num)) (first nums) (rest nums)))))

;;function with macro
(defn compute-pascal-triangle-next-row-with-macro [prev_row]
  (loop [acc [] prev_num 0 nums prev_row]
    (if (empty? nums) (conj acc prev_num)
        (recur (->> (first nums)
                    (+ prev_num)
                    (conj acc)) (first nums) (rest nums)))))

(compute-pascal-triangle-next-row [1])
(compute-pascal-triangle-next-row [1 2 1])
(compute-pascal-triangle-next-row-with-macro [1 3 3 1])

;;calculating the all the nth pascal triangles 
(defn compute-pascal-traingle [n]
  (loop [acc [[1]] times n prev_row [1 2 1]]
    (if (= 1 times) acc
        (let [current_row (compute-pascal-triangle-next-row prev_row)]
          (recur (conj acc prev_row) (- times 1) current_row)))))

(compute-pascal-traingle 8)
(defn lol [func x]
  (func x))
(lol inc 8)

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
          (recur (+ acc (find-length prev curr)) curr (first (rest col)) (rest col))
          )

    )
  )
)

(calculate-perimeter-polygon [[1 0] [0 1] [0 0]])

