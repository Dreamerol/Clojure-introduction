;;zad 5
;;easy task ->  sum the first and the last els from the collection [1 2 3 4] ->[5 5]
(defn sum-of-first-last-els [els]
  (loop [acc [] remaining els]
    (if (= (count remaining) 1)
      (conj acc (first remaining))
      (if (empty? remaining)
        acc
        (recur (conj acc (+ (first remaining) (last remaining))) (rest (butlast remaining)))))))

(sum-of-first-last-els [1 2 3 4 6 5 87])

;;zad 6
;; Y B
;; G R

;;RRRG
;;RGBY
;;function checking on each iteration whether count of color1 - color2 > 1
;;helper func1 to check for two colors if abs(col1 - col2) > 1
;;FINAL IMPROVED WORKING VERSION!!!
(defn validate-for-two-colors [map color1 color2]
  (< (abs (- (get map color1) (get map color2))) 2))

(validate-for-two-colors {"R" 5 "G" 2 "Y" 0 "B" 0} "R" "G")
(defn validate-all-colors [mp col1 col2 col3 col4]
  (and (validate-for-two-colors mp col1 col2) (validate-for-two-colors mp col3 col4)))



(validate-all-colors {"R" 0 "G" 0 "Y" 0 "B" 0} "R" "G" "Y" "B")

(defn validate-string-colors [els]
  (loop [acc count-colors remaining els]
    (let [key (str (first remaining))]
      (if (empty? remaining)
        (cond
          (not (= (get acc "R") (get acc "G"))) false
          (not (= (get acc "Y") (get acc "B"))) false
          :else true)

        (cond
          (validate-all-colors acc "R" "G" "Y" "B") (recur (assoc acc key (+ (get acc key) 1)) (rest remaining))
          :else false)))))

(validate-string-colors "RRRRRGBY")
;;final working version
(defn find-count-colors [els]
  (loop [acc count-colors remaining els]
    (let [key (str (first remaining))]
      (if (empty? remaining)
        ;;in the end we check if the red ones = count of green ones
        ;;and if count yellow = count blue ones 
        (if (= (get acc "R") (get acc "G"))
          ;;  (if
          (= (get acc "Y") (get acc "B"))
          ;; true false)
          false)
        (if (validate-all-colors acc "R" "G" "Y" "B")
          ;;if the func returns true -> that means that it is a valid prefix 
          ;;so we perceed with the iterations
          (recur (assoc acc key (+ (get acc key) 1)) (rest remaining))
          false)))))
;;cond func
;RRRG
(find-count-colors "RRRRRGBY")

;;Pentagonal numbers
;;https://www.hackerrank.com/challenges/pentagonal-numbers/problem?isFullScreen=true
;;find the count of the dots in a pentagon
;;formula P(n) = n *(3n-1)/2

(defn calculate-formula [n]
  (->
   (* 3 n)
   (- 1)
   (* n)
   (/ 2)
   ;;(* n (- (* 3 n) 1))
   ))

(defn print-N-pentagon-numbers [n]
  (doseq [x (range n)]
    (prn (calculate-formula x))))

(print-N-pentagon-numbers 5)
