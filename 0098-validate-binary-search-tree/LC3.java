/*
Approach 3: Recursive Inorder Traversal
Algorithm
- Left -> Node -> Right order of inorder traversal means for BST that each element should be smaller than the next one.
- Hence the algorithm with O(N) time complexity and O(N) space complexity could be simple:
   - Compute inorder traversal list inorder.
   - Check if each element in inorder is smaller than the next one.

- Do we need to keep the whole inorder traversal list?
- Actually, no. The last added inorder element is enough to ensure at each step that the tree is BST (or not). Hence one could merge both steps into one and reduce the used space.

*/
class Solution {
    // We use Integer instead of int as it supports a null value.
    private Integer prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (prev != null && root.val <= prev) {
            return false;
        }
        prev = root.val;
        return inorder(root.right);
    }
}

/*
Complexity Analysis
Time complexity: O(N) in the worst case when the tree is a BST or the "bad" element is a rightmost leaf.
Space complexity: O(N) for the space on the run-time stack.
  */
