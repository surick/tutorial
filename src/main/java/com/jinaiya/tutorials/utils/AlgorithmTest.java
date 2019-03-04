package com.jinaiya.tutorials.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Jin
 * @date 2019/3/1
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;
//        Set<Integer> set = new TreeSet<>();
//        Arrays.stream(nums).forEach(item -> set.add(item));
//        int x = 0;
//        for (Integer i : set) {
//            nums[x] = i;
//            ++x;
//        }

//        Arrays.stream(nums).forEach(System.out::println);
//        int i = nums.length > 0 ? 1 : 0;
//        for (int n : nums)
//            if (n > nums[i-1])
//                nums[i++] = n;
//        for (int m = 0; m < nums.length; m++) {
//            System.out.print(nums[m]);
//        }
//        return i;
//        System.out.println(nums.length);
//        System.out.println();
//        k %= nums.length;
//        reverse(nums, 0, nums.length - 1);
//        reverse(nums, 0, k - 1);
//        reverse(nums, k, nums.length - 1);
//        Arrays.stream(nums).forEach(System.out::println);
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (!set.add(n)) {
                set.remove(n);
            }
        }

    }
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
