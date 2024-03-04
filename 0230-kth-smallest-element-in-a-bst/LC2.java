/*
Approach 2: Iterative Inorder Traversal
The above recursion could be converted into iteration, with the help of stack. This way one could speed up the solution because there is no need to build the entire inorder traversal, and one could stop after the kth element.
*/
class Solution {
  public int kthSmallest(TreeNode root, int k) {
    LinkedList<TreeNode> stack = new LinkedList<>();

    while (true) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      if (--k == 0) return root.val;
      root = root.right;
    }
  }
}

/*
Complexity Analysis
Time complexity: O(H+k)O(H + k)O(H+k), where HHH is a tree height. This complexity is defined by the stack, which contains at least H+kH + kH+k elements, since before starting to pop out one has to go down to a leaf. This results in O(log⁡N+k)O(\log N + k)O(logN+k) for the balanced tree and O(N+k)O(N + k)O(N+k) for a completely unbalanced tree with all the nodes in the left subtree.

Space complexity: O(H)O(H)O(H) to keep the stack, where HHH is a tree height. That makes O(N)O(N)O(N) in the worst case of the skewed tree, and O(log⁡N)O(\log N)O(logN) in the average case of the balanced tree.
*/
