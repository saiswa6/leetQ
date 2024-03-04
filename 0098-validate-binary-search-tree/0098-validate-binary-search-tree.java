/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidHelper(root, null, null);
    }

    public boolean isValidHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if ((min != null && min >= node.val) || (max != null && max <= node.val)) {
            return false;
        }

        return (isValidHelper(node.left, min, node.val) && isValidHelper(node.right, node.val, max));
    }

    /*
     * public boolean isValidBST(TreeNode root) {
     * return isValidHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
     * }
     * 
     * public boolean isValidHelper(TreeNode node, int min, int max) {
     * if (node == null) {
     * return true;
     * }
     * if (!(min < node.val && node.val < max)) {
     * return false;
     * }
     * 
     * return isValidHelper(node.left, min, node.val) && isValidHelper(node.right,
     * node.val, max);
     * }
     */
}