package org.wzx.algorithm.dp;

import java.util.Scanner;

/**
 * <em>斐波那契数</em>，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * <p>F(0) = 0,   F(1) = 1
 * <p>F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * <p>给定 N，计算 F(N)。
 *
 * <p>
 * <p>来源：力扣（LeetCode）
 * <p>链接：https://leetcode-cn.com/problems/fibonacci-number
 * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shawn
 * @create 2020/9/11
 * @since 1.0.0
 */
public class Feibonacci {

    public static void main(String[] args) {
        // 主要负责接收数据
        Scanner scanner = new Scanner(System.in);
        int res = fibWithDpCompress(scanner.nextInt());
        System.out.println(res);
    }

    /**
     * 带备忘录的递归解法
     *
     * @param n 入参
     * @return 斐波那契数列结果
     */
    static int fibWithMemo(int n) {
        if (n < 1) {
            return 0;
        }

        int[] memo = new int[n + 1];
        return compute(memo, n);
    }

    static int compute(int[] memo, int n) {
        // base case
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = compute(memo, n - 1) + compute(memo, n - 2);
        return memo[n];
    }

    /**
     * 使用动态规划思想自下向上计算
     *
     * @param n 入参
     * @return 斐波那契计算结果
     */
    static int fibWithDp(int n) {
        if (n < 1) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * 使用动态规划思想自下向上计算，可进一步进行<em>状态压缩</em>优化，把空间复杂度降为O(1)
     * @param n 入参
     * @return 斐波那契计算结果
     */
    static int fibWithDpCompress(int n) {
        if (n < 1) {
            return 0;
        }

        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }

        return curr;
    }


}
