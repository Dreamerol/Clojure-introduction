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


;; for each id generate a document containing the same data structure and only one id--------------------------------------------------------------------------
;;helper functions to visualize my idea

(def help-first (first lables))
help-first
(def keyta (keys help-first))
keyta
(def valta (vals help-first))
valta


(defn separate-structs [els]
  (reduce (fn [acc item]
            (->> (assoc (conj {} (dissoc els "ids")) "id" item)
                 (conj acc))) [] (get els "ids")))
;;here i separate the struct and create [] with {} maps with each id
(map separate-structs lables)

;; (defn separate-structs [els]
;;   (reduce (fn [acc item]
;;             (-
;;              (conj {} (dissoc els "ids"))
;;              (assoc  "id" item)
;;              (conj acc)))

;;             ;;(conj acc help)
;;             )
;;           [] (get els "ids"))

(def final-struct (map separate-structs lables))
final-struct
(separate-structs help-first)
(separate-structs help-first)
(assoc (assoc {} :a 2) :b 4)

;;(assoc {} (keys {:a }))
;;(defn )

;; for each new document with id, create a new one with a single comment------------------------------------------------------------------------------
(def helper-two (first final-struct))
helper-two

;;helper function for writing the comments
(defn combine-comments [els]
  (reduce (fn [acc item]
          (str acc " " (get item "body"))
  )
          "" els)
  )

(combine-comments [{"body" "jsj"} {"body" "jskj"}])


(defn combine-comments-one-struct
  [els]
  (reduce (fn [acc item]
            (let [comment (combine-comments (get item "comments"))]
              ;; (prn comment)
              (prn comment)
              
             (->>
              ;;here for each element we add a key comment where we write the comment
             (assoc (conj {} (dissoc item "comments")) "comment" comment)
              (conj acc)
               
               )
               )
  )
          [] els
)
)

helper-two
(combine-comments-one-struct helper-two)

;;finally we apply the combine comments function on each substruct in the [] 
(map combine-comments-one-struct final-struct)