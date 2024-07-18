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
Result "org.sample.forTest.test1":
  33.149 ±(99.9%) 7.948 ops/ms [Average]
  (min, avg, max) = (30.829, 33.149, 34.797), stdev = 2.064
  CI (99.9%): [25.201, 41.097] (assumes normal distribution)

# Run complete. Total time: 00:01:43

Benchmark       Mode  Cnt   Score   Error   Units
forTest.test1  thrpt    5  33.149 ± 7.948  ops/ms
```
### Obfuscated
```
Result "org.sample.forObfTest.test1":
  26.867 ±(99.9%) 4.634 ops/ms [Average]
  (min, avg, max) = (25.084, 26.867, 28.174), stdev = 1.204
  CI (99.9%): [22.233, 31.502] (assumes normal distribution)

# Run complete. Total time: 00:01:43

Benchmark          Mode  Cnt   Score   Error   Units
forObfTest.test1  thrpt    5  26.867 ± 4.634  ops/ms
```

