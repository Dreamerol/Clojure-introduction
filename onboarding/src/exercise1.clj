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

;; find the oldest person ---------------------------------------------------------------------------------------------------------------------
(defn findOldestHighOrder
  [els]
  (reduce (fn [acc item]
            (let [currAge (get item "age")]
              (if (> currAge acc)
                currAge
                acc)))
          0 els))

(findOldestHighOrder players)
(defn findOldest
  [els]
  (loop [remaining els oldest (first els)];;we give in the loop - > first we set the oldest to be the first person
    (if (empty? remaining);;if we have noone left to check 
      oldest;;we return the oldest one
      (let [next (first remaining)];;else we letnext to bethe firts player and in the next recur iteration the next player will be the first of
        ;;the last but firts remaining so the second one
        (if (> (get next "age") (get oldest "age"));;we use get "age" as a function, returning the value behind that key
          (recur (rest remaining) next);;if the next is older we pass it as oldest
          (recur (rest remaining) oldest))))));;else we keep with the oldest one

;;Better version-> we find the max-key(max value of the key age)
(defn findOldestBetterVersion
  [players]
  ;;get all the values behind the key "age"
  (apply max-key #(get % "age") players))

(findOldestBetterVersion players)
;;as max if max > cur -> passing max
;;else max = current or in our case we pass current

(findOldest players)

;; find the average age of the gamers -----------------------------------------------------------------------------------------------------------
;;Better version
(defn AverageAgeBetterVersion
  [players]
  (let [sumAges (reduce + (map #(get % "age") players))
        ;;we let sumAges = reduce + allAges
        countPlayers (count players)];;the count
    (/ sumAges countPlayers)))
;;and then we calculate the result with the local bindings

(AverageAgeBetterVersion players)

(def sumAges (reduce + (map #(get % "age") players)))
;;reduce will get a collection and return after the execution only one value "reducing"
;;map is just applying a func to the els of the collection and returns the new collection 
;;get % "age" -> we call the func get "age" that returns the values behind the age key in anew struct
;;% means it is applied on the all elements
(def countPlayers (count players));;we count the players
;;we find their average age
(def findAverageAge (/ sumAges countPlayers))
findAverageAge

;;another sum function just to exercise myself
(defn sumAllages
  [players]
  (loop [remaining players res 0]
    (if (empty? remaining)
      res
      (recur (rest remaining) (+ res (get (first remaining) "age"))))))

(sumAllages players)
;; [players]
;; (loop))

;; find all games-----------------------------------------------------------------------------------------------------------------------------------
(map #(get % "ownedGames") players)
;;map creates a collections of subvectors of ownedGames by get% we take all these games and put it into the new collection
;;created by the map func - but this data consists of price, name...
;;what if we want to get only the names?
(def games (map (fn [player]
                  (map #(get % "name") (get player "ownedGames"))) players))
;;this is an upgraded function that:
;;1. map applies the anonymous func fn onto the whole players struct
;;2. fn takes player by player and then it uses agan map on each's subcollection of games
;;the idea is to get the name of the ownedGames, so the inner map just returns the executional result ofthe applied func
;;which just gets the value behind the key
;;so inner map ->["name1", "name2"...]
;;the outer map->we apply to each player of the players so->[name1, name2], [name3, name4]...
;;finally we want to combine them in one struct if we want to place all the names into one place
games
;;so i created a unpack func to conver this [[], [...]] - >[.....]
;;the data will be accessed easier
(defn unpackSeq
  [seq]
  (loop [remaining seq res []]
    (if (empty? remaining)
      res
      (recur (rest remaining) (into res (first remaining))))));;using into to combine the collection res with the other collection

(def gamesFinal (unpackSeq games))
gamesFinal

;; find the games that are being played for over 150 hours------------------------------------------------------------------------------------
(defn gamesOver150
  [players]
  (filter #(> (get % "timePlayed") 150);;get % -> gets all the elements behind the key "ownedGames" and then returns those
          ;;objects (games) whose timePlayed is bigger 150
          (let [ownedGames #(get % "ownedGames")]
            (mapcat ownedGames players))))
;;mapcat concatenates into a map all the owned games -> then from the owned Games it checks the nested

(gamesOver150 players)

;; find the cheapest game----------------------------------------------------------------------------------------------------------------------
(def gameCol (let [ownedGames  #(get % "ownedGames")] (mapcat ownedGames players)))
;;i just changed ownedGames = #(get % "ownedGames")
gameCol
;;first I define a new collection, because it will be easier to compare the prices if i create a new list
;;consisting only of the ownedGames
(defn findCheapestGame
  [els]
  (loop [remaining els res (first remaining)];;then i loop through the list and set the base element to be the firts one
    (if (empty? remaining)
      res;;if we have iterated through all the elements return res - that's the obj with cheapest price
      (recur (rest remaining) (if (> (get (first remaining) "price") (get res "price"))
                                ;;with the get function we take the value behind the key "price" from the first el of remaining
                                res;;if fisrt price > res price then we know that the price of res is lower so we pass res
                                (first remaining);;else firts remainig has a lower price and we pass it
                                )))))
;;here in the recur func we pass the rest of the remaining and if
(findCheapestGame gameCol)
;; find the total playtime of each person-------------------------------------------------------------------------------------------------------
;;first we take map and then we get the key - (map key)
(def hoursPerGamer (map
                    (fn [player];;we create an anonymous function that returns a dictionary with the name and the total played hours
                      {:name (get player "name")
                       ;;we use the func to reduce - from a collection we want to return the sum of the hours, so we are "reducing"
                       :totalPlayedHours (reduce + (map #(get  % "timePlayed") (get player "ownedGames")));;gets the games played by the player
                       ;;get all the values behind the key "timePLayed" and apply to them the func + 
                       ;;when we have (reduce (func) collection) -> we apply this func to all the elements in the collection and return the result 
                       })
                    players))
hoursPerGamer
;; find the total playtime of everyone------------------------------------------------------------------------------------------------------------------
(reduce + (map #(get % :totalPlayedHours) hoursPerGamer))
;;we use reduce to return from our new collection the total hours of everyone;
;;the new col only holds the total played hours per player
;; find the the least popular game--------------------------------------------------------------------------------------------------------------------------------
(defn getAllGames
  [els]
  (loop [remaining els res []]
    (if (empty? remaining)
      res
      (recur (rest remaining) (conj res (get (first remaining) "ownedGames"))))))

(getAllGames players)
(def allGames (apply concat (getAllGames players)))
allGames
;;with high-order functions
(map #(get % "ownedGames") players)

(defn combineDicts
  [allGames]
  (loop [remaining allGames res []]
    (if (empty? remaining)
      res
      (recur (rest remaining) (merge-with res (first remaining))))))

(first allGames)

allGames

(defn countHours
  [els]
  (loop [res 0 remaining els]
    (if (empty? remaining) res
        (recur (rest remaining) (if (= (get (first remaining) "name") "God of War")
                                  (+ res (get (first remaining) "timePlayed"))
                                  res)))))
;;Map
(defn allGamesMap
  [els]
  (map (fn [item]
         (get item "ownedGames"))
       els))

(def all-games (allGamesMap players))
all-games
;;Reduce
;;   (into acc (get item "ownedGames")))
(defn allGamesReduce
  [els]
  (reduce (fn [acc item]
            (concat acc (get item "ownedGames")))
          [] els))

(allGamesReduce players)

(def allGamesFinal (apply concat (allGamesReduce players)))

allGamesFinal
(allGamesReduce players)
;!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
(defn countHoursPrim
  [els name]
  (reduce (fn [acc item]
            (if (= (get item "name") name)
              (+ acc (get item "timePlayed"))
              acc))
          0 els))
all-games
(countHoursPrim all-games "Age of Empire")
(def Names (map #(get % "name") all-games))
Names

(defn AllTimePlayedPerGame [names els]
  (reduce (fn [acc item]
            (assoc acc item (countHoursPrim els item)))
          {} names))

(AllTimePlayedPerGame Names allGames)

(map #(get % "name") allGames)
(countHours allGames)

(reduce + {:a 2})
(reduce + #(get % "price") allGames)

(combineDicts allGames)

(merge-with + {:a 2 :b 4 :c 90} {:a 56 :b 4})
(reduce + (get player "ownedGames") players)

;; find the most expensive game---------------------------------------------------------------------------------------------------------------------------
(apply max-key #(get % "price") allGames)
;;finding the max value of the key price from allGames
;; find total amount of money spent on a game-------------------------------------------------------------------------------------------------------------
allGames
(merge-with allGames)
;; find the price/time-played average of each game



[{"label" "Issue"
  "description" "Huston we have lift off"
  "ids" [1 2 3 4 5 6 7 9 123]
  "comments" [{"body" "hello"}
              {"body" "its"}
              {"body" "me"}]}
 {"label" "bug"
  "description" "Huston we have a problem"
  "ids" [471 24 32 33 34 35]
  "comments" [{"body" "I"}
              {"body" "am"}
              {"body" "hungry"}]}]

;; for each id generate a document containing the same data structure and only one id
;; for each new document with id, create a new one with a single comment



;; use the reduce function to combine a vector of maps like this:
;;(combine [{:a 1 :b 2} {:c 3} {:d 4 :e 5}])
;;  ==> {:a 1 :b 2 :c 3 :d 4 :e 5}

;; Eliminate consecutive duplicates of list elements.
;; If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
;; Example:
;; * (compress '(a a a a b c c a a d e e e e))
;; (A B C A D E)


Write a solution which squashes the following data structures
;; Result
;; [([{"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "hello"}
;;    {"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "its"}
;;    {"label" "Issue", "description" "Huston we have lift off", "id" 1, "comment" "me"}]
;;   [{"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "hello"}
;;    {"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "its"}
;;    {"label" "Issue", "description" "Huston we have lift off", "id" 2, "comment" "me"}])
;;  ([{"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "I"}
;;    {"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "am"}
;;    {"label" "bug", "description" "Huston we have a problem", "id" 471, "comment" "hungry"}]
;;   [{"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "I"}
;;    {"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "am"}
;;    {"label" "bug", "description" "Huston we have a problem", "id" 24, "comment" "hungry"}])]
;; Result
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


;-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;;zadacha 1
;;HIGH-ORDER FUNCTIONS

;find oldest person
(defn findOldestHighOrder
  [els]
  (reduce (fn [acc item]
            (let [currAge (get item "age")]
              (if (> currAge acc)
                currAge
                acc)))
          0 els))
(findOldestHighOrder players)

;find average age of the gamers
(defn average-gamers-age
  [els]
  (let [count (count els) sumAges (reduce (fn [acc item]
                                            (+ acc (get item "age"))) 0 els)]
    (/ sumAges count)))
(average-gamers-age players)

(average-gamers-age players)

;find all Games
(defn allGamesReduce
  [els]
  (reduce (fn [acc item]
            (concat acc (get item "ownedGames")))
          [] els))
(def allGames (allGamesReduce players))
allGames

;find the games played for over 150 hours

(def PlayTime (AllTimePlayedPerGame Names allGames))
PlayTime
(map {:a 23 :b 3} {:a 34 :b 45})

all-games

(defn getNames
  [els]
  (reduce (fn [acc item]
            (conj acc (get item "name"))) {} els)
  )

(getNames all-games)