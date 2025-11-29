;;Function for generating all subsets
;;solutions inspired from stack overflow
(defn power-set[s]
  (loop [[current & remaining] (seq s) p '(())]
    (if current
      (recur remaining (concat p (map (partial cons current) p)))
      p)))

;;function for finding the subsets with sum > bigger than S
(defn find-subarrays-greater-than-S[els S]
  (let [subarrays (power-set els)]
  (loop [acc [] subs subarrays]
    (let [current (first subs)]
      (if (empty? subs)
        acc
      (if (> (reduce + current) S)
        (recur (conj acc current) (rest subs))
         (recur acc (rest subs))
      )
    ))
  )
  )
)

(power-set [1 2 3 4])
(find-subarrays-greater-than-S [1 2 3 4] 4)

;;finding all the permutations
(defn permutations [s]
  ;;(lazy-seq 
   (if (seq (rest s))
     (apply concat (for [x s]
                     (map #(cons x %) (permutations (remove #{x} s)))))
     [s]
     )
   ;;)
  )

(permutations [1 2 4 3])
