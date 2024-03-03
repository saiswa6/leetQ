/*
Approach 2: BFS: One Queue + Sentinel
-------------------------------------
- Another approach is to push all the nodes in one queue and to use a sentinel node to separate the levels. Typically, one could use null as a sentinel.
- The first step is to initiate the first level: root + null as a sentinel. Once it's done, continue to pop the nodes one by one from the left and push their children to the right. Stop each time the current node is null because it means we hit the end of the current level. Each stop is a time to update a right-side view list and to push null in the queue to mark the end of the next level.

//In computer programming, a sentinel node is a specifically designated node used with linked lists and trees as a traversal path terminator. This type of node does not hold or reference any data managed by the data structure.
//Sentinels are used as an alternative over using NULL as the path terminator in order to get one or more of the following benefits:
   - Marginally increased speed of operations
   - Increased data structure robustness (arguably)
Drawbacks
   - Marginally increased algorithmic complexity and code size.

Algorithm
- Initiate the list of the right side view rightside.
- Initiate the queue by adding a root. Add null sentinel to mark the end of the first level.
- Initiate the current node as root.
- While the queue is not empty:
   - Save the previous node prev = curr and pop the current node from the queue curr = queue.poll().
   - While the current node is not null:
      - Add first left and then right child node into the queue.
      - Update both previous and current nodes: prev = curr, curr = queue.poll().
   - Now the current node is null, i.e. we reached the end of the current level. Hence the previous node is the rightmost one and makes a part of the right side view. Add it into rightside.
   - If the queue is not empty, push the null node as a sentinel, to mark the end of the next level.
- Return rightside.

Implementation
-----------------
Note, that ArrayDeque in Java doesn't support null elements, and hence the data structure to use here is LinkedList.
*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        
        Queue<TreeNode> queue = new LinkedList(){{ offer(root); offer(null); }};
        TreeNode prev, curr = root;
        List<Integer> rightside = new ArrayList();
        
        while (!queue.isEmpty()) {
            prev = curr;
            curr = queue.poll();

            while (curr != null) {
                // add child nodes in the queue
                if (curr.left != null) {
                    queue.offer(curr.left);    
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                
                prev = curr;
                curr = queue.poll();
            }      

            // the current level is finished
            // and prev is its rightmost element
            rightside.add(prev.val);

            // add a sentinel to mark the end
            // of the next level
            if (!queue.isEmpty())
                queue.offer(null);
        }
        return rightside;
    }
}

/*
Complexity Analysis
Time complexity: O(N) since one has to visit each node.
Space complexity: O(D) to keep the queues, where DDD is a tree diameter. Let's use the last level to estimate the queue size. This level could contain up to N/2 tree nodes in the case of complete binary tree.
*/
