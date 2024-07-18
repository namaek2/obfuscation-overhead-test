package org.sample;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;


import org.openjdk.jmh.annotations.Benchmark;


@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class forTest {
    private final String ROTOR1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    private static final String ROTOR2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    private static final String ROTOR3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";

    // 반사판 구성
    private static final String REFLECTOR = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

    // 알파벳 매핑
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // 로터 위치
    private int rotor1Pos = 0;
    private int rotor2Pos = 0;
    private int rotor3Pos = 0;

    private static String rotateString(String s, int amount) {
        return s.substring(amount) + s.substring(0, amount);
    }

    // 문자열 암호화 및 복호화 함수

    public String encryptDecrypt(String message) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (ALPHABET.indexOf(c) >= 0) {
                result.append(encryptDecryptChar(c));
            } else {
                result.append(c); // 알파벳 이외의 문자는 그대로 둠
            }
        }
        return result.toString();
    }

    private void rotateRotors() {
        rotor1Pos = (rotor1Pos + 1) % 26;
        if (rotor1Pos == 0) {
            rotor2Pos = (rotor2Pos + 1) % 26;
            if (rotor2Pos == 0) {
                rotor3Pos = (rotor3Pos + 1) % 26;
            }
        }
    }

    private char mapChar(char c, String mapping) {
        return mapping.charAt(ALPHABET.indexOf(c));
    }

    // 문자 암호화 및 복호화 함수

    public char encryptDecryptChar(char c) {
        // 회전한 로터 적용
        char step1 = mapChar(c, rotateString(ROTOR1, rotor1Pos));
        char step2 = mapChar(step1, rotateString(ROTOR2, rotor2Pos));
        char step3 = mapChar(step2, rotateString(ROTOR3, rotor3Pos));

        // 반사판 적용
        char reflected = mapChar(step3, REFLECTOR);

        // 역방향 로터 적용
        char step4 = ALPHABET.charAt(rotateString(ROTOR3, rotor3Pos).indexOf(reflected));
        char step5 = ALPHABET.charAt(rotateString(ROTOR2, rotor2Pos).indexOf(step4));
        char step6 = ALPHABET.charAt(rotateString(ROTOR1, rotor1Pos).indexOf(step5));

        // 로터 회전
        rotateRotors();

        return step6;
    }

    @Benchmark
    public static void test1() {
        forTest enigma = new forTest();

        // 예제 메시지
        String message = "HELLOENIGMA";

        // 암호화
        String encrypted = enigma.encryptDecrypt(message);
        System.out.println("Encrypted: " + encrypted);

        // 암호화된 메시지를 복호화 (로터 초기화 필요)
        enigma = new forTest(); // 새로운 EnigmaMachine 객체로 로터 위치 초기화
        String decrypted = enigma.encryptDecrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
