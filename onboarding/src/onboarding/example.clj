(ns onboarding.example)
(+ 1 4 5 6 2)
(* (+ 1 2 3 4) (+ 9 8))
(+ 1 (* 2 3) 4)
(+ 1 2 3 4)
"jkss"

[12 23 4]
(str "sjhs" "jshhs")

(if true
  "kjeee"
  "ysyys")

(if false
  "loo"
  "jskjjs")

(if true
  "dgd")

(if false
  "jhhhd")

(if true
  (do (println "shhss") 
         "hjshjs" )
  (do (println "failure")
      "lol")
)

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))
;;   do (println "success!")
;;   do (println "failure!"))

(when true
  (println "jhshjhs")"kjskjsk"
)

(= 1 2)

(def nums 
  [1 2 3 3 4])
nums

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]
  )

failed-protagonist-names

(def severity :mild)
(def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
(println error-message)
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED!"))
  (def error-message (str error-message "DOOOOOOOMED!")))
(println error-message)

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(error-message :mild)

(hash-map :a 1 :b 2)

(hash-map "aja" 1 "ajha" 2)

(get {:a 1 :b 23} :a)

(get {:a 0 :b 1} :c)


(get {:a 0 :b 1} :c "unicorns?")

(get-in {:a 1 :b {:c "kjak"}}, [:b :c])

({:name "The Human Coffeepot"} :name)
(:a {:a 1 :b 2 :c 3})

(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")

(get [1 2 3 4 {"ahjh" 24}] 0)

(vector 23 34 56 "yy")

(conj [1 2 3] 6)

(list 1 2 3)

(nth '(1 2 3 4) 0)

((and (= 1 1) +) 1 2 3)
; => 6

((first [+ 0]) 1 2 3)
(inc 2)

(map inc [0 1 2])

(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")

;; & parameter_name - means we take parameters as a list

(defn my-second
  [[_ second-thing]]   ; the underscore means “I don’t care about the first one”
  second-thing)

;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:x 10 :y 20});;return nil if key x not in the list

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

((fn [x] (* x 3)) 8)
; => 24
(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)
; => 36

;;!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)

(let [x 3]
  x)
; => 3



(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)
; => ("Pongo" "Perdita")

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))
(recursive-printer)
; => Iteration 0
; => Iteration 1
; => Iteration 2
; => Iteration 3
; => Iteration 4
; => Goodbye!

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})
(matching-part {:name "left-eye" :size 1})
; => {:name "right-eye" :size 1}]

(matching-part {:name "head" :size 3})
; => {:name "head" :size 3}]

(reduce + 15 [1 2 3 4])

(defn kaval [{:keys [a b c]}]
  (prn a b c)
  )

(kaval {:a 1 :b 2 :cfdrtddt 67})


;;------------------------------------------------------------------------------------------------------------------------------
;;zad 23
(defn reverseOrder
  [els]
  (loop [remaining els
         result []]
    (if (empty? remaining) result
        (recur (butlast remaining) (conj result (last remaining))))))
(reverseOrder [1 2 3 4])

;;zad 24
(defn sum 
  [els] 
  (loop [remaining els result 0] 
    (if (empty? remaining) result
        (recur (rest remaining) (+ result (first remaining))))))

(sum [1 2 3 4 45 0])

;;zad 
;;Printing odd numbers
(defn isEven
  [x]
  (zero? (mod x 2))
  )

(def isOdd (complement isEven))
(isOdd 67)
(isOdd 89)

(defn oddNums
  [els]
  (loop [remaining els result []]
  (if (empty? remaining)
    result
      (recur (rest remaining)
             (if (isOdd (first remaining))
                                 (conj result (first remaining))
                                 result)
      ))
        ))
  
(oddNums [1 2 3 4 5 2 3 6 6 7])


(defn isEven
        [x]
        (zero? (mod x 2)))
      
      ;;(def isOdd (complement isEven))
      
      (defn oddNums
        [els]
        (loop [remaining els result ()]
          (if (empty? remaining)
            result
            (recur (butlast remaining)
                   (if (isEven (last remaining))
                     result 
                     (conj result (last remaining)))))
          ))
(oddNums [1 2 3 4 5])

(defn oddNums
  [seq]
  (filter odd? seq))
      
;;zad 26
;;Fibonacci Sequence
(defn firstFibonacciNumbers
  [x] 
  (loop [t1 1 t2 1 result []]
    (if (== (count result) x) 
      result
   (recur t2 (+ t1 t2) (conj result t1)))
    ))

(firstFibonacciNumbers 7)
  
;;zad 27
;;Palindrome Detector
(defn PalindromeDetector
  [seq]
  (loop [remaining seq]
    (if (or (== (count remaining) 1) (== (count remaining) 0)) true
    (if (not= (first remaining) (last remaining))
      false
      (recur (butlast (rest remaining)))
      ))))

(+ 1 2)
(PalindromeDetector "ada")
(PalindromeDetector [1 2 2 1])

(defn flattenSeq
  [seq]
  (loop [remaining seq result []]
    (if (empty? remaining)`
      result
      (recur (rest remaining) (into result (first remaining))))
    )
  )

(flattenSeq [[1 2 3] [ 2 3 4 5]])
(flattenSeq '((1 2 3) 7 [ 1 2]))