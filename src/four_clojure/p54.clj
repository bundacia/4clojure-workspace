(ns four-clojure-workspace.p54
  (:require [clojure.test :refer :all]))

(with-test

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

  (is (= (answer [1 2 1 3 1 2 4]) [1 2 3 4]))
  (is (= (answer [:a :a :b :b :c :c]) [:a :b :c])) 
  (is (= (answer '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3])))
  (is (= (answer (range 50)) (range 50)))) 
