(ns four-clojure.p54-test
  (:require [clojure.test :refer :all]
            [four-clojure.p54 :refer :all]))

(deftest answer-test
  (is (= (answer [1 2 1 3 1 2 4]) [1 2 3 4]))
  (is (= (answer [:a :a :b :b :c :c]) [:a :b :c])) 
  (is (= (answer '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3])))
  (is (= (answer (range 50)) (range 50))))
