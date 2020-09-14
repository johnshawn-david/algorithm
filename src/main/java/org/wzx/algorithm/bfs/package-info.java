/**
 * <p><em>BFS（广度优先搜索）问题的本质就是让你在一幅「图」中找到从起点 start 到终点 target 的最近距离，这个例子听起来很枯燥，但是 BFS 算法问题其实都是在干这个事儿，</em>把枯燥的本质搞清楚了，再去欣赏各种问题的包装才能胸有成竹嘛</p>
 *
 * <p>这个广义的描述可以有各种变体，比如走迷宫，有的格子是围墙不能走，从起点到终点的最短距离是多少？如果这个迷宫带「传送门」可以瞬间传送呢？
 *
 * <p>再比如说两个单词，要求你通过某些替换，把其中一个变成另一个，每次只能替换一个字符，最少要替换几次？
 *
 * <p>再比如说连连看游戏，两个方块消除的条件不仅仅是图案相同，还得保证两个方块之间的最短连线不能多于两个拐点。你玩连连看，点击两个坐标，游戏是如何判断它俩的最短连线有几个拐点的？
 *
 * <p>BFS算法框架：</p>
 * <pre>{@code
 * // 计算从起点 start 到终点 target 的最近距离
 * int BFS(Node start, Node target) {
 *     Queue<Node> q; // 核心数据结构
 *     Set<Node> visited; // 避免走回头路
 *
 *     q.offer(start); // 将起点加入队列
 *     visited.add(start);
 *     int step = 0; // 记录扩散的步数
 *
 *     while (q not empty) {
 *         int sz = q.size();
 *         // 将当前队列中的所有节点向四周扩散
 *         for (int i = 0; i < sz; i++) {
 *             Node cur = q.poll();
 *             // 划重点：这里判断是否到达终点
 *             if (cur is target)
 *                 return step;
 *             // 将 cur 的相邻节点加入队列
 *             for (Node x : cur.adj())
 *                 if (x not in visited) {
 *                     q.offer(x);
 *                     visited.add(x);
 *                 }
 *         }
 *         // 划重点：更新步数在这里
 *         step++;
 *     }
 * }
 *
 * }</pre>
 *
 * <p>BFS 的核心数据结构；cur.adj() 泛指 cur 相邻的节点，比如说二维数组中，cur 上下左右四面的位置就是相邻节点；visited 的主要作用是防止走回头路，大部分时候都是必须的，但是像一般的二叉树结构，没有子节点到父节点的指针，不会走回头路就不需要 visited</p>
 * <p><a href="https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/bfs-kuang-jia">BFS 算法解题套路框架</a></p>
 * @author shawn
 * @create 2020/9/14
 * @since 1.0.0
 */
package org.wzx.algorithm.bfs;