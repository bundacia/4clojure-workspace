(ns four-clojure.p54)

(defn answer
  [col]
  (loop [col col, seen #{}, result []]
    (if (seq col)
      (recur
       (rest col)
       (conj seen (first col))
       (if (seen (first col))
         result
         (conj result (first col))))
      result)))
