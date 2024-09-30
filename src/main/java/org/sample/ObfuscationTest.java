package org.sample;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;


@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ObfuscationTest {
    private static final int ITERATIONS = 1000000;

    @Benchmark
    public static void mainTest() {
        long startTime = System.nanoTime();

        int result = performComplexCalculations();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // Convert to milliseconds

        System.out.println("Final result: " + result);
        System.out.println("Execution time: " + duration + " ms");
    }

    private static int performComplexCalculations() {
        int sum = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            sum += calculateFactorial(i % 10);
            sum += fibonacci(i % 20);
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    private static int calculateFactorial(int n) {
        if (n <= 1) return 1;
        return n * calculateFactorial(n - 1);
    }

    private static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}