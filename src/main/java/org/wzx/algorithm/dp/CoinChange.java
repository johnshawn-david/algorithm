package org.wzx.algorithm.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>零钱兑换
 * <p>
 * <p>给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1
 * <p>
 * <p>来源：力扣（LeetCode）
 * <p>链接：https://leetcode-cn.com/problems/coin-change
 * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shawn
 * @create 2020/9/11
 * @since 1.0.0
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = new int[]{3, 5};
        int amount = 13;

        System.out.println(coinChangeWithMemo(coins, amount));
        System.out.println(coinChangeBottomUp(coins, amount));
    }

    /**
     * 备忘录方式零钱兑换
     *
     * @param coins  不同面额的硬币
     * @param amount 总金额
     * @return 凑成总金额所需要的最少的硬币个数
     */
    static int coinChangeWithMemo(int[] coins, int amount) {
        Map<Integer, CoinChangeNode> memoMap = new HashMap<>();
        CoinChangeNode coinChangeNode = dpWithMemo(memoMap, coins, amount);
        if (coinChangeNode == null || coinChangeNode.curCoin <= 0) {
            return -1;
        }

        System.out.println(getCoinCollection(coinChangeNode));
        return coinChangeNode.coinAmount;
    }

    static CoinChangeNode dpWithMemo(Map<Integer, CoinChangeNode> memoMap, int[] coins, int amount) {
        if (amount == 0) {
            return new CoinChangeNode(0);
        }

        if (amount < 0) {
            return null;
        }

        if (memoMap.containsKey(amount)) {
            return memoMap.get(amount);
        }

        int res = Integer.MAX_VALUE;
        CoinChangeNode coinChangeNode = new CoinChangeNode(-1);
        for (int coin : coins) {
            CoinChangeNode subCoinChangeNode = dpWithMemo(memoMap, coins, amount - coin);
            if (subCoinChangeNode == null || subCoinChangeNode.curCoin < 0) {
                continue;
            }

            if (subCoinChangeNode.coinAmount + 1 < res) {
                res = subCoinChangeNode.coinAmount + 1;
                coinChangeNode = new CoinChangeNode(coin);
                coinChangeNode.coinAmount = res;
                coinChangeNode.subCoinNode = subCoinChangeNode;
            }
        }

        memoMap.put(amount, coinChangeNode);
        return coinChangeNode;
    }

    /**
     * 由底向上迭代方式零钱兑换
     *
     * @param coins  不同面额的硬币
     * @param amount 总金额
     * @return 凑成总金额所需要的最少的硬币个数
     */
    static int coinChangeBottomUp(int[] coins, int amount) {
        CoinChangeNode coinChangeNode = dpBottomUp( coins, amount);
        if (coinChangeNode == null || coinChangeNode.curCoin <= 0) {
            return -1;
        }

        System.out.println(getCoinCollection(coinChangeNode));
        return coinChangeNode.coinAmount;
    }

    static CoinChangeNode dpBottomUp(int[] coins, int amount) {
        Map<Integer, CoinChangeNode> dpMap = new HashMap<>();
        // base case
        dpMap.put(0, new CoinChangeNode(0, 0));
        for (int i = 1; i <= amount; i++) {
            CoinChangeNode coinChangeNode = new CoinChangeNode(-1, Integer.MAX_VALUE);
            dpMap.put(i, coinChangeNode);
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }

                CoinChangeNode subCoinChangeNode = dpMap.get(i - coin);
                if (subCoinChangeNode.curCoin >= 0 && subCoinChangeNode.coinAmount + 1 < coinChangeNode.coinAmount) {
                    coinChangeNode = new CoinChangeNode(coin);
                    coinChangeNode.coinAmount = subCoinChangeNode.coinAmount + 1;
                    coinChangeNode.subCoinNode = subCoinChangeNode;
                    dpMap.put(i, coinChangeNode);
                }
            }
        }

        return dpMap.get(amount);
    }


    static class CoinChangeNode {
        private final int curCoin;

        private int coinAmount;

        private CoinChangeNode subCoinNode;

        public CoinChangeNode(int curCoin) {
            this.curCoin = curCoin;
        }

        public CoinChangeNode(int curCoin, int coinAmount) {
            this.curCoin = curCoin;
            this.coinAmount = coinAmount;
        }
    }

    static List<Integer> getCoinCollection(CoinChangeNode coinChangeNode) {
        List<Integer> collection = new ArrayList<>();
        if (coinChangeNode.curCoin > 0) {
            collection.add(coinChangeNode.curCoin);
            collection.addAll(getCoinCollection(coinChangeNode.subCoinNode));
        }

        return collection;
    }
}
