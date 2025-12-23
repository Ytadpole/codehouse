package pers.yangs.leetcode.common;

/**
 * @className: TreeNode
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/21
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
