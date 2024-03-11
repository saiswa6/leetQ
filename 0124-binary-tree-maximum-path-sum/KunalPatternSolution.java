/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return ans;
    }

    private int helper(TreeNode node) {
        if(node == null){
            return 0;
        }

        int left = helper(node.left);
        left = Math.max(0, left);
        int right = helper(node.right);
        right = Math.max(0, right);

        ans = Math.max(ans, left + right + node.val);

        return Math.max(left, right) + node.val;
    }
}
