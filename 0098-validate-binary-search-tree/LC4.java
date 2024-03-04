/*
Approach 4: Iterative Inorder Traversal
Alternatively, we could implement the above algorithm iteratively.
  */
class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
    }
}
/*
Complexity Analysis
Time complexity: O(N) in the worst case when the tree is BST or the "bad" element is the rightmost leaf.
Space complexity: O(N) to keep stack.
  */
