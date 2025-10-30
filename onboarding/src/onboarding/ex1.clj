;; zad 1
(str "jhshs" "hsss")
(hash-map :a 726 :b 8989)
(list 1 2 3 4 5)
(hash-set 1 1 1 1 1 2)

;;zad 2
(defn addNumber
  [x]
  (+ x 100)
  )
(addNumber 3)

;;zad 3
(defn dec-maker
  [x]
  (fn [y](- x y)))

;;  (defn decrement
;;    [x]
;;    (fn [1] (- 1 x)))

;; (dec-maker 20)
;; (def dec9 (dec-maker 9))
;; (dec9 10)


;; (defn dec-maker
;;   [dec-by]
;;   #(- % dec-by))

(defn returnOne
  [x] 1)
(returnOne 56)

(defn decOne
  [x] (- x 1))
(decOne 34)

(defn decTwoNums
  [x y] (- x y))

(def dec9 (decTwoNums 9 8))
(dec9)

(defn decTwoNums
  [x y] 
  1
  (fn [](- x y)))

(decTwoNums 9 8)


(defn returnOne
  [x] 1)

(defn decTwoNumsPrim
  [x y]
  (fn [] (- x (returnOne 7))))

(defn dec-maker []
  (fn [x] (- x 1)))

(def dec9 dec-maker)
(dec9 9)


(fn
  [[head & tail]]
  (if (empty? tail) head
      (recur tail)))


;;making a map-zad4
(defn mapset [f coll]
  (set (map f coll)))

(mapset inc [1 1 2 2 3])

(defn SecondLast
  [els]
  (let [cnt (count els)]
    (nth els (- cnt 2))))

