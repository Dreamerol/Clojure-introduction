;;calculating pow
;;po-optimalno ot strana na pamet
;;reduce - dopylnitelna pamet
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

;;calculating exp = 1 + x/1! + x^2/2!.... to the nth number
(defn calculate-exp [x]
  (loop [acc 0 times 10]
    (if (zero? times) acc
        ;macro!!!!!!
        (recur (+ (/ (pow x times) (calculate-factorial times)) acc) (- times 1)))))

(defn calculate-exp-with-macro [x]
  (loop [acc 0 times 10]
    (if (zero? times) acc
        ;macro!!!!!!
        (recur (+ (->
                   (pow x times)
                   (/ (calculate-factorial times)))
                  acc)
               (- times 1)))))

(calculate-exp-with-macro 11)
