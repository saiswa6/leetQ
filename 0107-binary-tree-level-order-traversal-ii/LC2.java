/*
Approach 2: Iteration: BFS Traversal
Algorithm
The recursion above could be rewritten in the iteration form.
- Let's keep each tree level in the queue structure, which typically orders elements in a FIFO (first-in-first-out) manner. 
In Java one could use ArrayDeque implementation of the Queue interface.
In Python using Queue structure would be an overkill since it's designed for a safe exchange between multiple threads and hence requires locking which leads to a performance downgrade.
In Python the queue implementation with a fast atomic append() and popleft() is deque.

Algorithm
- Initialize two queues: one for the current level, and one for the next. Add root into nextLevel queue.
- While nextLevel queue is not empty:
   - Initialize the current level currLevel = nextLevel, and empty the next level nextLevel.
   - Iterate over the current level queue:
     - Append the node value to the last level in levels.
     - Add first left and then right child node into nextLevel queue.
- Return reversed levels.
*/

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;
        
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque() {{ offer(root); }};
        ArrayDeque<TreeNode> currLevel = new ArrayDeque();        
        
        while (!nextLevel.isEmpty()) {
            currLevel = nextLevel.clone();
            nextLevel.clear();
            levels.add(new ArrayList<Integer>());
            
            for (TreeNode node : currLevel) {
                // append the current node value
                levels.get(levels.size() - 1).add(node.val);

                // process child nodes for the next level
                if (node.left != null) 
                    nextLevel.offer(node.left);    
                if (node.right != null) 
                    nextLevel.offer(node.right);
            }   
        }
        
        Collections.reverse(levels);
        return levels;
    }
}

/*
Complexity Analysis
Time complexity: O(N) since each node is processed exactly once.

Space complexity: O(N) to keep the output structure which contains N node values.
*/

//use Linked List to store the result
//LinkedList<List<Integer>> result = new LinkedList<>();
//result.addFirst(level);
