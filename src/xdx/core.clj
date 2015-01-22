(ns xdx.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn parse-dice
  [fistful]
  (let [fistful (str/replace fistful #"[^a-zA-Z0-9]" "")
        digits (str/split fistful #"[a-zA-Z]")
        dietype (str/replace fistful #"[0-9]" "")]
    (list 
      (Integer. (first digits))
      dietype
      (try (Integer. (last digits))
             (catch NumberFormatException e
               (last digits))))))
      ;(if (= 2 (count digits))
       ;(Integer. (second digits))))))

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

(defn roll-fudge
  "Rolls a 'dice' number of fudge dice."
  ([] (roll-fudge 4))
  ([dice] (reduce + (repeatedly dice #([-1 0 1] (rand-int 3))))))

(defn roll
  "Parse an input of dice (in the format xdy) and rolls them."
  [fistful]
  (let [parsed-fist (parse-dice fistful)
        dice (first parsed-fist)
        dietype (second parsed-fist)
        sides (last parsed-fist)]
   (condp = dietype
     "d" (roll-dice dice sides)
     "x" (roll-explode dice sides)
     "f" (roll-fudge dice)
     (str "Unexpected input \"" fistful "\"."))))

