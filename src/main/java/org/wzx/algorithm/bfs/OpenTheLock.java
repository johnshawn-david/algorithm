package org.wzx.algorithm.bfs;

import java.util.*;

/**
 * <P>你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * <p>锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * <p>列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * <p>字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 * <p>来源：力扣（LeetCode）
 * <p>链接：https://leetcode-cn.com/problems/open-the-lock
 * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shawn
 * @create 2020/9/14
 * @since 1.0.0
 */
public class OpenTheLock {

    static class OpenLockNode {
        private String value;

        private OpenLockNode parent;

        public OpenLockNode(String value) {
            this.value = value;
        }

        public OpenLockNode(String value, OpenLockNode parent) {
            this.value = value;
            this.parent = parent;
        }

    }

    public static void main(String[] args) {
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        System.out.println(openLockUseDoubleBFS(new HashSet<>(Arrays.asList(deadends)), "0202"));
    }

    static List<String> openLock(Set<String> deadends, String target) {
        Queue<OpenLockNode> queue = new LinkedList<>();
        int step = 0;
        queue.offer(new OpenLockNode("0000"));
        Set<String> visited = new HashSet<>();
        visited.add("0000");

        while (!queue.isEmpty()) {
            int sz = queue.size();
            // 将当前队列中的所有节点向四周扩散
            // 对本层节点进行遍历
            for (int i = 0; i < sz; i++) {
                OpenLockNode cur = queue.poll();
                // 是否到达终点
                if (deadends.contains(cur.value)) {
                    continue;
                }
                if (cur.value.equals(target)) {
                    return resolveOpenLockPath(cur, true);
                }

                // 将节点的下一层节点加入队列
                // 将一个节点的未遍历相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur.value, j);
                    if (!visited.contains(up)) {
                        queue.offer(new OpenLockNode(up, cur));
                        visited.add(up);
                    }

                    String down = minusOne(cur.value, j);
                    if (!visited.contains(down)) {
                        queue.offer(new OpenLockNode(down, cur));
                        visited.add(down);
                    }
                }
            }

            // 更新步数
            step++;
        }

        // 如果穷举完都没找到目标密码，那就是找不到了
        return null;
    }

    /**
     * 使用双向BFS进行优化
     * <p>传统的 BFS 框架就是从起点开始向四周扩散，遇到终点时停止；而双向 BFS 则是从起点和终点同时开始扩散，当两边有交集的时候停止。</p>
     * <p>不过，双向 BFS 也有局限，因为你必须知道终点在哪里。</p>
     *
     * @param deadends 死亡数字
     * @param target   目标密码
     * @return 操作路径
     */
    static List<String> openLockUseDoubleBFS(Set<String> deadends, String target) {
        // 用集合不用队列，可以快速判断元素是否存在
        Set<OpenLockNode> q1 = new HashSet<>();
        Set<OpenLockNode> q2 = new HashSet<>();

        q1.add(new OpenLockNode("0000"));
        q2.add(new OpenLockNode(target));
        Set<String> visited = new HashSet<>();

        boolean positive = true;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<OpenLockNode> tmp = new HashSet<>();

            // 遍历本层
            for (OpenLockNode cur : q1) {
                // 是否到达终点
                if(deadends.contains(cur.value)) {
                    continue;
                }

                for (OpenLockNode node : q2) {
                    if (node.value.equals(cur.value)) {
                        List<String> positivePath;
                        List<String> reversePath;
                        if (positive) {
                            positivePath = resolveOpenLockPath(cur, true);
                            reversePath = resolveOpenLockPath(node, false);

                        } else {
                            positivePath = resolveOpenLockPath(node, true);
                            reversePath = resolveOpenLockPath(cur, false);
                        }
                        List<String> openLockPath = new ArrayList<>(positivePath);
                        if(reversePath.size() > 1) {
                            openLockPath.addAll(reversePath.subList(1, reversePath.size()));
                        }
                        return openLockPath;
                    }
                }

                visited.add(cur.value);

                // 获取下一层 将一个节点的未遍历相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur.value, j);
                    if (!visited.contains(up)) {
                        tmp.add(new OpenLockNode(up, cur));
                    }

                    String down = minusOne(cur.value, j);
                    if (!visited.contains(down)) {
                        tmp.add(new OpenLockNode(down, cur));
                    }
                }

            }

            // temp 相当于 q1
            // 这里交换 q1 q2，下一轮 while 就是扩散 q2 (双向BFS轮流交替搜索)
            q1 = q2;
            q2 = tmp;
            positive = !positive;
        }

        return null;
    }

    /**
     * 将指定位置向下拨动一次
     *
     * @param s 密码锁当前密码
     * @param j 指定位置
     * @return
     */
    static String plusOne(String s, int j) {
        char[] array = s.toCharArray();
        if (array[j] == '9') {
            array[j] = '0';
        } else {
            array[j] = (char) ((int) array[j] + 1);
        }

        return new String(array);
    }

    /**
     * 将指定位置向上拨动一次
     *
     * @param s 密码锁当前密码
     * @param j 指定位置
     * @return
     */
    static String minusOne(String s, int j) {
        char[] array = s.toCharArray();
        if (array[j] == '0') {
            array[j] = '9';
        } else {
            array[j] = (char) ((int) array[j] - 1);
        }

        return new String(array);
    }

    static List<String> resolveOpenLockPath(OpenLockNode openLockNode, boolean reverse) {
        List<String> list = new ArrayList<>();
        list.add(openLockNode.value);
        OpenLockNode cur = openLockNode.parent;
        while (cur != null) {
            list.add(cur.value);
            cur = cur.parent;
        }

        if (reverse) {
            Collections.reverse(list);
        }
        return list;
    }
}
