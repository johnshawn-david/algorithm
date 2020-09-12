package org.wzx.algorithm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>全排列</p>
 * <p>给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * <p>来源：力扣（LeetCode）
 * <p>链接：https://leetcode-cn.com/problems/permutations
 * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author shawn
 * @create 2020/9/12
 * @since 1.0.0
 */
public class Permutations {

    private static List<LinkedList<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        permute(nums);
        for(LinkedList<Integer> track : res) {
            System.out.println(track);
        }
    }

    static void permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
    }

    static void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if(nums.length == track.size()) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num)) {
                continue;
            }

            // 做选择
            track.add(num);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}
