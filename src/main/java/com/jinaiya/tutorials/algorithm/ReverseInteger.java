package com.jinaiya.tutorials.algorithm;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *
 * @author Jin
 * @date 2019/3/5
 */
public class ReverseInteger {
    public static int reverse(int x) {
        char[] tempChar = Integer.toString(x).toCharArray();
        int start = tempChar[0] == '-' ? 1 : 0;
        int end = tempChar.length - 1;
        while (start < end) {
            char swap = tempChar[start];
            tempChar[start] = tempChar[end];
            tempChar[end] = swap;
            start++;
            end--;
        }
        long result = Long.valueOf(String.valueOf(tempChar));
        if(result > Integer.MAX_VALUE) return 0;
        if(result < Integer.MIN_VALUE) return 0;
        return (int) result;
    }

    public int reverse1(int x)
    {
        int result = 0;

        while (x != 0)
        {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
            x = x / 10;
        }

        return result;
    }

    public int reverse2(int x)
    {
        long result =0;
        while(x != 0)
        {
            result = (result*10) + (x%10);
            if(result > Integer.MAX_VALUE) return 0;
            if(result < Integer.MIN_VALUE) return 0;
            x = x/10;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        reverse(-123);
    }
}
