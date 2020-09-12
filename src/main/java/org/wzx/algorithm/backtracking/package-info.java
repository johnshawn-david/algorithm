/**
 * <em>解决一个回溯问题，实际上就是一个决策树的遍历过程。</em>你只需要思考 3 个问题：
 * <p>1、路径：也就是已经做出的选择。
 * <p>2、选择列表：也就是你当前可以做的选择。</p>
 * <p>3、结束条件：也就是到达决策树底层，无法再做选择的条件。</p>
 * 回溯算法的框架：
 * <pre>{@code
 * result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 * }</pre>
 *
 * 其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
 * <p><a href="https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban">回溯算法解题套路框架</a></p>
 *
 * @author shawn
 * @create 2020/9/12
 * @since 1.0.0
 */
package org.wzx.algorithm.backtracking;