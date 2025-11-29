(defn convert-into-map [els]
  (reduce (fn [acc item] 
             (assoc acc item (-> (get acc item)
                                 (or 0)
                                 (+ 1))
                    ) 
            )
          {} els) 
  )
(convert-into-map [1 1 1 2 2 3])

(defn is-element-in-there [coll key val]
 (loop [remaining coll]
   (let [current (first remaining) k (first current) v (second current)]
     (cond 
       (and (= k key) (= v val)) true
       (empty? remaining) false
     :else (recur (rest remaining))))

   )
     )

;;getting all the numbers that are in A but not in B
(is-element-in-there {1 2, 3 4,5 6} 1 1)
(defn get-missing-numbers
  [els1 els2]
  (let [mp1 (convert-into-map els1) mp2 (convert-into-map els2)] 
    (reduce (fn [acc item] 
               (let [key (first item) val (second item)] 
                 (if (is-element-in-there mp2 key val)
                   acc
                   (conj acc key) 
                   )
                 
                 ))
               [] mp1
               )
    
    )

)
(concat [1 2 3] [ 5 6 7 8])
(get-missing-numbers [1 2 3 4 5] [6 7 8 1 2 2 3])
(defn get-diff-subset
  [mp1 mp2]
  
    (reduce (fn [acc item]
              (let [key (first item) val (second item)]
                (if (is-element-in-there mp2 key val)
                  acc
                  (conj acc key))))
            [] mp1))

(defn get-symmetric-difference[els1 els2]
  (let [mp1 (convert-into-map els1) mp2 (convert-into-map els2)]
    (set (concat (get-diff-subset mp1 mp2) (get-diff-subset mp2 mp1)))
    
    ))

;;i create a set with the symmetric difference from tha A and B into a set
(get-symmetric-difference [1 2 3 4 5] [2 2 3 4])
