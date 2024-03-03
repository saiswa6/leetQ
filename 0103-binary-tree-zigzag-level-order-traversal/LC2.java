/*
Approach 2: DFS (Depth-First Search)
Intuition
- Though not intuitive, we could also obtain the BFS traversal ordering via the DFS (Depth-First Search) traversal in the tree.
- The trick is that during the DFS traversal, we maintain the results in a global array that is indexed by the level, i.e. the element array[level] would contain all the nodes that are at the same level. The global array would then be referred to and updated at each step of DFS.

Similar with the above modified BFS algorithm, we employ the deque data structure to hold the nodes that are of the same level, and we alternate the insertion direction (i.e. either to the head or to the tail) to generate the desired output ordering.

Algorithm
- Here we implement the DFS algorithm via recursion. We define a recursive function called DFS(node, level) which only takes care of the current node which is located at the specified level. Within the function, here are three steps that we would perform:
  1. If this is the first time that we visit any node at the level, i.e. the deque for the level does not exist, then we simply create the deque with the current node value as the initial element.
  2. If the deque for this level exists, then depending on the ordering, we insert the current node value either to the head or to the tail of the queue.
  3. At the end, we recursively call the function for each of its child nodes.

  It might go without saying that, one can also implement the DFS traversal via iteration rather than recursion, which could be one of the followup questions by an interviewer.
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
  protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
    if (level >= results.size()) {
      LinkedList<Integer> newLevel = new LinkedList<Integer>();
      newLevel.add(node.val);
      results.add(newLevel);
    } else {
      if (level % 2 == 0)
        results.get(level).add(node.val);
      else
        results.get(level).add(0, node.val);
    }

    if (node.left != null) DFS(node.left, level + 1, results);
    if (node.right != null) DFS(node.right, level + 1, results);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<List<Integer>>();
    }
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    DFS(root, 0, results);
    return results;
  }
}

/*
Complexity Analysis
Time Complexity: O(N), where N is the number of nodes in the tree.
Same as the previous BFS approach, we visit each node once and only once.

Space Complexity: O(N)
- Unlike the BFS approach, in the DFS approach, we do not need to maintain the node_queue data structure for the traversal.
- However, the function recursion will incur additional memory consumption on the function call stack. As we can see, the size of the call stack for any invocation of DFS(node, level) will be exactly the number of level that the current node resides on. Therefore, the space complexity of our DFS algorithm is O(H), where HHH is the height of the tree. In the worst-case scenario, when the tree is very skewed, the tree height could be NNN. Thus the space complexity is also O(N).
- Note that if the tree were guaranteed to be balanced, then the maximum height of the tree would be log⁡N\log NlogN which would result in a better space complexity than the BFS approach.
*/
