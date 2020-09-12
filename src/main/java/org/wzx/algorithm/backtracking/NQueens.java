package org.wzx.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后
 * <p>n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * <p>每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * <p>皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * <p>来源：力扣（LeetCode）
 * <p>链接：https://leetcode-cn.com/problems/n-queens
 * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author shawn
 * @create 2020/9/12
 * @since 1.0.0
 */
public class NQueens {

    private List<List<String>> boards = new ArrayList<>();

    public static void main(String[] args) {
        int n = 8;

    }

    static void solveNQueens(int n) {
        List<String> board = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            board.add(new String(chars));
        }

        // 递归回溯
        backtrack(board, 0);
    }

    static void backtrack(List<String> board, int row) {
        // 触发结束条件

    }
}
