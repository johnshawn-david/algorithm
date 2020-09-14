package org.wzx.algorithm.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>给定一个二叉树，找出其最小深度。
 * <p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>说明: 叶子节点是指没有子节点的节点。
 * <p>
 * <p>来源：力扣（LeetCode）
 * <p>链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shawn
 * @create 2020/9/14
 * @since 1.0.0
 */
public class MinimumDepthOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Integer[] binaryTreeArray = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = new TreeNode(3);
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        for(int i = 0; i < 3; i++) {
            TreeNode parentNode = treeNodes.get(i);
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            if(leftIndex < binaryTreeArray.length && binaryTreeArray[leftIndex] != null) {
                parentNode.left = new TreeNode(binaryTreeArray[leftIndex]);
                treeNodes.add(parentNode.left);
            }

            if(rightIndex < binaryTreeArray.length && binaryTreeArray[rightIndex] != null) {
                parentNode.right = new TreeNode(binaryTreeArray[rightIndex]);
                treeNodes.add(parentNode.right);
            }
        }

        System.out.println(minDepth(root));
    }

    /**
     *
     * @param root 二叉树根节点
     * @return 最小深度
     */
    static int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();

            // 遍历同一层
            for(int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                // 判断是否到达终点
                if(cur.left == null && cur.right == null) {
                    return depth;
                }

                // 将相邻节点加入到队列中
                if(cur.left != null) {
                    q.offer(cur.left);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                }
            }

            depth++;
        }

        return depth;
    }
}
