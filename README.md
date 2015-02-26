# four-clojure-workspace

A workspace for hackiong on four-clojure problems

## Usage

Use the `build-problem-file` lein task to pull the given problem from the
4clojure site and create a stubbed out cliojure file with the tests and a place
for you to write your solution.

For example...

```
lein build-problem-file 1
```

Will write this content to `src/four_clojure_workspace/p1.clj`

```clojure
; Nothing but the Truth: http://www.4clojure.com/api/problem/1

(ns four-clojure-workspace.p1
  (:require [clojure.test :refer :all]))

(with-test

  (def answer
    ; --> your code here <---
  )

(is (= answer true)))
```

## License

TODO

