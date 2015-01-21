(ns xdx.core
  (:gen-class))

(defn get-input
  "Waits for user to enter a number of dice and hit enter, then cleans the input."
  ([] (get-input "Please enter input. [DEFAULT MESSAGE]" nil))
  ([default] (get-input "Please enter input. [DEFAULT MESSAGE]" default))
  ([message default]
   (prn message)
   (let [input (clojure.string/trim (read-line))]
     (if (empty? input)
       default
       (clojure.string/lower-case input)))))

(defn roll-dice
  "Rolls a 'dice' number of dice with 'sides' sides."
  [dice sides]
  (loop [left-to-roll dice
         total 0]
    (if (zero? left-to-roll)
      total
      (recur (dec left-to-roll) (+ total (inc (rand-int sides)))))))

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
