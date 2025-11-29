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

