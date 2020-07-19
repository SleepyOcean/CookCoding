package com.sleepy.algorithm.code;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 剑指Offer刷题集
 *
 * @author gehoubao
 * @create 2020-07-17 16:05
 **/
public class PointToOffer {
    public static void main(String[] args) {
        test06();
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *  
     * 限制：
     * 0 <= 链表长度 <= 10000
     */
    private static void test06() {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(3);
        head.next = next;
        next.next = new ListNode(2);
        int[] result = reversePrint(head);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] reversePrint(ListNode head) {
        Stack<Integer> list = new Stack<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] result = new int[list.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = list.pop();
        }
        return result;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *  
     * 限制：
     * 0 <= s 的长度 <= 10000
     */
    private static void test05() {
        System.out.print(replaceSpace("We are happy."));
    }

    private static String replaceSpace(String s) {
        return s.replaceAll("\\s", "%20");
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     * <p>
     * 限制：
     * 0 <= n <= 1000
     * 0 <= m <= 1000
     */
    private static void test04() {
        System.out.println(findNumberIn2DArray(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 20));
    }

    private static boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > 0 && matrix[i][0] <= target && matrix[i][matrix[i].length - 1] >= target) {
                int[] row = matrix[i];
                int cLength = row.length;

                // 二分查找
                int left = 0;
                int right = cLength - 1;

                while (left <= right) {
                    int mid = (right + left) / 2;
                    if (row[mid] == target)
                        return true;
                    else if (row[mid] < target)
                        left = mid + 1;
                    else if (row[mid] > target)
                        right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 找出数组中重复的数字。
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     *  
     * 限制：
     * <p>
     * 2 <= n <= 100000
     */
    private static void test03() {
        System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }

    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) return nums[i];
        }
        return 0;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}