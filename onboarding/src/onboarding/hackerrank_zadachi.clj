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
(defn remove-els-odd-pos-nth[els]
  (take-nth 2 els))

(remove-els-odd-pos-nth [1 2 3 4 5])


(defn string-compression
  [els]
  (loop [remaining els cnt 0 result "" current (first remaining) prev ""]
    (if (empty? remaining) 
    ;;result
      (do
        (if (> cnt 1)
        ;;   (concat result (format "%s%d" prev cnt))
        ;;   (concat result (format "%s" prev))
           (str result prev cnt)
           (str result prev))
         )
        
      
      (do
        ;; (prn (first remaining))
    ;;    (prn prev current)
        (prn cnt)

        (if (= prev current) 
          (recur (rest remaining) (+ cnt 1) result (first (rest remaining)) current)
          (recur (rest remaining) 1 (if (> cnt 1) 
                                      (str result prev cnt) 
                                      (str result prev))
                 (first (rest remaining)) current)
          )
        ) 
        )
      
      )
)

(def result "kdjjkd")
(format result "%s" "dhhd")

(format (format "%s%s" "dhghgd" "kjshjs") )
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
(defn find-abs[els]
  (map abs els))

(find-abs [-9 8 7 -9])


