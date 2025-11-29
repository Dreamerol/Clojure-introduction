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

sep
(def helper-three (first sep))
helper-three
(defn squashes [els])


 ;special helper func for combining the comments and inserting them into new map
(defn one-combine-comments
  [els]
  (let [comments (reduce (fn [acc item]
                           (->>
                            (assoc {} "body" (get item "comment")) ;; instead of assoc just create your own
                            (conj acc))
                           ;; (prn (assoc {} "body" (get item "comment")))
                           )

                         [] els)]



    (->
     (first els)
     (dissoc  "comment")
     (assoc  "comments" comments))))
;;(dissoc (first els) "comment")

;;(assoc (dissoc els "comment") "comments" comments)

(one-combine-comments helper-three)
;;here I apply te func to each substruct
(map one-combine-comments sep)



(defn lol [x] grouped-data
  (def y 7)
  (+ x y))

(lol 3)


(defn hello_word_n_times [n]
  (doseq [x (range n)]
    (prn "Hello World")))

;; (defn squash-final
;;   [els]
;;   (reduce (fn [acc item]
;;             (conj acc ()))))
;;  (prn (dissoc els "comment"))

;;(prn (assoc (dissoc els "comment") "comments" comments))


;;(one-squash helper-three)
;; Result



;;zad advanced - grouping data
(def sep1 [{"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "hello"}
           {"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "its"}
           {"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "me"}
           {"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "hello"}
           {"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "its"}
           {"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "me"}
           {"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "I"}
           {"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "am"}
           {"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "hungry"}
           {"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "I"}
           {"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "am"}
           {"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "hungry"}])

;; (vals {:a 2})

(def grouped-data (group-by #(get % "id") sep1))
grouped-data

(defn combine-body-comments
  [els]
  (reduce (fn [acc item]
            (conj acc {"body" (get item "comment")}))
          [] els))

;;grupiranu sa!!!
(defn combine-comments-from-grouped-data
  [each-group]
  (let [comments (combine-body-comments each-group)]
    ;;  (prn comments)
    (assoc (first each-group) "comment" comments)))

(defn format-data [els]
  (reduce (fn [acc item]
            (prn item)
            (->> item
                 ;;ako ne podavam argumenti nqmam nujda ot skobi
                 combine-comments-from-grouped-data
                 ;;kato podavam dopylnitelen argument imam nujda ot skobi
                 (conj acc))
         ;   (conj acc (combine-comments-from-grouped-data (second item)))
            )[] (vals els)))


(format-data grouped-data)

(def formatted-data (format-data grouped-data))
formatted-data

(combine-body-comments (first formatted-data))

(map combine-comments-from-grouped-data formatted-data)


;helper functions
;; (defn unique-keys[els key]
;;   (reduce (fn [acc item]
;;             (conj acc (get item key)))
;;           #{} els))

;; (unique-keys sep1 "id")

;;first version for group-function-----------------------------------------------
(defn get-items-behind-val [els key val]
  (reduce (fn [acc item]
            (if (= (get item key) val)
              (conj acc item)
              acc)) [] els))


(defn grouping [key els]
  (let [unique-keys (reduce (fn [acc item]
                              (->>
                               (get item key)
                               (conj acc)))
                            #{} els)]
    (reduce (fn [acc item]
              (conj acc [item (get-items-behind-val els key item)]))
            [] unique-keys)))


(grouping "id" sep1)

(get-items-behind-val sep1 "id" 1)
