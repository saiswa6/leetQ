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

//Refer Kunal Pattern Diagram
// Check if current node has a left child. If yes, find the rightmost element of it. Attach the current right to it. Now, convert the left child of current to right child of current and make left of current as NULL.

class Solution {
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while(current != null) {
            if(current.left != null) {
                TreeNode temp = current.left;
                while(temp.right != null) {
                    temp = temp.right;
                }
                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }

    }
}
