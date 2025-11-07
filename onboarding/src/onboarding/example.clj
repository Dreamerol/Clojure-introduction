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
      "hjshjs")
  (do (println "failure")
      "lol"))

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))
;;   do (println "success!")
;;   do (println "failure!"))

(when true
  (println "jhshjhs") "kjskjsk")

(= 1 2)

(def nums
  [1 2 3 3 4])
nums

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

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

(reduce + 15 [1 2 3 4])

(defn kaval [{:keys [a b c]}]
  (prn a b c))

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

;;zad 23 better version
(defn revone
  [els]
  (reduce conj '() els))



(defn revThree
  [els]
  (reduce (fn [res x]
            (conj res x)) els '()))


(defn conj-two-lists [res x]
  (conj res x))

(defn rev-four [els]
  (reduce conj-two-lists '() els))

(rev-four [1 2 3 4 5])



(revone [1 2 3])

(defn reverseOrderBetterVersion
  [els]
  (loop [remaining els result '()]
    (if (empty? remaining) result
        (recur (rest remaining) (conj result (first remaining))))))

(defn reverseEls
  [els]
  (reverse els))
(reverse [2 3 4 5])
(reverseOrderBetterVersion [2  3 4])
;;zad 24
(defn sum
  [els]
  (loop [remaining els result 0]
    (if (empty? remaining) result
        (recur (rest remaining) (+ result (first remaining))))))

(sum [1 2 3 4 45 0])

;;zad 
;;Printing odd numbers

(defn oddNums
  [els]
  (filter #(odd? %) els))

(defn evenNums
  [els]
  (filter #(even? %) els))

(evenNums [1 2 3 4 5])

(defn isEven
  [x]
  (zero? (mod x 2)))

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
               result)))))

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
               (conj result (last remaining)))))))
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
      (recur t2 (+ t1 t2) (conj result t1)))))

(firstFibonacciNumbers 7)

(defn Fibonacci
  [n];;we pass as a parameter to which n number wewant to iterate
  (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1]))))
;; we teka the first n numbers
;;we create an anonyous fn wich accepts as a parameter vetcor of [a b] -> and returns the next [b (a+b)] ->which 
;;is our next fibonacci pair
;;map first to take the first el cause our format is [a b]
;;and [0 1] are the base values for our fn when starting iterating
(Fibonacci 6)

;;zad 27
;;Palindrome Detector
(defn palindromeDecBetter
  [text]
  (= text (reverse text)))

(defn palindromeDecBetter
  [text]
  (= text (revone text)))
;;we check whether the text is equal to it's reverse
;if text = reverse text -> is a palindrome
(palindromeDecBetter [1 2 3 2 1])

(defn PalindromeDetector
  [seq]
  (loop [remaining seq]
    (if (or (== (count remaining) 1) (== (count remaining) 0)) true
        (if (not= (first remaining) (last remaining))
          false
          (recur (butlast (rest remaining)))))))

(+ 1 2)
(PalindromeDetector "ada")
(PalindromeDetector [1 2 2 1])

