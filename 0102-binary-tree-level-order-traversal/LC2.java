/*
Approach 2: Iteration
Algorithm
- The recursion above could be rewritten in the iteration form.
- Let's keep nodes of each tree level in the queue structure, which typically orders elements in a FIFO (first-in-first-out) manner. In Java one could use LinkedList implementation of the Queue interface. In Python using Queue structure would be an overkill since it's designed for a safe exchange between multiple threads and hence requires locking which leads to a performance loss. In Python, the queue implementation with a fast atomic append() and popleft() is deque.
- The zero level contains only one node root. The algorithm is simple :
  - Initiate queue with a root and start from the level number 0: level = 0.
  - While the queue is not empty :
    - Start the current level by adding an empty list into the output structure levels.
    - Compute how many elements should be on the current level: it's a queue length.
    - Pop out all these elements from the queue and add them to the current level.
    - Push their child nodes into the queue for the next level.
    - Go to the next level level++.

  */

class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();
    if (root == null) return levels;

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    int level = 0;
    while ( !queue.isEmpty() ) {
      // start the current level
      levels.add(new ArrayList<Integer>());

      // number of elements in the current level
      int level_length = queue.size();
      for(int i = 0; i < level_length; ++i) {
        TreeNode node = queue.remove();

        // fulfill the current level
        levels.get(level).add(node.val);

        // add child nodes of the current level
        // in the queue for the next level
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
      }
      // go to next level
      level++;
    }
    return levels;
  }
}
/*
Complexity Analysis
Time complexity: O(N) since each node is processed exactly once.
Space complexity: O(N) to keep the output structure which contains N node values.


Discussion SC
-- Iterative is O(n) in the worst case due to @kirankorey133 's explanation
  Space complexity = O( max number of nodes on a level), for a full and complete binary tree(worst case) last level which has all the leaf nodes will have the max number of nodes.
  Number of leaf nodes is given by (n+1)/2, so ignoring the constants makes the space complexity O(N)
-- Recursive is O(h) ---> O(n) in the worst case of a skewed tree

  */
