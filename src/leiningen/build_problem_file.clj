(ns leiningen.build-problem-file
  (:require [clj-http.client :as http]
            [clojure.string :as str]
            [clojure.data.json :as json]))

(def base_url "http://www.4clojure.com/")

(defn get-url
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
    (parse (get-url (problem-url id)))))

(defn problem-file-template
 [params]
 (str/join [
"; " (params "title") ": " (params "url") "

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
")"
]))

(defn problem-file-str
  [id]
  (problem-file-template (problem-data id)))

(defn build-problem-file
  [project id]
  (let [file_name (str/join ["src/four_clojure/p" id ".clj"])]
    (spit file_name (problem-file-str id))
    (println file_name)))
