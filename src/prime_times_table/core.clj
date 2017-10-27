(ns prime-times-table.core
  (:gen-class))

(defn sieve [s]
  (cons (first s)
        (lazy-seq (sieve (filter #(not= 0 (mod % (first s)))
                                 (rest s))))))
(defn primes [n]
  (take n (sieve (iterate inc 2))))

(defn cell [width contents]
  "creates a formatted cell string of a specified width"
  (if (number? contents)
    (format (str "%" width "d") contents)
    (format (str "%" width "s") contents))) 

(defn create-row [col-width col1 remaining]
  "combines an initial col1 with the remaining sequence of cells passing 
along the cell width"
   (apply str (cons (cell col-width col1) 
                      (map #(cell col-width %) remaining))))

(defn print-multiplication-table! [nums]
  "prints out a multiplication table from an array of numbers (nums)"
  (let [width (inc (count (str(* (last nums) (last nums)))))]
    (println (create-row width "" nums))
    (doseq [num nums]
      (println (create-row width num (map #(* num %) nums))))))

(defn -main
  ([] (-main 10))                    ;default value
  ([n] 
   (if (> n 0) 
     (print-multiplication-table! (primes n))
     (println "Please use an argument greater than zero."))))
