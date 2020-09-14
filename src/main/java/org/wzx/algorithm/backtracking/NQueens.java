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
 *
 * @author shawn
 * @create 2020/9/12
 * @since 1.0.0
 */
public class NQueens {

    private static final List<List<String>> boards = new ArrayList<>();

    public static void main(String[] args) {
        int n = 8;
        solveNQueens(n);
        System.out.println(boards.size());
        for (List<String> board : boards) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println("---------------------------------------");
        }
    }

    static void solveNQueens(int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            board.add(new String(chars));
        }

        // 递归回溯
        backtrack(board, 0);
    }

    static void backtrack(List<String> board, int row) {
        // 触发结束条件
        if (row == board.size()) {
            boards.add(new ArrayList<>(board));
            return;
        }

        for (int col = 0; col < board.size(); col++) {
            if (!isValid(board, row, col)) {
                continue;
            }

            // 做选择
            char[] array = board.get(row).toCharArray();
            array[col] = 'Q';
            board.set(row, new String(array));

            // 进入下一行决策
            backtrack(board, row + 1);

            // 撤销选择
            array[col] = '.';
            board.set(row, new String(array));
        }
    }

    static boolean isValid(List<String> board, int row, int col) {
        // 同一列上方
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        // 左上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        // 右上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.size(); i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }
}
