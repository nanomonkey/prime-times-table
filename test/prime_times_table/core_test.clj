(ns prime-times-table.core-test
  (:require [clojure.test :refer :all]
            [prime-times-table.core :refer :all]))

(deftest prime-tests
  (testing "primes"
    (testing "with zero"
      (is (= (primes 0) '())))
    (testing "with one"
      (is (= (primes 1) '(2))))
    (testing "with ten"
      (is (= (primes 10) '(2 3 5 7 11 13 17 19 23 29))))))

(deftest cell-test
  (testing "cell"
    (testing "with one width and number"
      (is (= (cell 1 1) "1")))
    (testing "with a normal value"
      (is (= (cell 5 25) "   25")))))

(deftest create-row-test
  (testing "create-row"
    (is (= (create-row 1 1 [1])) " 1 1")
    (is (= (create-row 5 "x" [1 2 3 4 5]) 
           "    x    1    2    3    4    5"))))

(defmacro test-print-stdout [test-form print-out]
  "tests the output of a command that prints to STDOUT by capturing it in 
   a file and comparing what it finds to print-out"
  `(let [test-file# (str "/tmp/test" (rand 10000))]
     (binding [*out* (clojure.java.io/writer test-file#)]
       ~test-form
       (is (= (slurp test-file#) ~print-out)))))

(testing "print-multiplication-table" 
  (testing "small values of one"
    (test-print-stdout (print-multiplication-table! [1]) "   1\n 1 1\n"))
  (testing "default"
    (test-print-stdout (print-multiplication-table! [1 2 3 4 5 6 7 8 9 10]) 
                       "       1   2   3   4   5   6   7   8   9  10\n   1   1   2   3   4   5   6   7   8   9  10\n   2   2   4   6   8  10  12  14  16  18  20\n   3   3   6   9  12  15  18  21  24  27  30\n   4   4   8  12  16  20  24  28  32  36  40\n   5   5  10  15  20  25  30  35  40  45  50\n   6   6  12  18  24  30  36  42  48  54  60\n   7   7  14  21  28  35  42  49  56  63  70\n   8   8  16  24  32  40  48  56  64  72  80\n   9   9  18  27  36  45  54  63  72  81  90\n  10  10  20  30  40  50  60  70  80  90 100\n"
                       )))

(testing "-main"
  (testing "one"
    (test-print-stdout (-main 1) "   2\n 2 4\n" ))
  (testing "default argument"
    (test-print-stdout (-main) "       2   3   5   7  11  13  17  19  23  29\n   2   4   6  10  14  22  26  34  38  46  58\n   3   6   9  15  21  33  39  51  57  69  87\n   5  10  15  25  35  55  65  85  95 115 145\n   7  14  21  35  49  77  91 119 133 161 203\n  11  22  33  55  77 121 143 187 209 253 319\n  13  26  39  65  91 143 169 221 247 299 377\n  17  34  51  85 119 187 221 289 323 391 493\n  19  38  57  95 133 209 247 323 361 437 551\n  23  46  69 115 161 253 299 391 437 529 667\n  29  58  87 145 203 319 377 493 551 667 841\n"))
  (testing "twelve as argument"
    (test-print-stdout (-main 12) "         2    3    5    7   11   13   17   19   23   29   31   37\n    2    4    6   10   14   22   26   34   38   46   58   62   74\n    3    6    9   15   21   33   39   51   57   69   87   93  111\n    5   10   15   25   35   55   65   85   95  115  145  155  185\n    7   14   21   35   49   77   91  119  133  161  203  217  259\n   11   22   33   55   77  121  143  187  209  253  319  341  407\n   13   26   39   65   91  143  169  221  247  299  377  403  481\n   17   34   51   85  119  187  221  289  323  391  493  527  629\n   19   38   57   95  133  209  247  323  361  437  551  589  703\n   23   46   69  115  161  253  299  391  437  529  667  713  851\n   29   58   87  145  203  319  377  493  551  667  841  899 1073\n   31   62   93  155  217  341  403  527  589  713  899  961 1147\n   37   74  111  185  259  407  481  629  703  851 1073 1147 1369\n"))
  (testing "zero"
    (test-print-stdout (-main 0) 
                       "Please use an argument greater than zero.\n"))
   (testing "negative argument"
    (test-print-stdout (-main -4) 
                       "Please use an argument greater than zero.\n")))

