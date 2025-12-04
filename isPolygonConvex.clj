   
;;zadacha - determine whether a polygon is convex or concave     
;;function to convert two points into vector
     (defn convert-into-vector [point1 point2] 
    (map - point2 point1) 
)
 
 (map - [1 2 3] [4 5 6])
 (convert-into-vector [1 2] [3 4])
 (reduce * [1 2 3])
   (->> (map * [1 2 3] [4 5 6]) (reduce +))

(defn negative-angle [point1 point2 point3]
  (let [col1 (convert-into-vector point1 point2) col2  (convert-into-vector point1 point3)
    result (->> (map * col1 col2) (reduce +))]
    (< result 0))
  )
     

     (negative-angle [1 2] [1 3] [1 4])
    
(defn is-polygon-convex
  [els]
  (loop [remaining (-> (conj els (first els))
                       (conj (second els))) prev (first els) curr (second els) next (second (rest els))]
    
   ;; (prn (negative-angle prev curr next))
    (cond 
      (negative-angle prev curr next) false
      (empty? (drop 2 remaining)) true
      :else  ;;(do (prn prev curr next)
                 ;;(prn (negative-angle prev curr next))
                 (recur (rest remaining) curr next (first (drop 3 remaining))
                        ;;) 
      )
      )
)
)
     
     (negative-angle [0 1] [] nil)
     (negative-angle [0 0] [0 1] [1 0])
(is-polygon-convex [[0 0] [0 1] [1 0]])
