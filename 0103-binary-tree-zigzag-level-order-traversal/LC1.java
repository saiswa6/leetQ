/*
Approach 1: BFS (Breadth-First Search)
Intuition
- Following the description of the problem, the most intuitive solution would be the BFS (Breadth-First Search) approach through which we traverse the tree level-by-level.
- The default ordering of BFS within a single level is from left to right. As a result, we should adjust the BFS algorithm a bit to generate the desired zigzag ordering.
- One of the keys here is to store the values that are of the same level with the deque (double-ended queue) data structure, where we could add new values on either end of a queue.
- So if we want to have the ordering of FIFO (first-in-first-out), we simply append the new elements to the tail of the queue, i.e. the latecomers stand last in the queue. If we want to have the ordering of FILO (first-in-last-out), we insert the new elements to the head of the queue, i.e. the latecomers jump the queue.


Code
Testcase
Testcase
Test Result
4.05
(96 votes)
Premium

Solution
Approach 1: BFS (Breadth-First Search)
Intuition

Following the description of the problem, the most intuitive solution would be the BFS (Breadth-First Search) approach through which we traverse the tree level-by-level.

The default ordering of BFS within a single level is from left to right. As a result, we should adjust the BFS algorithm a bit to generate the desired zigzag ordering.

One of the keys here is to store the values that are of the same level with the deque (double-ended queue) data structure, where we could add new values on either end of a queue.

So if we want to have the ordering of FIFO (first-in-first-out), we simply append the new elements to the tail of the queue, i.e. the latecomers stand last in the queue. If we want to have the ordering of FILO (first-in-last-out), we insert the new elements to the head of the queue, i.e. the latecomers jump the queue.

Algorithm
==========
There are several ways to implement the BFS algorithm.
1. One way would be to run a two-level nested loop, with the outer loop iterating each level on the tree, and with the inner loop iterating each node within a single level.
2. We could also implement BFS with a single loop though. The trick is that we append the nodes to be visited into a queue and we separate nodes of different levels with a sort of delimiter (e.g. an empty node). The delimiter marks the end of a level, as well as the beginning of a new level.

- Here we adopt the second approach above. One can start with the normal BFS algorithm, upon which we add a touch of zigzag order with the help of deque. For each level, we start from an empty deque container to hold all the values of the same level. Depending on the ordering of each level, i.e. either from left to right or from right to left, we decide at which end of the deque to add the new element:
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
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<List<Integer>>();
    }

    List<List<Integer>> results = new ArrayList<List<Integer>>();

    // add the root element with a delimiter to kick off the BFS loop
    LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
    node_queue.addLast(root);
    node_queue.addLast(null);

    LinkedList<Integer> level_list = new LinkedList<Integer>();
    boolean is_order_left = true;

    while (node_queue.size() > 0) {
      TreeNode curr_node = node_queue.pollFirst();
      if (curr_node != null) {
        if (is_order_left)
          level_list.addLast(curr_node.val);
        else
          level_list.addFirst(curr_node.val);

        if (curr_node.left != null)
          node_queue.addLast(curr_node.left);
        if (curr_node.right != null)
          node_queue.addLast(curr_node.right);

      } else {
        // we finish the scan of one level
        results.add(level_list);
        level_list = new LinkedList<Integer>();
        // prepare for the next level
        if (node_queue.size() > 0)
          node_queue.addLast(null);
        is_order_left = !is_order_left;
      }
    }
    return results;
  }
}

//Note: as an alternative approach, one can also implement the normal BFS algorithm first, which would generate the ordering of from-left-to-right for each of the levels. Then, at the end of the algorithm, we can simply reverse the ordering of certain levels, following the zigzag steps.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level=0;
        
        while(!q.isEmpty()){
            List<Integer> ls = new ArrayList<>();
            int n = q.size();
            
            int i=0;
            while(i<n){
                TreeNode peek= q.remove();
                if(peek==null) break;
                ls.add(peek.val);
                
                i++;
                
                if(peek.left!=null) q.add(peek.left);
                if(peek.right!=null) q.add(peek.right);
            }
            if(level%2!=0) Collections.reverse(ls);
            if(ls.size()>0)
                result.add(ls);
            level++;
        }
        return result;
    }
}


/*
Complexity Analysis
Time Complexity: O(N), where N is the number of nodes in the tree.
- We visit each node once and only once.
- In addition, the insertion operation on either end of the deque takes a constant time, rather than using the array/list data structure where the inserting at the head could take the O(K) time where KKK is the length of the list.

Space Complexity: O(N) where N is the number of nodes in the tree.
- The main memory consumption of the algorithm is the node_queue that we use for the loop, apart from the array that we use to keep the final output.
- As one can see, at any given moment, the node_queue would hold the nodes that are at most across two levels. Therefore, at most, the size of the queue would be no more than 2⋅L2 \cdot L2⋅L, assuming LLL is the maximum number of nodes that might reside on the same level. Since we have a binary tree, the level that contains the most nodes could occur to consist of all the leave nodes in a full binary tree, which is roughly L=N/2. As a result, we have the space complexity of 2⋅N/2=N in the worst case.
*/
