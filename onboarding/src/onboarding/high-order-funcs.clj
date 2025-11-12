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
(defn find-oldest-gamer
  [els]
  (reduce (fn [acc item]
            (let [currAge (get item "age")]
              (if (> currAge acc)
                currAge
                acc)))
          0 els))

(find-oldest-gamer players)

;find the average age of gamers-------------------------------------------------------------------------------------------
(defn average-age-gamers
  [players]
  (let [sumAges (reduce (fn [acc item]
                          (+ acc (get item "age"))) 0 players)
        countPlayers (count players)]
    (/ sumAges countPlayers)))
;;and then we calculate the result with the local bindings

(average-age-gamers players)

;find all games-------------------------------------------------------------------------------------------------------------
(defn allGamesReduce
  [els]
  (reduce (fn [acc item]
            (concat acc (get item "ownedGames")))
          [] els))

(def all-games (allGamesReduce players))
all-games
;transforming the all-games structure
(defn remove-keys
  [els key]
  (reduce (fn [acc item]
            (conj acc (assoc {} (get item "name") (get item key))))
          [] els))

(def formated-games-timePlayed (remove-keys all-games "timePlayed"))
;formating from [{..}{...}{...}] -> {.....}
;?why into doesnt keep the same keys
formated-games-timePlayed
;; (defn convert-in-map 
;;   [els]
;;   (reduce (fn [acc item]
;;             (into acc item))
;;           [] els))

;Working version
(defn convert-in-map-new-vals
  [els]
  (reduce (fn [acc item]
            (let [key (first (keys item)) points (first (vals item))]
              (assoc acc key (+ (or (get acc key) 0) points)))) {} els))
(def final-games-timeplayed-value (convert-in-map-new-vals formated-games-timePlayed))
final-games-timeplayed-value


;filter games played > 150 hours
;help function to merge the values
(defn sum-hours-assoc
  [els]
  (reduce (fn [acc item];;tuk
            (let [key (first item) points (second item)];destructure in func args 
              (assoc acc key (+ (or (get acc key) 0) points))
              ;; (prn (or (get acc key) 0))
              ;;   )
              )){} els))

;?? destructing howwww
;; (defn sum-hours-assoc
;;   [els]
;;   (reduce (fn [acc let [key points(first item) (second item)]]
;;             (assoc acc key (+ (or (get acc key) 0) points))
;;                  ;(prn (key points))
;;             ;;   )
;;             ){} els))

(def final-final-games-timeplayed (sum-hours-assoc final-games-timeplayed-value))
final-final-games-timeplayed

(def filered-games (filter (fn [item]
                             (> (second item) 150)) final-final-games-timeplayed))
filered-games

(defn combine-games-over-150
  [els]
  (reduce (fn [acc item]
            (assoc acc (first item) (second item)))
          {} els))

(def final-filterd-games-above150 (combine-games-over-150 filered-games))
final-filterd-games-above150

;;find least popular game------------------------------------------------------------------------
(defn find-least-popular-game-with-name
  [els]
  (reduce (fn [acc item]
            (let [hours (second item) hoursmin (second acc)]
              (if (> hoursmin hours)
                item
                acc)))  (first els) els))

(def name-of-least-popular-game (first (find-least-popular-game-with-name final-games-timeplayed-value)))
name-of-least-popular-game

;find the cheapest game

(defn cheapest-game
  [els]
  (reduce (fn [acc item]
            (let [accPrice (get acc "price") currentPrice (get item "price")]
              (if (> accPrice currentPrice)
                item
                acc))) (first els) els))

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
(most-expensive-game all-games)

(def name-most-expensive-game (get (most-expensive-game all-games) "name"))
name-most-expensive-game

;find the total money spent on each game--------------------------------------------------------------------------------
(def formated-games-price(remove-keys all-games "price"))
formated-games-price

;finding the sum of money spend on each game
(def total-money-spend-on-game (convert-in-map-new-vals formated-games-price))
total-money-spend-on-game

;;find the price/timeplayed average of each game-------------------------------------------------------------------------
all-games

(defn find-average-attribute-games
  [els key]
  (let [sum-prices (reduce (fn [acc item]
                             (+ acc (get item key)))
                           0 els) count-games (count els)]
    (/ sum-prices count-games)))

(def average-price (find-average-attribute-games all-games "price"))
average-price

(def average-time-played (find-average-attribute-games all-games "timePlayed"))
average-time-played


; find the total playtime of each person-----------------------------------------------------------


; find the total playtime of everyone-----------------------------------------------------------
(defn find-total-playtime-of-everyone
  [els]
  (reduce (fn [acc item]
            (+ acc (get item "timePlayed")))
          0 els))

all-games
;total playtime of everyone
(find-total-playtime-of-everyone all-games)
