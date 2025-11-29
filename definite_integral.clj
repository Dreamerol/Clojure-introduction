
;;zad - definite integral
(defn power [x n]
  (loop [acc 1 times n]
    (if (= times 0) acc
        (recur (* acc x) (- times 1)))))

(power 2 4)

(defn calculating-part-rhiman-sum [coeff powi x1 x2]
  (let [pow (+ powi 1) final-coeff (/ coeff pow)]
    (->
     (power x2 pow)
     (- (power x1 pow))
     (* final-coeff)))
  )

(calculating-part-rhiman-sum 1 1 3 4)

(defn calculate-area-under-curve [coeffs pows x1 x2]
  (loop [acc 0 curr-coef (first coeffs) curr-pow (first pows) rem-coeffs coeffs rem-pows pows]
    (if (empty? rem-coeffs)
      acc
      (recur (+ acc (calculating-part-rhiman-sum curr-coef curr-pow x1 x2))
             (first (rest rem-coeffs)) 
             (first (rest rem-pows)) 
             (rest rem-coeffs)
             (rest rem-pows))))
 
  )


(defn calculate-area-under-curve-high-order-functions[coeffs pows x1 x2]
  (map (fn [coef powi] 
         (calculating-part-rhiman-sum coef powi x1 x2)) 
       coeffs pows))



(calculate-area-under-curve [1 1 1] [1 1 1] 2 3)
