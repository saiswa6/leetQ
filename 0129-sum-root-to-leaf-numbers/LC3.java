/*
Approach 3: Morris Preorder Traversal.
- We discussed already iterative and recursive preorder traversals, which both have great time complexity though use up to O(H)\mathcal{O}(H)O(H) to keep the stack. We could trade in performance to save space.
- The idea of Morris preorder traversal is simple: to use no space but to traverse the tree.

- How that could be even possible? At each node one has to decide where to go: to the left or to the right, traverse the left subtree, or traverse the right subtree. How one could know that the left subtree is already done if no additional memory is allowed?
- The idea of Morris algorithm is to set the temporary link between the node and its
predecessor: predecessor.right = root. So one starts from the node, computes its predecessor, and verifies if the link is present.
   - There is no link? Set it and go to the left subtree.
   - There is a link? Break it and go to the right subtree.

There is one small issue to deal with what if there is no left child, i.e. there is no left subtree? Then go straight to the right subtree.
*/
class Solution {
  public int sumNumbers(TreeNode root) {
    int rootToLeaf = 0, currNumber = 0;
    int steps;
    TreeNode predecessor;

    while (root != null) {
      // If there is a left child,
      // then compute the predecessor.
      // If there is no link predecessor.right = root --> set it.
      // If there is a link predecessor.right = root --> break it.
      if (root.left != null) {
        // Predecessor node is one step to the left
        // and then to the right till you can.
        predecessor = root.left;
        steps = 1;
        while (predecessor.right != null && predecessor.right != root) {
          predecessor = predecessor.right;
          ++steps;
        }

        // Set link predecessor.right = root
        // and go to explore the left subtree
        if (predecessor.right == null) {
          currNumber = currNumber * 10 + root.val;
          predecessor.right = root;
          root = root.left;
        }
        // Break the link predecessor.right = root
        // Once the link is broken,
        // it's time to change subtree and go to the right
        else {
          // If you're on the leaf, update the sum
          if (predecessor.left == null) {
            rootToLeaf += currNumber;
          }
          // This part of tree is explored, backtrack
          for(int i = 0; i < steps; ++i) {
            currNumber /= 10;
          }
          predecessor.right = null;
          root = root.right;
        }
      }

      // If there is no left child
      // then just go right.
      else {
        currNumber = currNumber * 10 + root.val;
        // if you're on the leaf, update the sum
        if (root.right == null) {
          rootToLeaf += currNumber;
        }
        root = root.right;
      }
    }
    return rootToLeaf;
  }
}

/*
Complexity Analysis
Time complexity: O(N)
Space complexity: O(1)
*/
