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

(defn problem-file-template
 [params]
 (str/join [
"; " (params "url") "
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
")"
]))

(defn problem-file-str
  [id]
  (problem-file-template (problem-data id)))

(defn build
  [id]
  (let [content (problem-file-str id)]
    (with-open [w (clojure.java.io/writer (str/join ["src/four_clojure/p" id ".clj"]))]
      (.write w content)
      (println "Success!"))))
