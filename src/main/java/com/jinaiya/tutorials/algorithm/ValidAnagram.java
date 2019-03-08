package com.jinaiya.tutorials.algorithm;

import java.util.HashMap;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * <p>
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 *
 * @author Jin
 * @date 2019/3/7
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
            alphabet[t.charAt(i) - 'a']--;
        }
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        isAnagram("a", "b");
    }
}
