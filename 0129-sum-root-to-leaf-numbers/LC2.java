/*
Approach 2: Recursive Preorder Traversal.
Iterative approach 1 could be converted into recursive one.
- Recursive preorder traversal is extremely simple: follow Root->Left->Right direction, i.e. do all the business with the node (= update the current number and root-to-leaf sum), and then do the recursive calls for the left and right child nodes.
- P.S.
Here is the difference between preorder and the other DFS recursive traversals. In the following figure, the nodes are numerated in the order you visit them, please follow 1-2-3-4-5 to compare different DFS strategies implemented as recursion.
*/
class Solution {
  int rootToLeaf = 0;

  public void preorder(TreeNode r, int currNumber) {
    if (r != null) {
      currNumber = currNumber * 10 + r.val;
      // if it's a leaf, update root-to-leaf sum
      if (r.left == null && r.right == null) {
        rootToLeaf += currNumber;
      }
      preorder(r.left, currNumber);
      preorder(r.right, currNumber) ;
    }
  }

  public int sumNumbers(TreeNode root) {
    preorder(root, 0);
    return rootToLeaf;
  }
}

/*
Complexity Analysis
Time complexity: O(N) since one has to visit each node.
Space complexity: up to O(H) to keep the recursion stack, where HHH is a tree height.
*/
