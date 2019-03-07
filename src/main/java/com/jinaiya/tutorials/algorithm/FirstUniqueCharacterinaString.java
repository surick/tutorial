package com.jinaiya.tutorials.algorithm;

import java.util.*;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 *
 * @author Jin
 * @date 2019/3/6
 */
public class FirstUniqueCharacterinaString {

    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if (map.get(s.charAt(i)) != null) {
                    map.remove(s.charAt(i));
                }
            } else {
                map.put(s.charAt(i), i);
                set.add(s.charAt(i));
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }

    public int firstUniqChar1(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        firstUniqChar("leetcode");
    }
}