;; (defn flattenSeq
;;   [seq]
;;   (loop [remaining seq result []]
;;     (if (empty? remaining)`
;;       result
;;       (recur (rest remaining) (into result (first remaining))))
;;     )
;;   )


(defn flattenSeq
  [seq]
  (loop [remaining seq result []]
    (if (empty? remaining)
      result
      (recur (rest remaining) (into result  (if (number? (first remaining)) [(first remaining)]
                                                (first remaining)))))))


;;zad 28 solution from archive
(defn f [c]
  (if (empty? c)
    '()
    (if (coll? (first c))
      (concat (f (first c)) (f (rest c)))
      (cons (first c) (f (rest c))))))

(f [[1 2 3] [2 3 4 5]])
(flattenSeq '((1 2 3) 7 [1 2]))


;;zad 29
;;better version
(defn getCapitalsBetter
  [text]
  (apply str (filter #(Character/isUpperCase %) text)))

;;get Capitals with string join
(defn getCapitals [text]
  (clojure.string/join "" (filter #(Character/isUpperCase %) text)))

(getCapitals "ahgDFDAGFF")

;;we apply str only on the filtered chars that are upperCase

(getCapitalsBetter "gayfafFFAFFGFGfagfc")
(defn getCapital
  [text];;podavane na parametyr za funkciqta - string
  (loop [remaining text newText ""] ;;vyrtim cikyl v kojto podavame ostanalata chast ot teksta, a newText syhranqva zapazenite golemi bukvi
    ;;kato za bazova stojnost mu podavame prazen string
    (if (empty? remaining);;proverqvame dali ne e ostanal prazen string, tyj kato vsqko vyrtene vzimame pyrviq simvol
      newText ;;ako e prazen vryshtame noviq texts golemite bukvi
      (let [ch (first remaining)];;destructing - vzimame ch = pyrviq simvol
        (recur (rest remaining);;v cikyla podavame ostatyka ot dumata
               (if (Character/isUpperCase ch) ;; ako vzetiq simvol e uppercase togava -> add kym newText
                 (str newText ch);;konkatenirame simvola s newText
                 newText);;ako statementa v ifa e falsely togava podavame newText, a inache ako ch e Uppercase podavame (ch+newText)
               )))))

(getCapital "fafaDFAFAF")

;;zad 30
;;Function to remove sequential duplicates
(defn removeDuplicates
  [seq]
  (loop [remaining seq newSeq []];;подаваме старата колекция и нова - има базова стойнот []
    (if (empty? remaining)
      newSeq
      (recur (rest remaining) (if (= (last newSeq) (first remaining))
                                ;;ако първият елемент от remaining сравнява с последния елемент на новата ни
                                ;;колекция и ако са равни просто си връщаме колекцията без да добавяме новия елемент, тъй като ще стане поредица 
                                ;;от еднакви числа
                                newSeq
                                (conj newSeq (first remaining))))));;ако са различни обаче пример - [1 2 3]-new Seq [4 4 5]-remaining
  ;;[можем да го добавим без да нарушаваме уникалността
  )

(removeDuplicates [1 1 2 2 2 2 3 3])

(defn removeDupl
  [els]
  (vec (into #{} els)))


;;we add the elements in a set with the into func and then we transfrom it into a vector
(removeDupl [1 2 3 33 4 3 3 5])

;;zad 31
;; (defn diffCollections 
;;   [els]
;;   (loop [remaining els res []]
;;     (if (empty? remaining)
;;       res
;;       (do (if (= (first remaining) (last (last res)))
;;             (conj (last res) (first remaining))
;;             (conj res '((first remaining))))
;;           (recur (rest remaining) res)
;;       ))))


(defn diffCollections
  [els];;подаваме параметър seq
  (loop [remaining els res []]
    (if (empty? remaining)
      res;;ако сме обходили remaining връщаме res
      (let [x (first remaining);;ако не - destructing като x <- първия елемент от поредицата
            newRes (if (empty? res) ;;и newRes <- ако res е empty първият елемент ще бъде [[x]]
                     [[x]]
                     (if (= x (last (last res)));;ако следващият подаден елемент съвпада с последния елемент от последното подвекторче 
                       (conj (vec (butlast res));;vec (butlast res) -> преобразува във вектор всички без последния вектор
                             (conj (last res) x)) ;;и фо свързва с (към последния вектор last res добавяме елемента х) - (1, 1) 1 -> (1, 1, 1)
                       (conj res [x])))];;ако х е различен - направо към res [x] - подвекторчето (2, 2) 1->(2, 2) (1) 
        (recur (rest remaining) newRes)))));;подаваме newRes, който чрез destructing е присвоил преобразуваните вектори
;;подаваме newRes , а не res, тъй като структурите в clojure са immutable->следователно не можем да ги променяме в хода на процеса
;;трябва да създадем нова структура, върху която да извършим промените и после тази нова преобразувана структура да я подадем като параметър
;;извиканата рекурентна функция

(diffCollections [1 2 2 3 3 3 4 5])

;;better sum function
(defn SumNums
  [els]
  (reduce + els))

(SumNums [2 3 4 4])

