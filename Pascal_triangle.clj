
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
