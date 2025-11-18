(def lables [{"label" "Issue"
               "description" "Huston we have lift off"
               "ids" [1 2 3 4 5 6 7 9 123]
               "comments" [{"body" "hello"}
                           {"body" "its"}
                           {"body" "me"}]}
              {"label" "bug"
               "description" "Huston we have a problem"
               "ids" [471 24 32 33 34 35]
               "comments" [{"body" "I"}
                           {"body" "am"}
                           {"body" "hungry"}]}])

 ;; for each id generate a document containing the same data structure and only one id------------------------------------------------------------------------------------
;we collect the ids
 (defn get-ids [els]
   (reduce (fn [acc item]
             (conj acc (get item "ids"))) [] els))

;all the ids in one place
 (def idta (get-ids lables))
 ;;without getting the idta in anoter struct!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 idta

;testing the func with one el in a subcollection
 (def firstids (first idta))
 (def first-els (first lables))
 first-els
 firstids

;helper function for creating a single map for each id

;reduce withoud writing the keys!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111
 (defn create-one-struct [els ids]
   (reduce (fn [acc item]
             (conj acc (assoc {} "label" (get els "label") "description" (get els "description") "comments" (get els "comments") "id" item)))  [] ids))


 (defn collect-ids [els]
   (reduce (fn [acc item]
             (into acc item))
           [] els))

;combining all the ids in a []
 (def all-ids (collect-ids idta))
 all-ids

 (create-one-struct first-els firstids)
;we implement the function map -> to apply the creation function for a map with single id
 (def final-id-structs (map create-one-struct lables idta))
 final-id-structs

;here i merge them (the structs) into a []
 (defn combine-structs [els]
   (reduce (fn [acc item]
             (concat acc item))
          ;into, flatten
           [] els))

 [[2 2] [3 4]] [2 2 3 4]
 (def id-structs-separated (combine-structs final-id-structs))
;merged structs wit single ids
 id-structs-separated

;here we create a special vector for the names of the files soc then to apply on
;each of them the creation-files map function

 (defn create-file-names [els]
   (reduce (fn [acc item]
             (conj acc (str "struct" item ".txt")))
           [] els))

;creating the files
 ["structID.txt"]
 (def file-names (create-file-names all-ids))

 final-id-structs
 (count file-names)

;here we write in each id-name file each struct
 (map spit file-names id-structs-separated)

 (spit "zad2.txt" final-id-structs)

 ;; for each new document with id, create a new one with a single comment----------------------------------------------------------------------------------------
 all-ids
 ;;(defn create-file-names-comments)
 ;; (defn create [els ids]
 ;;   (reduce))

 (def l {:a 1 :b 2 :c 3})
 (get l :a)
 (assoc {} :a (get l :a) :b 9)
 ;; (generate-ids lables)
 (spit "ex2.txt" lables)

 ;; use the reduce function to combine a vector of maps like this:
 (defn combine
   [els]
   (reduce (fn [acc item]
             (conj acc item))
           {} els))

 (combine [{:a 1 :b 2} {:c 3} {:d 4 :e 5}])
 ;;  ==> {:a 1 :b 2 :c 3 :d 4 :e 5}

 ;; Eliminate consecutive duplicates of list elements.
 ;; If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
 ;; Example:
 ;; * (compress '(a a a a b c c a a d e e e e))
 ;; (A B C A D E)


 ;;po-predpochitan variant
 (defn remove-consequences
   [els]
   (reduce (fn [acc item]
             (let [lasti (last acc)]
               (if (= lasti item)
                 acc
                 (conj acc item)))) [] els))

;using macro
 (defn remove-consequences-macro
   [els]
   (reduce (fn [acc item]
             (->
              (last acc)
              (= item)
              (if
               acc
                (conj acc item)))) [] els))


 (defn format-upper-case
   [els]

   (map clojure.string/upper-case els))


 (format-upper-case ["a" "b"])
 (->
  (format-upper-case '(a s x d d d))
  (remove-consequences-macro))
 ;;   (reduce [acc item]
 ;;           (let [lasti (last acc)])
 ;;           '() els)



 ;;Write a solution which squashes the following data structures
 ;; Result
 (def sep [[{"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "hello"}
            {"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "its"}
            {"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "me"}]
           [{"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "hello"}
            {"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "its"}
            {"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "me"}]
           [{"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "I"}
            {"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "am"}
            {"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "hungry"}]
           [{"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "I"}
            {"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "am"}
            {"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "hungry"}]])

 sep
 (def helper-three (first sep))
 helper-three
 (defn squashes [els])

 
 ;special helper func for combining the comments and inserting them into new map
 (defn one-combine-comments
   [els]
   (def comments (reduce (fn [acc item]
                           (->>
                            (assoc {} "body" (get item "comment"))
                            (conj acc))
                           ;; (prn (assoc {} "body" (get item "comment")))
                           )
                         [] els))
   
   (->
    (first els)
    (dissoc  "comment")
    (assoc  "comments" comments)
   )

 )
   ;;(dissoc (first els) "comment")

 ;;(assoc (dissoc els "comment") "comments" comments)
 
(one-combine-comments helper-three)
;;here I apply te func to each substruct
(map one-combine-comments sep)
 


 (defn lol[x]
   (def y 7)
   (+ x y))
 
 (lol 3)

;; (defn squash-final
;;   [els]
;;   (reduce (fn [acc item]
;;             (conj acc ()))))
;;  (prn (dissoc els "comment"))

 ;;(prn (assoc (dissoc els "comment") "comments" comments))


;;(one-squash helper-three)
;; Result

[[{"id" 1,
   "label" "Issue",
   "description" "Huston we have lift off",
   "comments" [{"body" "hello"} {"body" "its"} {"body" "me"}]}
  ...
  {"id" 123,
   "label" "Issue",
   "description" "Huston we have lift off",
   "comments" [{"body" "hello"} {"body" "its"} {"body" "me"}]}]
 [{"id" 471,
   "label" "bug",
   "description" "Huston we have a problem",
   "comments" [{"body" "I"} {"body" "am"} {"body" "hungry"}]}
  ...
  {"id" 35,
   "label" "bug",
   "description" "Huston we have a problem",
   "comments" [{"body" "I"} {"body" "am"} {"body" "hungry"}]}]]

(defn)

