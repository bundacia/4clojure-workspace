(ns four-clojure.build
  (:require [clj-http.client :as http]
            [clojure.string :as str]
            [clojure.data.json :as json]))

(def base_url "http://www.4clojure.com/")

(defn get
  [url]
  (:body (http/get url {:insecure? true})))

(defn parse
  [json-string]
  (json/read-str json-string))

(defn problem-url
  [id]
  (str/join "/" [base_url "api" "problem" id]))

(defn problem-data
  [id]
  (merge
    {"id" id "url" (problem-url id)}
    (parse (get (problem-url id)))))

(defn problem-file-str
 [params]
  (println (str/join ["
; " (params "url") "
; " (params "title") ":
; " (params "description") "

(ns four-clojure.p" (params "id") "
  (:require [clojure.test :refer :all]))

(with-test

  (def answer
    ; --> your code here <---
  )

"
(str/replace 
  (str/join "\n" (map #(str "(is " % ")") (params "tests")))
  "__"
  "answer" 
  )
"

)
"])))
