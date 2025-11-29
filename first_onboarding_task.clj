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

;using macro
(defn find-oldest-gamer-macro
  [els]
  (reduce (fn [acc item]
            (let [currAge (get item "age")]
              (->
               (> currAge acc)
               (if
                currAge
                 acc)))) 0 els))


(find-oldest-gamer-macro players)

;find the average age of gamers-------------------------------------------------------------------------------------------
(defn average-age-gamers
  [players]
  (let [sumAges (reduce (fn [acc item]
                          (+ acc (get item "age"))) 0 players)
        countPlayers (count players)]
    (/ sumAges countPlayers)))
;;and then we calculate the result with the local bindings
(average-age-gamers players)

;;using thread - macro
(defn average-age-gamers-macro
  [players]
  (let [sumAges (reduce (fn [acc item]
                          (->

                           (get item "age")
                           (#(do (prn %) %))

                           (+ acc))) 0 players)
        ;; (-> item
        ;;     (get "age")
        ;;     (+ acc)) another version

        countPlayers (count players)]
    (/ sumAges countPlayers)))
;;and then we calculate the result with the local bindings
players
(average-age-gamers-macro players)


;find all games-------------------------------------------------------------------------------------------------------------
(defn allGamesReduce
  [els]
  (reduce (fn [acc item]
            (concat acc (get item "ownedGames")))
          [] els))

;;thread macro 
(defn allGamesReduce-macro
  [els]
  (reduce (fn [acc item]
            (-> item
                (get "ownedGames")
                (concat acc)))
          [] els))

(allGamesReduce-macro players)
(def all-games (allGamesReduce players))
all-games
;create collection of all game names
(defn create-list-all-game-names
  [els]
  (reduce (fn [acc item]
            (conj acc (get item "name")))
          #{} els))

;with macro !
(defn create-list-all-game-names-macro
  [els]
  (reduce (fn [acc item]
            (->>
             (get item "name")
             (conj acc)))
          #{} els))

(create-list-all-game-names-macro all-games)

(create-list-all-game-names all-games)

;transforming the all-games structure
(defn remove-keys
  [els key]
  (reduce (fn [acc item]
            (conj acc (assoc {} (get item "name") (get item key))))
          [] els))

;thread macro 
(defn remove-keys-macro
  [els key]
  (reduce (fn [acc item]
            (->>
             (assoc {} (get item "name") (get item key))
             (conj acc)))
          [] els))

(remove-keys-macro all-games "timePlayed")

;;find the games played for over 150 hours-------------------------------------------------------------------------------------------------------------
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

(defn convert-in-map-new-vals-macro
  [els]
  (reduce (fn [acc item]
            (let [key (first (keys item)) points (first (vals item)) p (or (get acc key) 0)]
              (->> p 
               (+ points) 
                   (assoc acc key)
               ))) {} els))


(convert-in-map-new-vals-macro formated-games-timePlayed)
(def final-games-timeplayed-value (convert-in-map-new-vals formated-games-timePlayed))
final-games-timeplayed-value


;filter games played > 150 hours
;help function to merge the values
(defn sum-hours-assoc-with-destructing
  [els]
  (reduce (fn [acc [key points]];;tuk
            (assoc acc key (+ (or (get acc key) 0) points)))

          {} els))
(sum-hours-assoc-with-destructing final-games-timeplayed-value)

;macro
(defn sum-hours-assoc-with-destructing-macro
  [els]
  (reduce (fn [acc [key points]];;tuk
            (->
             (get acc key)
             (or 0)
             (->> 
              (+ points)
              (assoc acc key))
            ) 

        )  {} els))

(defn sum-hours-assoc-with-destructing-macro-better
  [els]
  (reduce (fn [acc [key points]]
            (let [p (or (get acc key) 0)]
              (->> p
               (+ points)
               (assoc acc key))))  {} els))

(sum-hours-assoc-with-destructing-macro-better final-games-timeplayed-value)
(sum-hours-assoc-with-destructing-macro final-games-timeplayed-value)

;?? destructing howwww
;; (defn sum-hours-assoc
;;   [els]
;;   (reduce (fn [acc let [key points(first item) (second item)]]
;;             (assoc acc key (+ (or (get acc key) 0) points))
;;                  ;(prn (key points))
;;             ;;   )
;;             ){} els))

(def final-final-games-timeplayed (sum-hours-assoc-with-destructing final-games-timeplayed-value))
final-final-games-timeplayed

(def filered-games (filter (fn [item]
                             (> (second item) 150)) final-final-games-timeplayed))

;macro
(def filered-games-macro (filter (fn [item]
                                   (->
                                    (second item)
                                    (> 150))) final-final-games-timeplayed))

filered-games
filered-games-macro

(defn combine-games-over-150
  [els]
  (reduce (fn [acc item]
            (assoc acc (first item) (second item)))
          {} els))

(def final-filterd-games-above150 (combine-games-over-150 filered-games))
final-filterd-games-above150

;find the cheapest game----------------------------------------------------------------------------

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

; find the total playtime of each person-----------------------------------------------------------
players
(defn calculate-playtime-person
  [els]
  (reduce (fn [acc item]
            (+ acc (get item "timePlayed")))
          0 els))

(defn calculate-playtime-for-all [els]
  (reduce (fn [acc item]
            (let [name (get item "name") playtime (calculate-playtime-person (get item "ownedGames"))]
              (assoc acc name playtime)))  {} els))

(calculate-playtime-for-all players)
;;with macro
(defn calculate-playtime-person-macro
  [els]
  (reduce (fn [acc item]
            (-> (get item "timePlayed")
                (+ acc)))
          0 els))

(defn calculate-playtime-for-all-macro [els]
  (reduce (fn [acc item]
            (let [name (get item "name") playtime (calculate-playtime-person-macro (get item "ownedGames"))]
              (assoc acc name playtime)))  {} els));;tuk maj e samo edna finkciq i nqma nugda ot macro

; find the total playtime of everyone-----------------------------------------------------------
(defn find-total-playtime-of-everyone
  [els]
  (reduce (fn [acc item]
            (+ acc (get item "timePlayed")))
          0 els))

;;thread macro
(defn find-total-playtime-of-everyone-macro
  [els]
  (reduce (fn [acc item]
            (-> item
                (get "timePlayed")
                (+ acc)))
          0 els))

all-games
(find-total-playtime-of-everyone-macro all-games)
;total playtime of everyone
(find-total-playtime-of-everyone all-games)

;;find least popular game------------------------------------------------------------------------
(defn find-least-popular-game-with-name
  [els]
  (reduce (fn [acc item]
            (let [hours (second item) hoursmin (second acc)]
              (if (> hoursmin hours)
                item
                acc)))  (first els) els))

(defn find-least-popular-game-with-name-macro
  [els]
  (reduce (fn [acc item]
            (let [hours (second item) hoursmin (second acc)]
              (-> (> hoursmin hours)
                  (if
                   item
                    acc)))) (first els) els))


(def name-of-least-popular-game (first (find-least-popular-game-with-name final-games-timeplayed-value)))
name-of-least-popular-game

;find most expensive game---------------------------------------------------------------------
(defn most-expensive-game
  [els]
  (reduce (fn [acc item]
            (let [accPrice (get acc "price") currentPrice (get item "price")]
              (if (< accPrice currentPrice)
                item
                acc))) (first els) els))
;macro
(defn most-expensive-game-macro
  [els]
  (reduce (fn [acc item]
            (let [accPrice (get acc "price") currentPrice (get item "price")]
              (->
               (< accPrice currentPrice)
               (if
                item
                 acc)))) (first els) els))

(most-expensive-game-macro all-games)

(def name-most-expensive-game (get (most-expensive-game all-games) "name"))
name-most-expensive-game

;find the total money spent on each game--------------------------------------------------------------------------------
(def formated-games-price (remove-keys all-games "price"))
formated-games-price

;finding the sum of money spend on each game
(def total-money-spend-on-game (convert-in-map-new-vals formated-games-price))
total-money-spend-on-game

(defn foo [[arg & others :as all]]
  (prn arg others all))

(foo [1 2 3])

(defn doo [{kaval :a maval "b" :as all}]
  (let []
    (prn all kaval maval)))


(doo {:a 1 "b" 3 :c "adwa"})



;;find the price/timeplayed average of each game-------------------------------------------------------------------------
all-games

(defn find-average-attribute-games
  [els key]
  (let [sum-prices (reduce (fn [acc item]
                             (+ acc (get item key)))
                           0 els) count-games (count els)]
    (/ sum-prices count-games)))

;;thread macro
(defn find-average-attribute-games-macro
  [els key]
  (let [sum-prices (reduce (fn [acc item]
                             (->
                              (get item key)
                              (+ acc)))
                           0 els) count-games (count els)]
    (/ sum-prices count-games)))
(find-average-attribute-games-macro all-games "price")

(def average-price (find-average-attribute-games all-games "price"))
average-price

(def average-time-played (find-average-attribute-games all-games "timePlayed"))
average-time-played


