package com.example.study.leetcode;

public class CalculateBinaryTreeDepth {
    /**
     * 计算给定二叉树的最大深度。
     *
     * @param root 二叉树的根节点
     * @return 二叉树的最大深度
     * 二叉树的最大深度是指从根节点到最远叶子节点的最长路径上的节点数
     */
    public int maxDepth(TreeNode root) {
        // 如果当前节点为空，则深度为0
        if (root == null) {
            return 0;
        }
        // 递归计算左子树的最大深度
        int left = maxDepth(root.left);
        // 递归计算右子树的最大深度
        int right = maxDepth(root.right);
        // 返回左子树和右子树深度的较大值加1，即为当前节点的深度
        return Math.max(left, right) + 1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
