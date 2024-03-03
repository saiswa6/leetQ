/*
Let's use this article to discuss the two most simple and fast techniques:
1. Recursive DFS.
2. Iterative BFS with two queues.

Note, that both approaches are root-to-bottom traversals, and we're asked to provide bottom-up output. To achieve that, the final result should be reversed.

Approach 1: Recursion: DFS Preorder Traversal
Intuition
- The first step is to ensure that the tree is not empty.
- The second step is to implement the recursive function helper(node, level), which takes the current node and its level as the arguments.

Algorithm for the Recursive Function
Here is its implementation:
1. Initialize the output list levels.
    The length of this list determines which level is currently updated.
    You should compare this level len(levels) with a node level level,
    to ensure that you add the node on the correct level.
    If you're still on the previous level -
add the new level by adding a new list into levels.
2. Append the node value to the last level in levels.
3. Process recursively child nodes if they are not None:
   helper(node.left / node.right, level + 1).
*/

class Solution {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

         // append the current node value
         levels.get(level).add(node.val);

         // process child nodes for the next level
         if (node.left != null)
            helper(node.left, level + 1);
         if (node.right != null)
            helper(node.right, level + 1);
    }
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        Collections.reverse(levels); // IMPORTANT
        return levels;
    }
}

/*
Complexity Analysis
Time complexity: O(N) since each node is processed exactly once.

Space complexity: O(N) to keep the output structure which contains NNN node values.
*/
