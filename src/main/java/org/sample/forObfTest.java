package org.sample;


import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class forObfTest {
    private final String ROTOR1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    private static final String ROTOR2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    private static final String ROTOR3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    private static final String REFLECTOR = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int rotor1Pos = 0;
    private int rotor2Pos = 0;
    private int rotor3Pos = 0;

    private static String rotateString(String s, int amount) {
        return s.substring(amount) + s.substring(0, amount);
    }

    public String encryptDecrypt(String message) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (ALPHABET.indexOf(c) >= 0) {
                result.append(this.encryptDecryptChar(c));
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }

    private void rotateRotors() {
        this.rotor1Pos = (this.rotor1Pos + 1) % 26;
        if (this.rotor1Pos == 0) {
            this.rotor2Pos = (this.rotor2Pos + 1) % 26;
            if (this.rotor2Pos == 0) {
                this.rotor3Pos = (this.rotor3Pos + 1) % 26;
            }
        }
    }

    private char mapChar(char c, String mapping) {
        return mapping.charAt(ALPHABET.indexOf(c));
    }

    public char encryptDecryptChar(char c) {
        char step1 = this.mapChar(c, forObfTest.rotateString("EKMFLGDQVZNTOWYHXUSPAIBRCJ", this.rotor1Pos));
        char step2 = this.mapChar(step1, forObfTest.rotateString(ROTOR2, this.rotor2Pos));
        char step3 = this.mapChar(step2, forObfTest.rotateString(ROTOR3, this.rotor3Pos));
        char reflected = this.mapChar(step3, REFLECTOR);
        char step4 = ALPHABET.charAt(forObfTest.rotateString(ROTOR3, this.rotor3Pos).indexOf(reflected));
        char step5 = ALPHABET.charAt(forObfTest.rotateString(ROTOR2, this.rotor2Pos).indexOf(step4));
        char step6 = ALPHABET.charAt(forObfTest.rotateString("EKMFLGDQVZNTOWYHXUSPAIBRCJ", this.rotor1Pos).indexOf(step5));
        this.rotateRotors();
        return step6;
    }

    @Benchmark
    public static void test1() {
        forObfTest enigma = new forObfTest();
        String message = "HELLOENIGMA";
        String encrypted = enigma.encryptDecrypt(message);
        System.out.println("Encrypted: " + encrypted);
        enigma = new forObfTest();
        String decrypted = enigma.encryptDecrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}

