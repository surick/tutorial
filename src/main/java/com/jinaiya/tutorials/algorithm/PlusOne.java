package com.jinaiya.tutorials.algorithm;

import java.util.Arrays;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * @author Jin
 * @date 2019/3/4
 */
public class PlusOne {

    public static int[] plusOne(int[] digits) {
//        int[] digits = {4, 3, 2, 1};
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }

    public static void main(String[] args) {
        plusOne(new int[]{4, 3, 2, 1});
    }
}
