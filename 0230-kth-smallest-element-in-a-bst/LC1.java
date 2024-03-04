/*
To solve the problem, one could use the property of BST: inorder traversal of BST is an array sorted in ascending order.

Approach 1: Recursive Inorder Traversal
It's a very straightforward approach with O(N) time complexity. The idea is to build an inorder traversal of BST which is an array sorted in the ascending order. Now the answer is the k - 1th element of this array.
*/
class Solution {
  public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
    if (root == null) return arr;
    inorder(root.left, arr);
    arr.add(root.val);
    inorder(root.right, arr);
    return arr;
  }

  public int kthSmallest(TreeNode root, int k) {
    ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
    return nums.get(k - 1);
  }
}
/*
Complexity Analysis

Time complexity : O(N)O(N)O(N) to build a traversal.
Space complexity : O(N)O(N)O(N) to keep an inorder traversal.
*/
