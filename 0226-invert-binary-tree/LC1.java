/*
Approach 1: Recursive
This is a classic tree problem that is best-suited for a recursive approach.

Algorithm
The inverse of an empty tree is the empty tree. The inverse of a tree with root r, and subtrees right and left, is a tree with root rrr, whose right subtree is the inverse of left, and whose left subtree is the inverse of right.
*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}
/*
Complexity Analysis
Since each node in the tree is visited only once, the time complexity is O(n), where nnn is the number of nodes in the tree. We cannot do better than that, since at the very least we have to visit each node to invert it.

Because of recursion, O(h) function calls will be placed on the stack in the worst case, where hhh is the height of the tree. Because h∈O(n), the space complexity is O(n).
  */
