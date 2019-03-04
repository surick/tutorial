package com.jinaiya.tutorials.algorithm;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * @author Jin
 * @date 2019/3/4
 */
public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[x] = nums[i];
                x++;
            }
        }
        while (x < nums.length) {
            nums[x++] = 0;
        }
    }


    public static void main(String[] args) {
        moveZeroes(new int[]{0,1});
    }
}
