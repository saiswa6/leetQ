/*
How to traverse the tree
-------------------------
There are two general strategies to traverse a tree:
1) Depth First Search (DFS)
- In this strategy, we adopt depth as the priority, so that one would start from a root and reach all the way down to a certain leaf, and then back to root to reach another branch.
- The DFS strategy can further be distinguished as preorder, inorder, and postorder depending on the relative order among the root node, left node, and right node.

2)Breadth First Search (BFS)
- We scan through the tree level by level, following the order of height, from top to bottom. The nodes on higher levels would be visited before the ones with lower levels.
*/

/*
Approach 1: Recursion
Algorithm
- The simplest way to solve the problem is to use a recursion. Let's first ensure that the tree is not empty, and then call recursively the function helper(node, level), which takes the current node and its level as the arguments.
- This function does the following :
  1. The output list here is called levels, and hence the current level is just a length of this list len(levels). Compare the number of a current level len(levels) with a node level level. If you're still on the previous level - add the new one by adding a new list into levels.
  2. Append the node value to the last list in levels.
  3. Process recursively child nodes if they are not None: helper(node.left / node.right, level + 1).
*/

class Solution {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

         // fulfil the current level
         levels.get(level).add(node.val);

         // process child nodes for the next level
         if (node.left != null)
            helper(node.left, level + 1);
         if (node.right != null)
            helper(node.right, level + 1);
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }
}
