(def lables [{"label" "Issue"
              "description" "Huston we have lift off"
              "id" [1 2 3 4 5 6 7 9 123]
              "comments" [{"body" "hello"}
                          {"body" "its"}
                          {"body" "me"}]}
             {"label" "bug"
              "description" "Huston we have a problem"
              "id" [471 24 32 33 34 35]
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
            (->> (assoc (dissoc els "ids") "id" item) 
                ;; {"leble" .. "key2"...} -> {... "id" item}
                 (conj acc))) [] (get els "ids")))


;better solution
(defn separate-structs-one-id [input-map]
  (reduce (fn [acc id]
            (->> (assoc input-map "id" id)
                 ;; {"leble" .. "key2"...} -> {... "id" item}
                 (conj acc)
                 )
            ) [] (get input-map "id")))

lables

(def final-struct-separated-ids (flatten (map separate-structs-one-id lables)))
final-struct-separated-ids
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
            (prn item)
            (str acc " " (get item "body"))) ;; str join
          "" els))

;better function witstring join
(defn get-comments[comments]
  (reduce (fn [acc comment]
            (conj acc (get comment "body")))
          [] comments))

(defn combine-comments-string-join [els]
  (clojure.string/join " " (get-comments els)))

(combine-comments-string-join [{"body" "jsj"} {"body" "jskj"}])


(defn combine-comments-one-struct
  [els]
  (reduce (fn [acc item]
            (let [comment (combine-comments-string-join (get item "comments"))]
               (prn item)
           ;;   (prn comment)

              (->>
               ;;here for each element we add a key comment where we write the comment
               (assoc (conj {} (dissoc item "comments")) "comment" comment)
               (conj acc))))
          [] els))

helper-two
(combine-comments-one-struct helper-two)

;;finally we apply the combine comments function on each substruct in the [] 
(def final-comment-structs (flatten (map combine-comments-one-struct final-struct)))
final-comment-structs

(flatten '([1 2 3]) )
(take 3 (prn "8"))

(def N (read-line))

  (defn write-N-times[N]
    ;; (loop [cnt]
    ;; (if (= cnt N)))
    (def cnt 0)
    
    (while (not (= cnt N))
      (+ cnt 1)
      (prn "Hello world"))
    ;;(take N (prn "Hello world!"))
    )

(write-N-times N)
  

