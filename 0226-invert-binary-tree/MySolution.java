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

//Use Preorder Traversal
class Solution {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root; // Always root is returned
    }

    public void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left; // swap left, right
        node.left = node.right;
        node.right = temp;

        invert(node.left); // traverse left
        invert(node.right); // traverse right

    }
}
