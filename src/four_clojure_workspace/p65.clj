; Black Box Testing: http://www.4clojure.com/api/problem/65

(ns four-clojure-workspace.p65
  (:require [clojure.test :refer :all]))

(with-test

  (def answer
    (fn get-type
      [coll]
      (let [; maps are a function mapping their keys to values
            is-map?    (fn [c] (= 8 (get (conj c [9 8]) 9)))
            ; lists conj onto the front
            is-list?   (fn [c] (= 2 (first (-> c (conj 1) (conj 2)))))
            ; vectors conj onto the back
            is-vector? (fn [c] (= 1 (first (-> c (conj 1) (conj 2)))))
            ; sets only have one of each item
            is-set?    (fn [c] (= 1 (count (filter #(= 1 %) (conj c 1 1)))))]
        (cond
          (is-map?    coll) :map
          (is-list?   coll) :list
          (is-set?    coll) :set
          (is-vector? coll) :vector))))

(is (= :list (answer (range (rand-int 20)))))
(is (= :map (answer {:a 1, :b 2})))
(is (= :vector (answer [1 2 3 4 5 6])))
(is (= :set (answer #{10 (rand-int 5)})))
(is (= [:map :set :vector :list] (map answer [{} #{} [] ()])))

)
