(ns xdx.core
  (:gen-class))

(defn roll-dice
  "Rolls a 'dice' number of dice with 'sides' sides. If only one arg, rolls 1 'sides' die."
  ([sides] (roll-dice 1 sides))
  ([dice sides]
   (reduce + (repeatedly dice #(inc (rand-int sides))))))

(defn roll-explode
  "Rolls a 'dice' number of dice with 'sides' sides, with the dice _exploding_ if a max val is rolled."
  [dice sides]
  (loop [left-to-roll dice
         total 0]
    (if (zero? left-to-roll)
      total
      (let [the-roll (inc (rand-int sides))]
        (if (= the-roll sides)
          (recur left-to-roll (+ total the-roll))
          (recur (dec left-to-roll) (+ total the-roll)))))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  )
