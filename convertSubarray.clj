;;zad - reverse subarray
;;with this function we only revert the elements between i and j
 (defn reverse-subarray [i j els]
   (loop [remaining els acc '() cnt 0]
     (if (> cnt (- j i))
       acc
       (recur (rest remaining) (conj acc (first remaining)) (+ cnt 1)))))
   (defn reverse-subarray-with-macro[i j els]
    (cond
      (> i j) els
      :else (-> (->>
                 (take i els)
                 (into []))
                
                (into (reverse-subarray i j (drop i els))) 
               (into (drop (+ j 1) els))
                )
      ))
     
     (reverse-subarray-with-macro 2 4 [1 2 3 4 5 6])

;;zad super digit
;;task - superdigit 1234 -> 1 + 2 + 3 + 4 -> 10 -> 1 + 0 -> 1
   (defn nums-to-collection-converter[number]
     (map #(Character/getNumericValue %) (str number)))
    

     (defn calculate-super-digit [number]
       (let [curr (reduce + (nums-to-collection-converter number))]
         (if (< curr 10)
           curr
           ;;(prn curr)
           (calculate-super-digit curr))
         )
       )
      
   (calculate-super-digit 256535)

   ;;find greatest common divisor
   (defn gcd [a b]
     (if (= b 0)
       a
       (gcd b (mod a b)))
   )
     
     (gcd 6 2)

;;find least common delitel
     (defn gcm[a b]
       (->>
        (gcd a b)
        (/ a)
        (* b)))
   
   (gcm 6 4)

   (defn find-lcm-of-coll [els]
     (reduce gcm els))

  ;;check for substring
;;function to check whether the sliding window of chars is the same as the substring
(defn sliding-window-check-string
  [substring remaining]
  (loop [res remaining substr substring]
    
    ;; (prn (first remaining))
    (let [subch (first substr) strch (first res)]
      (cond
        
        (empty? substr) true 
        (not= subch strch)  false
        :else (recur (rest res) (rest substr))
        )) )
)
    (sliding-window-check-string "lop" "lopo")

;;the main function for checking for substring
(defn is-substring [string substring]
     (if (< (count string) (count substring))
       false
         (loop [remaining string]
           (cond
             (empty? remaining) false
             (sliding-window-check-string substring remaining) true
             :else (recur (rest remaining))
             )
         )
       
         )
)
    (is-substring "computer" "dsiusiusgysygs")

   (find-lcm-of-coll [1 2 3 4 58])
