/*
Approach 1: Depth First Search with Branch Pruning
Intuition
- We can do a depth-first traversal and find the depth and parent for each node. Once we know the depth and parent for each node we can easily find out if they are cousins. Let's look at the pseudo-code for this before we try to optimize it a bit.

  // This pseudo-code recursively traverses the tree and
  // records the depth and parent for each node.
  function dfs(node, parentNode = None) {
      if (node != null) {
          depth[node.val] = 1 + depth[parentNode.val]
          parent[node.val] = parentNode
          dfs(node.left, node)
          dfs(node.right, node)
      }
  }
- The above pseudo-code would give us the depth and parent for each node. To find out whether or not x and y are cousins is just one step away.
    // If x and y are at same depth but have different parents.
    depth[x] == depth[y] and parent[x] != parent[y]

- Now let's see if this brute-force recursive approach can be optimized for some scenarios.
   - If Node x or Node y is lying very shallow in the tree, then does it make any sense to iterate down the entire tree?
- In the above example, Node 3 and Node 4 are both cousins and hence at the same depth. What if we find one of the nodes very early on during the traversal?
   - How would that help us?
- The diagram above shows that we encounter Node 3 very early on. This would help us to restrict our search space for the other node i.e. Node 4. For the second node, we do not need to go beyond the depth at which the first node was found, thus saving traversal of the subtree below node 3.
- We can search for the desired nodes in the tree recursively. Whenever either of the given nodes is found, we record its parent and depth.

Algorithm
1. Start traversing the tree from the root node. Look for Node x and Node y.
2. Record the depth when the first node i.e. either of x or y is found and return true.
3. Once one of the nodes is discovered, for every other recursive call after this discovery, we return false if the current depth is more than the recorded depth. This basically means we didn't find the other node at the same depth and there is no point going beyond. This step of pruning helps to speed up the recursion by reducing the number of recursive calls.
4. Return true when the other node is discovered and has the same depth as the recorded depth.
5. Recurse the left and the right subtree of the current node. If both left and right recursions return true and the current node is not their immediate parent, then Node x and Node y are cousins. Thus, isCousin is set to value true.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    // To save the depth of the first node.
    int recordedDepth = -1;
    boolean isCousin = false;

    private boolean dfs(TreeNode node, int depth, int x, int y) {

        if (node == null) {
            return false;
        }

        // Don't go beyond the depth restricted by the first node found.
        if (this.recordedDepth != -1 && depth > this.recordedDepth) {
            return false;
        }

        if (node.val == x || node.val == y) {
            if (this.recordedDepth == -1) {
                // Save depth for the first node found.
                this.recordedDepth = depth;
            }
            // Return true, if the second node is found at the same depth.
            return this.recordedDepth == depth;
        }

        boolean left = dfs(node.left, depth + 1, x, y);
        boolean right = dfs(node.right, depth + 1, x, y);

        // this.recordedDepth != depth + 1 would ensure node x and y are not
        // immediate child nodes, otherwise they would become siblings.
        if (left && right && this.recordedDepth != depth + 1) {
            this.isCousin = true;
        }
        return left || right;
    }
    public boolean isCousins(TreeNode root, int x, int y) {

        // Recurse the tree to find x and y
        dfs(root, 0, x, y);
        return this.isCousin;
    }
}
/*
Complexity Analysis

Time Complexity: O(N), where NNN is the number of nodes in the binary tree. In the worst case, we might have to visit all the nodes of the binary tree.

Let's look into one such scenario. When both Node x and Node y are the leaf nodes and at the last level of the tree, the algorithm has no reasons to prune the recursion. It can only come to a conclusion once it visits both the nodes. If one of these nodes is the last node to be discovered the algorithm inevitably goes through each and every node in the tree.

Space Complexity: O(N). This is because the maximum amount of space utilized by the recursion stack would be NNN, as the height of a skewed binary tree could be, at worst, NNN. For a left skewed or a right skewed binary tree, where the desired nodes are lying at the maximum depth possible, the algorithm would have to maintain a recursion stack of the height of the tree.
  */
