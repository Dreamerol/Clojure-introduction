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

;;zadachi 21.11.2025 -----------------------------------------------------------------------------------------------------------------------------------------------------------------

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

;https://www.hackerrank.com/challenges/string-o-permute/problem?isFullScreen=true-link kym zadacha
;;swapping characters which are next to each other - abcd -> badc
(defn swap-nbs [els]
  (prn (take-nth 2 els))
  (let [second-els (take-nth 2 (rest els))]
    (loop [acc "" col1 second-els col2 els]
      (if (empty? col1)
        (str acc (first col2))
        ;;rest rest->drop n/last , macro!
        (recur (str acc (first col1) (first col2)) (rest col1) (drop 2 col2))))))

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

;;zad 2
;;https://www.hackerrank.com/challenges/string-mingling/problem?isFullScreen=true
;;task string mingling -> combining two strings into one P = p1p2p3 and Q = q1q2q3 -> p1q1p2q2p3q3
;;strings with equal length

;;use regex!!!
;;???
(string/split "agaga" #".")
(defn string-mingling [s1 s2]
  (loop [acc "" string1 s1 string2 s2]
    (if (empty? string1)
      acc
      (recur (format "%s%s%s" acc (first string1) (first string2)) (rest string1) (rest string2)))))


(string-mingling "abscdf" "qwerty")


(partition-by (fn [arg]
                (subs arg 0 1)) "abcdf")

;;lets make the task more interesting -> what if the two strings are with different lengths
(defn string-mingling-advanced [s1 s2]
  (loop [acc "" string1 s1 string2 s2]
    (if (empty? string1)
      ;; (do 

      (loop [help-acc acc help-string2 string2]
        (if (empty? help-string2) help-acc
            (recur (format "%s%s" help-acc (first help-string2)) (rest help-string2))))

      ;;  (if (empty? string2)
      ;;       (do (loop [help-acc acc help-string1 string1]
      ;;             (if (empty? string1)
      ;;               help-acc
      ;;               (recur (format "%s%s" help-acc (first help-string1)) (rest help-string1)))))
      ;;       acc)


      (do (if (empty? string2)
            (loop [help-acc acc help-string1 string1]
              (if (empty? help-string1)
                help-acc
                (recur (format "%s%s" help-acc (first help-string1)) (rest help-string1))))

            (recur (format "%s%s%s" acc (first string1) (first string2)) (rest string1) (rest string2)))))))

;;combining strings with different length
(string-mingling-advanced "gahgahgfafa" "lko")


;;predicate function to not write repeating fragments of code
(defn predicate-helper-concat [acc string]
  (loop [help-acc acc help-string string]
    (if (empty? help-string)
      help-acc
      (recur (format "%s%s" help-acc (first help-string)) (rest help-string)))))

(predicate-helper-concat "ajgahgavg" "jgg")

;;final improved version
;;the repeated fragments of code are removed
(defn string-mingling-advanced-better-version [s1 s2]
  (loop [acc "" string1 s1 string2 s2]
    (if (empty? string1)
      ;;if string1 is empty we start iterating through string2
      (predicate-helper-concat acc string2)
      ;; if not empty -> we have two cases
      ;; 1. string2 is empty so we need to add the elements chars from string1
      ;; 2. string2 not empty -> we can add from both strings
      (do (if (empty? string2)
            (predicate-helper-concat acc string2)

            (recur (format "%s%s%s" acc (first string1) (first string2)) (rest string1) (rest string2)))))))
(string-mingling-advanced-better-version "sjshhs" "jsgsgys")

;zad 3
;;https://www.hackerrank.com/challenges/prefix-compression/problem?isFullScreen=true
;;so we have two strings -> abcdf and abcfr -> the idea of the task is to get the longest common preffix p= abc and x' = df and y'= fr
;;->we need to compress it and return p, x' and y'


(defn piano [it]
  (when (< it 10)
    (prn 1)
    (pr 2)))


(piano 9)
(defn print-compressed-strings [p x y]
  (prn (count p) p)
  (prn (count x) (reduce str x))
  (prn (count y) (reduce str y)))

(print-compressed-strings "gss" "jaa" "ka")

(defn compress-preffix [str1 str2]
  (loop [acc "" s1 str1 s2 str2]
    (let [el1 (first s1) el2 (first s2)]
      ;; (if (empty? s1)
      ;;   (print-compressed-strings acc s1 s2))
      (if (empty? s1)
        (print-compressed-strings acc s1 s2)
        (if (empty? s2)
          (print-compressed-strings acc s1 s2)
          (if (= el1 el2)
            (recur (format "%s%s" acc el1) (rest s1) (rest s2))
            (print-compressed-strings acc s1 s2)))))))

(compress-preffix "abc" "abce")
(compress-preffix "asbs" "asdvc")

;;zad 4 
;https://www.hackerrank.com/challenges/string-reductions/problem?isFullScreen=true
;we have to reduce sting if a char has already occured in the string we dont add it to the new one
; accbca -> acb


;;helper func for checking whether an element is in a collection
(defn el-in-coll [el coll]
  (loop [acc false col coll]
    (if (empty? col)
      acc
      (if (= el (first col))
        true
        (recur acc (rest col))))))

;; (el-in-coll 12 [1 2 3 4])
;; (el-in-coll 1 [1 2 3])
;; (el-in-coll 6 [1 2 3 4])


;; (defn reduce-coll[els]
;;   (loop [acc [] passed-els [] remaining els curr (first els)]
;;     (if (empty? remaining)
;;       acc
;;       (recur (if (el-in-coll curr passed-els)
;;                acc
;;                (conj acc curr)
;;                )
;;              (conj passed-els curr)
;;                    (rest remaining)
;;              (first (rest remaining))
;;                    )
;;              )
;;     )
;;   )

(defn reduce-coll-for-nums [els]
  (loop [passed-els [] remaining els]
    (if (empty? remaining)
      passed-els
      (recur (if (el-in-coll (first remaining) passed-els)

               passed-els
               (conj passed-els (first remaining)))
             (rest remaining)))))

;;(prn passed-els)
;; (do (prn passed-els)
;;             (prn (first remaining))
;;passed-els
;;) 

(reduce-coll-for-nums [1 1 2 1 2 3 3])

;;reduce function for strings -> aabsvv -> absv
(defn reduce-coll-for-strings [string]
  (reduce (fn [acc item]
            ;; (prn (str item))
            ;;(prn (clojure.string/includes? string item))
            ;; (if (clojure.string/includes? acc item)
            ;;   (format "%s%s" acc item)
            ;;   acc))
            (let [symbol (str item)]
              (if (clojure.string/includes? acc symbol)
                acc
                (str acc symbol)
                ;;(prn (str acc symbol))
                )))
          "" string))


(reduce-coll-for-strings "adadfadff")
(clojure.string/includes? "hshgsg" "h")
(el-in-coll "a" "afaffa")


(loop [acc [] passed-els [] string str]
  (if (empty? string)
    acc
    (recur (if (el-in-coll (first string) passed-els)
             (conj acc (first string))
             acc)
           (conj passed-els (first string))
           (rest string))))


(el-in-coll "a" "ahaahja")
(el-in-coll "a" ["a" "b"])
;;(el-in-coll \a ["a" "b"])

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

;;function checking on each iteration whether count of color1 - color2 > 1
;;helper func1 to check for two colors if abs(col1 - col2) > 1
(def count-colors {"R" 0 "G" 0 "B" 0 "Y" 0})

;;helper func that returns whether the count of color1 - color2 > 1
(defn helper-func-calc-colors [map color1 color2]
  (if (> (abs (- (get map color1) (get map color2))) 1)
    true
    false))

(helper-func-calc-colors {"R" 5 "G" 2 "Y" 0 "B" 0} "R" "G")

;;if two of the colors have a diff count > 1 than we return false -> invalid string
(defn help-func-counting-preffix [map col1 col2 col3 col4]
  (if (helper-func-calc-colors map col1 col2)
    false
    (if (helper-func-calc-colors map col3 col4)
      false
      true)))

(help-func-counting-preffix {"R" 0 "G" 2 "Y" 0 "B" 0} "R" "G" "Y" "B")

;; (defn find-count-colors [els]
;;   (reduce (fn [acc item]
;;             (prn (str item))
;;             (let [key (str item)]
;;               (if (help-func-counting-preffix acc "R" "G" "Y" "B")
;;                 false
;;                 true
;;              ;; (assoc acc key (+ (get acc key) 1))
;;                 )
;;              ;; (if (> (get acc "R") 1) false true)
;;               ;;(assoc acc "Red" 1)
;;               ;; (if (help-func-counting-preffix acc "Y" "B" "R" "G")
;;               ;;   false
;;               ;;   true))
;;               )
;;             )
;;           count-colors els)
  
  
;;   )

