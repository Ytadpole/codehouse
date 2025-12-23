package pers.yangs.leetcode.t101;

import pers.yangs.leetcode.common.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @className: Solution
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/21
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().getKth(12, 15, 2));
    }

    public int widthOfBinaryTree(TreeNode root) {

        return dfs(root, 0, 1);

    }

    public int dfs(TreeNode node, int high, int index) {

        if (null == node) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return index - ((int)Math.pow(2 , high));
        }

        return Math.max(dfs(node.left, high + 1, 2 * index), dfs(node.right, high + 1, 2 * index + 1));


    }

    Map<Integer, Integer> dp = new HashMap<>();

    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> sortMap = new HashMap<>();
        for (int i = lo; i <= hi; i++) {
            int sort = getSort(i);
            sortMap.put(i, sort);
        }
        List<Map.Entry<Integer, Integer>> tmp = sortMap.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (!Objects.equals(o1.getValue(), o2.getValue())) {
                        return o1.getValue() - o2.getValue();
                    } else {
                        return o1.getKey() - o2.getKey();
                    }
                }).toList();
        return tmp.get(k - 1).getKey();
    }

    private int getSort(int num) {
        int c = 0;
        List<Integer> list = new ArrayList<>();
        while (num != 1) {
            if (null != dp.get(num)) {
                c = c + dp.get(num);
                break;
            }
            list.add(num);

            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = 3 * num + 1;
            }
            c++;
        }
        for (int i = 0; i < list.size(); i++) {
            Integer stepNum = list.get(i);
            dp.put(stepNum, c - i);
        }
        return c;
    }

}
