/*
Approach 2: Preorder Traversal: Always Choose Right Middle Node as a Root
Algorithm
- Implement helper function helper(left, right), which constructs BST from nums elements between indexes left and right:
   - If left > right, then there are no elements available for that subtree. Return None.
   - Pick the right middle element:
      - p = (left + right) // 2.
      - If left + right is odd, add 1 to p-index.
   - Initiate the root: root = TreeNode(nums[p]).
   - Compute recursively left and right subtrees: root.left = helper(left, p - 1), root.right = helper(p + 1, right).
- Return helper(0, len(nums) - 1).

  */
class Solution {
    int[] nums;

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // always choose right middle node as a root
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) ++p;

        // preorder traversal: node -> left -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length - 1);
    }
}
/*
Complexity Analysis
Time complexity: O(N) since we visit each node exactly once.
Space complexity: O(log⁡N)

The recursion stack requires O(log⁡N) space because the tree is height-balanced. Note that the O(N) space used to store the output does not count as auxiliary space, so it is not included in the space complexity.
  */
