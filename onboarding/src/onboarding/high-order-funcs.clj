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
;find oldest person------------------------------------------------------------------------------------------------
(defn findOldestHighOrder
  [els]
  (reduce (fn [acc item]
            (let [currAge (get item "age")]
              (if (> currAge acc)
                currAge
                acc)))
          0 els))

(findOldestHighOrder players)

;find the average age of gamers-------------------------------------------------------------------------------------------
(defn AverageAgeBetterVersion
  [players]
  (let [sumAges (reduce (fn [acc item]
                          (+ acc (get item "age"))) 0 players)
        countPlayers (count players)]
    (/ sumAges countPlayers)))
;;and then we calculate the result with the local bindings

(AverageAgeBetterVersion players)

;find all games-------------------------------------------------------------------------------------------------------------
(defn allGamesReduce
  [els]
  (reduce (fn [acc item]
            (concat acc (get item "ownedGames")))
          [] els))

(def all-games (allGamesReduce players))
all-games
;transforming the all-games structure
(defn filteredGamesBetter
  [els]
  (reduce (fn [acc item]
            (conj acc (assoc {} (get item "name") (get item "timePlayed"))))
          [] els))

(def gamesAlmost (filteredGamesBetter (allGamesReduce players)))
gamesAlmost
(defn convert-in-map
  [els]
  (reduce (fn [acc item]
            (into acc item))
          {} els))

(def ALL-GAMES (convert-in-map gamesAlmost))

ALL-GAMES


;filter games played > 150 hours
;help function to merge the values
(defn sumHoursAssoc
  [els]
  (reduce (fn [acc item];;tuk
            (let [key (first item) points (second item)];destructure in func args 
              (assoc acc key (+ (or (get acc key) 0) points))
                 ;(prn (key points))
              ;;   )
              )){} els))

(def FINAL-GAMES (sumHoursAssoc ALL-GAMES))
FINAL-GAMES

(def FilterdGames (filter (fn [item]
                            (> (second item) 150)) FINAL-GAMES))
FilterdGames

(defn combine-games-over-150
  [els]
  (reduce (fn [acc item]
            (assoc acc (first item) (second item)))
          {} els))

(def finalFilterdGames (combine-games-over-150 FilterdGames))
finalFilterdGames

;;find least popular game
(defn find-least-popular-game-with-name
  [els]
  (reduce (fn [acc item]
            (let [hours (second item) hoursmin (second acc)]
              (if (> hoursmin hours)
                item
                acc)))  (first els) els))

(def name-of-least-popular-game (first (find-least-popular-game-with-name FINAL-GAMES)))
name-of-least-popular-game

;find the cheapest game
(defn cheapest-game
  [els]
  (reduce (fn [acc item]
             (let [minPrice (get acc "price") currentPrice (get item "price")]
               (if (> minPrice currentPrice)
                 currentPrice
                 minPrice))) (first els) els))


(defn cheapest-game
  [els]
  (reduce (fn [acc item] 
            (let [accPrice (get acc "price") currentPrice (get item "price")]
              (if (> accPrice currentPrice)
                item
                acc) 
              )) (first els) els))

(def name-cheapest-game (get (cheapest-game all-games) "name"))
name-cheapest-game
;name-cheapest-game

;find most expensive game---------------------------------------------------------------------

(defn most-expensive-game
  [els]
  (reduce (fn [acc item]
            (let [accPrice (get acc "price") currentPrice (get item "price")]
              (if (< accPrice currentPrice)
                item
                acc))) (first els) els))

(def name-most-expensive-game (get (most-expensive-game all-games) "name"))
name-most-expensive-game