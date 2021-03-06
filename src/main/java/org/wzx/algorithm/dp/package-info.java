/**
 * <em>首先，动态规划问题的一般形式就是求最值。</em>动态规划其实是运筹学的一种最优化方法，只不过在计算机问题上应用比较多，比如说让你求最长递增子序列呀，最小编辑距离呀等等。
 *
 * <p>既然是要求最值，核心问题是什么呢？<em>求解动态规划的核心问题是穷举。</em>因为要求最值，肯定要把所有可行的答案穷举出来，然后在其中找最值呗。
 *
 * <p>首先，动态规划的穷举有点特别，因为这类问题<em>存在「重叠子问题」</em>，如果暴力穷举的话效率会极其低下，所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算。
 *
 * <p>而且，动态规划问题一定会<em>具备「最优子结构」</em>，才能通过子问题的最值得到原问题的最值。
 *
 * <p>另外，虽然动态规划的核心思想就是穷举求最值，但是问题可以千变万化，穷举所有可行解其实并不是一件容易的事，只有列出<em>正确的「状态转移方程」</em>才能正确地穷举。
 *
 * <p>以上提到的重叠子问题、最优子结构、状态转移方程就是动态规划三要素。具体什么意思等会会举例详解，但是在实际的算法问题中，<em>写出状态转移方程是最困难的，</em>这也就是为什么很多朋友觉得动态规划问题困难的原因，我来提供我研究出来的一个思维框架，辅助你思考状态转移方程：
 *
 * <p><em>明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义。</em>
 *
 * <p><em>千万不要看不起暴力解，动态规划问题最困难的就是写出这个暴力解，即状态转移方程。</em>只要写出暴力解，优化方法无非是用备忘录或者 DP table，再无奥妙可言。
 *
 * <p>dp算法框架</p>
 * <pre>{@code
 * # 初始化 base case
 * dp[0][0][...] = base
 * # 进行状态转移
 * for 状态1 in 状态1的所有取值：
 *     for 状态2 in 状态2的所有取值：
 *         for ...
 *             dp[状态1][状态2][...] = 求最值(选择1，选择2...)
 * }</pre>
 *
 *  <p><a href="https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/dong-tai-gui-hua-xiang-jie-jin-jie">动态规划解题套路框架</a>
 * @author shawn
 * @create 2020/9/11
 * @since 1.0.0
 */
package org.wzx.algorithm.dp;