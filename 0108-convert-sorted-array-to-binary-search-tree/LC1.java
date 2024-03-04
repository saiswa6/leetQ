/*
- Construct BST from Inorder Traversal: Why the Solution is Not Unique
  It's known that inorder traversal of BST is an array sorted in the ascending order.
  Having the sorted array as an input, we could rewrite the problem as Construct Binary Search Tree from Inorder Traversal.
- Does this problem have a unique solution, i.e. could inorder traversal be used as a unique identifier to encore/decode BST? The answer is no.
- Here is the funny thing about BST. Inorder traversal is not a unique identifier of BST. At the same time, both preorder and postorder traversals are unique identifiers of BST. From these traversals one could restore the inorder one:
   inorder = sorted(postorder) = sorted(preorder), and inorder + postorder or inorder + preorder are both unique identifiers of whatever binary tree.

- So, the problem "sorted array -> BST" has multiple solutions.
- Here we have an additional condition: the tree should be height-balanced, i.e. the depths of the two subtrees of every node never differ by more than 1.
Does it make the solution to be unique? Still no.
- One could choose the left middle element, or the right middle one, and both choices will lead to different height-balanced BSTs. Let's see that in practice: in Approach 1 we will always pick up the left middle element and in Approach 2 - the right middle one. That will generate different BSTs but both solutions will be accepted.
===========================================

Approach 1: Preorder Traversal: Always Choose Left Middle Node as a Root
Algorithm
- Implement helper function helper(left, right), which constructs BST from nums elements between indexes left and right:
   - If left > right, then there is no elements available for that subtree. Return None.
   - Pick left middle element: p = (left + right) // 2.
   - Initiate the root: root = TreeNode(nums[p]).
   - Compute recursively left and right subtrees: root.left = helper(left, p - 1), root.right = helper(p + 1, right).
- Return helper(0, len(nums) - 1).
*/

class Solution {
    int[] nums;

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // always choose left middle node as a root
        int p = (left + right) / 2;

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

Time complexity: O(N)O(N)O(N) since we visit each node exactly once.

Space complexity: O(log⁡N)O(\log N)O(logN).

The recursion stack requires O(log⁡N)O(\log N)O(logN) space because the tree is height-balanced. Note that the O(N)O(N)O(N) space used to store the output does not count as auxiliary space, so it is not included in the space complexity.
  */