;; (help-func-counting-preffix {"R" 2 "G" 0 "B" 0 "Y" 0} "R" "G" "Y" "B")

;; (defn find-count-colors [els]
;;   (loop [acc count-colors remaining els] 
;;             (let [key (str (first remaining))]
;;               (if (empty? remaining) 
;;                 (if (= (get acc "R") (get acc "G")) 
;;                   (if (= (get acc "Y") (get acc "B"))
;;                     true
;;                     (prn acc))
;;                   false)
;;                 (if (help-func-counting-preffix acc "R" "G" "Y" "B")
;;                   acc
;;                   (recur (assoc acc key (+ (get acc key) 1)) (rest remaining))
;;                 )
;;           )
;; )
;;   )

;;final working version
(defn find-count-colors [els]
  (loop [acc count-colors remaining els]
    (let [key (str (first remaining))]
      (if (empty? remaining)
        ;;in the end we check if the red ones = count of green ones
        ;;and if count yellow = count blue ones 
        (if (= (get acc "R") (get acc "G"))
          (if (= (get acc "Y") (get acc "B"))
            true false)
          false)
        (if (help-func-counting-preffix acc "R" "G" "Y" "B")
          ;;if the func returns true -> that means that it is a valid prefix 
          ;;so we perceed with the iterations
        (recur (assoc acc key (+ (get acc key) 1)) (rest remaining))
          false)
        )
      )
    )
)

(find-count-colors "RGBY")

;;Pentagonal numbers
;;https://www.hackerrank.com/challenges/pentagonal-numbers/problem?isFullScreen=true
;;find the count of the dots in a pentagon
;;formula P(n) = n *(3n-1)/2

(defn calculate-formula[n]
  (->
   (* 3 n)
   (- 1)
   (* n)
   (/ 2)
   ;;(* n (- (* 3 n) 1))
)
)

(defn print-N-pentagon-numbers[n]
  (doseq [x (range n)]
    (prn (calculate-formula x))))

(print-N-pentagon-numbers 5)

