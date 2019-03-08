package com.jinaiya.tutorials.algorithm;

import java.util.regex.Pattern;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 * <p>
 * Input: "race a car"
 * Output: false
 *
 * @author Jin
 * @date 2019/3/7
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i<s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        if (sb.length() % 2 == 1) {
            return
                    new StringBuilder(sb.substring(0, sb.length() / 2)).reverse().toString().
                            equalsIgnoreCase(sb.substring(sb.length() / 2 + 1, sb.length()));
        } else {
            return
                    new StringBuilder(sb.substring(0, sb.length() / 2)).reverse().toString().
                            equalsIgnoreCase(sb.substring(sb.length() / 2, sb.length()));
        }
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
    }
}
