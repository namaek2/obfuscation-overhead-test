# java obfuscation overhead test

## Tools
- obfuscated with JObF : https://github.com/superblaubeere27/obfuscator
- overhead tested woth JMH : https://github.com/openjdk/jmh

## Code inttroduction
- some simple enigma simulation created with GPT...

## Procedure
- create normal simple enigma project
- build jar
- create obfuscated jar with JObF
- decompile obfuscated jar
- copy obfuscated class to JMH test maven project
- test benchmark of original class and obfuscated class

## Result
### Original
```
Result "forObfTest.test1":
35.323 ±(99.9%) 31.673 ops/ms [Average]
  (min, avg, max) = (21.593, 35.323, 43.684), stdev = 8.225
  CI (99.9%): [3.650, 66.996] (assumes normal distribution)

Benchmark       Mode  Cnt   Score    Error   Units
forTest.test1  thrpt    5  35.323 ± 31.673  ops/ms

Process finished with exit code 0
```
### Taint Bomb
```
Result "org.sample.forTest.test1":
  32.105 ±(99.9%) 31.047 ops/ms [Average]
  (min, avg, max) = (23.288, 32.105, 43.017), stdev = 8.063
  CI (99.9%): [1.058, 63.152] (assumes normal distribution)

Benchmark       Mode  Cnt   Score    Error   Units
forTest.test1  thrpt    5  32.105 ± 31.047  ops/ms

Process finished with exit code 0
```

### jObf
```
Result "org.sample.forTest.test1":
  28.294 ±(99.9%) 21.934 ops/ms [Average]
  (min, avg, max) = (22.236, 28.294, 35.932), stdev = 5.696
  CI (99.9%): [6.360, 50.228] (assumes normal distribution)
  
Benchmark       Mode  Cnt   Score    Error   Units
forTest.test1  thrpt    5  28.294 ± 21.934  ops/ms

Process finished with exit code 0
```


