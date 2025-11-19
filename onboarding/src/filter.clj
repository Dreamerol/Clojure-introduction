(def players [{"name" "Smith"
               "age" 12
               "ownedGames" [{"name" "God of War" "price" 68 "timePlayed" 124}
                             {"name" "Age of empires 4" "price" 80 "timePlayed" 300}
                             {"name" "StarCraft" "price" 20 "timePlayed" 421}]}
              {"name" "Bob"
               "age" 16
               "ownedGames" [{"name" "God of War" "price" 68 "timePlayed" 200}
                             {"name" "Age of empires 4" "price" 80 "timePlayed" 10}
                             {"name" "GTA V" "price" 120 "timePlayed" 500}]}
              {"name" "Suzan"
               "age" 25
               "ownedGames" [{"name" "HALO" "price" 50 "timePlayed" 800}
                             {"name" "Diablo" "price" 29.99 "timePlayed" 874}
                             {"name" "World of warcraft" "price" 200 "timePlayed" 1247}]}])

(defn allGamesReduce
  [els]
  (reduce (fn [acc item]
            (concat acc (get item "ownedGames")))
          [] els))

(def all-games (allGamesReduce players))
all-games

(defn getNames
  [els]
  (reduce (fn [acc item]
            (conj acc (get item "name"))) #{} els))

(getNames all-games)
;;(def names (set (getNames all-games)))
(def names (getNames all-games))

(defn countHoursPrim
  [els name]
  (reduce (fn [acc item]
            (if (= name (get item "name"))
              (+ acc (get item "timePlayed"))
              acc))
          0 els))


(defn AllTimePlayedPerGame [names els]
  (reduce (fn [acc item]
            (conj acc (assoc {} :name item :totalHours (countHoursPrim els item))))
          [] names))

;; (defn AllTimePlayedPerGame [names els]
;;   (reduce (fn [acc item]
;;             ;;(let [cur {:name item :totalPlayed (countHoursPrim els item)}]
;;             (conj acc (assoc {} :name item :totalHours (countHoursPrim els item))))
;;           [] names))

(def Games (AllTimePlayedPerGame names all-games))
Games

(defn find150
  [els]
  (reduce (fn [acc el]
            (if (> (get el :totalHours) 150)
              (conj acc el)
              acc))
          [] els))

(def filteredGames (find150 Games))
filteredGames

;;Better version
(filter (fn [item]
          (> (get item :totalHours) 150)) filteredGames)


all-games
;;zad 1 [{.....} {.....}]
(defn filteredGamesBetter
  [els]
  (reduce (fn [acc item]
            (conj acc (assoc {} (get item "name") (get item "timePlayed"))))
          [] els))

(def newGames (filteredGamesBetter all-games))
;;zad 3 
newGames
(defn convert-in-map 
  [els]
  (reduce (fn [acc item]
            (into acc item))
          {} els))
(def NEW-GAMES (convert-in-map newGames))
NEW-GAMES

(defn sumHours [els]
  (reduce (fn [acc item]
            (merge-with + acc item))
          
          (first els) (rest els)))

(defn sumHours2 [els]
  (reduce (fn [acc item]
            ;;refactor with assoc
            (merge-with + acc item))
          {} els))

newGames
(def FINAL-GAMES (sumHours newGames))
FINAL-GAMES



(assoc {:a 2} :a 4)

(defn sumHoursAssoc
  [els]
  (reduce (fn [acc item];;tuk
            (let [key (first item) points (second item)];destructure in func args 
              (assoc acc key (+ (or (get acc key) 0) points))
                 ;(prn (key points))
              ;;   )
              
              )) {} els))

NEW-GAMES
(sumHoursAssoc NEW-GAMES)

(first (vals {:a 1}))

(filter (fn [item]
          (prn item)) FINAL-GAMES)

;;into map {...}
;;converting into a map
(def finalFilterdGames (filter (fn [item]
          (> (second item) 150)) FINAL-GAMES))
finalFilterdGames

(defn map-conversion[els]
  (reduce (fn [acc item]
            (assoc acc (first item) (second item))) {} els))

(def FINAL-FINAL-GAMES (map-conversion finalFilterdGames))
FINAL-FINAL-GAMES

(defn unpack-final-games
  [els]
  (reduce (fn [acc item]
            (into acc item))
          [] els))

(def filtered-games-one (unpack-final-games finalFilterdGames))
filtered-games-one


;;zad 2 {..............}

(defn GamesUnion [els]
  (reduce (fn [acc item]
            (into acc item))
          {} els))

(def finalGames (GamesUnion newGames))
finalGames


;;find least popular games - we will get the game with the fewest TimePlayed

(defn find-least-popular-game
  [els]
  (reduce (fn [acc item]
           ; (prn item))
            (let [hours (second item)]
              (if (> acc hours)
                hours
                acc)
              )) (second (first els)) els))

(find-least-popular-game FINAL-FINAL-GAMES)

;;find the name of the least popular game
(defn find-least-popular-game-with-name
  [els]
  (reduce (fn [acc item]
           ; (prn item))
            (let [hours (second item) hoursmin (second acc)]
              (if (> hoursmin hours)
                item
                acc)))  (first els) els))

(find-least-popular-game-with-name FINAL-FINAL-GAMES)