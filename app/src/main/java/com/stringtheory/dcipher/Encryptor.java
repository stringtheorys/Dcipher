package com.stringtheory.dcipher;

import java.util.ArrayList;
import java.util.List;

public class Encryptor {

    private Encryptor() {
    }

    private static List<Integer> stringToIntegerList(String text) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            temp.add((int) text.charAt(i));
        }
        return temp;
    }

    /*
    A shift cipher is a type of substitution cipher where all the letters are
    replaced by letters further down the alphabet/ASCII table. Our cipher uses
    ASCII codes between 32 and 126.
     */
    public static String encryptShift(String plainText, int degree) {
        List<Integer> convert = stringToIntegerList(plainText);
        for (int i = 0; i < convert.size(); i++) {
            convert.set(i, ((convert.get(i) - 32 + degree) % 94) + 32);
        }
        StringBuilder sb = new StringBuilder();
        for (int num : convert) {
            sb.append((char) num);
        }
        return sb.toString();
    }

    public static String decryptShift(String cipherText, int degree) {
        degree = degree % 94;
        List<Integer> convert = stringToIntegerList(cipherText);
        for (int i = 0; i < convert.size(); i++) {
            int temp = convert.get(i) - degree;
            if (temp < 32) {
                temp = 126 - 32 + temp;
            }
            convert.set(i, temp);
        }
        StringBuilder sb = new StringBuilder();
        for (int num : convert) {
            sb.append((char) num);
        }
        return sb.toString();
    }

    private static int calculateRow(int rows, int column) {
        int temp = column % (2 * rows - 2);
        if (temp < rows) {
            return temp;
        } else {
            return ((2 * rows) - temp) - 2;
        }
    }

    /*
    A railfence cipher is a type of transposition cipher that encodes text by
    mapping it in a zig-zag onto a table, then taking the length-ways output.
     */
    public static String encryptRailFence(String plainText, int rows) {
        char[][] grid = new char[rows][plainText.length()];
        for (int column = 0; column < plainText.length(); column++) {
            grid[calculateRow(rows, column)][column] = plainText.charAt(column);
        }
        StringBuilder sb = new StringBuilder();
        for (char[] y : grid) {
            for (char x : y) {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    public static String decryptRailFence(String cipherText, int rows) {
        char[][] grid = new char[rows][cipherText.length()];
        for (int column = 0; column < cipherText.length(); column++) {
            grid[calculateRow(rows, column)][column] = ',';
        }
        int temp = 0;
        for (char[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != '\u0000') {
                    row[i] = cipherText.charAt(temp);
                    temp++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid[0].length; i++) {
            for (char[] row : grid) {
                sb.append(row[i]);
            }
        }
        return sb.toString();
    }

    /*
    The Vernam cipher is a cipher that uses a one-time keyword with an XOR
    applied to the plaintext.
     */
    public static String encryptVernam(String plainText, String keyword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            int numberValue = (int) plainText.charAt(i) ^ (int) keyword.charAt(i);
            char encryptedTextValue = ((char) (numberValue));
            sb.append(encryptedTextValue);
        }
        return sb.toString();
    }

    public static String decryptVernam(String cipherText, String keyword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyword.length(); i++) {
            char arrayValue = cipherText.charAt(i);
            int asciiArrayValue = (int) arrayValue;
            char next = (char) (asciiArrayValue ^ (int) keyword.charAt(i));
            sb.append(next);
        }
        return sb.toString();
    }

    private static String getFullKey(String text, String key) {
        if (text.length() < key.length()) {
            return key.substring(0, text.length());
        } else if (text.length() > key.length()) {
            int endLength = text.length() % key.length();
            int repeats = text.length() / key.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < repeats; i++) {
                sb.append(key);
            }
            sb.append(key.substring(0, endLength));
            return sb.toString();
        } else {
            return key;
        }
    }

    /*
    The Vigenere cipher is a type of substitution cipher that encrypts using a
    series of different shift ciphers based on the letters of a keyword.
     */
    public static String encryptVigenere(String plainText, String keyword) {
        List<Integer> cipherKeys = stringToIntegerList(getFullKey(plainText, keyword));
        for (int i = 0; i < cipherKeys.size(); i++) {
            cipherKeys.set(i, cipherKeys.get(i) - 32);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            sb.append(encryptShift(Character.toString(plainText.charAt(i)), cipherKeys.get(i)));
        }
        return sb.toString();
    }

    public static String decryptVigenere(String cipherText, String keyword) {
        List<Integer> cipherKeys = stringToIntegerList(getFullKey(cipherText, keyword));
        for (int i = 0; i < cipherKeys.size(); i++) {
            cipherKeys.set(i, -(cipherKeys.get(i) - 32));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            sb.append(encryptShift(Character.toString(cipherText.charAt(i)), cipherKeys.get(i)));
        }
        return sb.toString();
    }

}
