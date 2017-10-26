# prime-times-table

This code is my response for the **Funding Circle Coding Challenge**, which had the following directions.

Write a program that prints out a multiplication table of the first 10 prime number.
- The program must run from the command line and print one table to STDOUT.
- The first row and column of the table should have the 10 primes, with each cell
containing the product of the primes for the corresponding row and column.
Notes
- Consider complexity. How fast does your code run? How does it scale?
- Consider cases where we want N  primes.
- Do not use the Prime class from stdlib (write your own code).
- Write tests. Try to demonstrate TDD/BDD.
- If you’re using external dependencies please specify those dependencies and how to install them.
- Please package your code, OR include running instructions. When you’re done
Put your code on GitHub or email us a zip/tarball.
Thanks!

## Installation

### Using Java
Download precompiled jar file: prime-times-table-0.1.0-SNAPSHOT-standalone.jar

### Using Leinegen
Download and install [Leinegen](https://leiningen.org/#install), clone this repo, then you can use Leinegen to run the clojure code in a REPL or from the commandline.

## Usage

Run from the command line using stand alone jar file which you can grab from the /target/uberjar/ folder.

    $ java -jar prime-times-table-0.1.0-SNAPSHOT-standalone.jar [args]

Using Leinegen, from the cloned repository:

    $ lein run

or with option arguments

    $ lein run 11

## Options

A optional integer number can be passed as an argument to very the number of primes used in the table. The default number is if no argument is given is 10.

### Examples

No arguments produce the a multiplication table for the first 10 primes

```
       2   3   5   7  11  13  17  19  23  29
   2   4   6  10  14  22  26  34  38  46  58
   3   6   9  15  21  33  39  51  57  69  87
   5  10  15  25  35  55  65  85  95 115 145
   7  14  21  35  49  77  91 119 133 161 203
  11  22  33  55  77 121 143 187 209 253 319
  13  26  39  65  91 143 169 221 247 299 377
  17  34  51  85 119 187 221 289 323 391 493
  19  38  57  95 133 209 247 323 361 437 551
  23  46  69 115 161 253 299 391 437 529 667
  29  58  87 145 203 319 377 493 551 667 841
```

### Testing

With Leinegen run
    
    lein test

**Note**: Due to the nature of the program, writing to STDOUT, there is included a macro to set some of the test to write to a temp file and then test the contents of this file. I haven't tested this functionality on all systems which may not have a /tmp/ folder.

### Notes on performance

Due to the nature of the program, to be read from the command line, I opted for a fairly unoptimized prime number generator. This was partially for readibility sake, and partially because it performs well up to fairly large numbers, well beyond what a command line could show nicely. It will stack overflow if given large numbers, such as 10000. It is possible to optimize the function further by starting with a sequence of [2 3 5] and then only testing odd numbers that end in 1 3 7 9 as all other numbers as numbers ending in 2 4 5 6 8 0 known multiples of 2 and 5.

## License

Copyright © 2017 
Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
