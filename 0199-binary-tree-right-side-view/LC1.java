/*
Overview
DFS vs. BFS
- There are two ways to traverse the tree: DFS depth first search and BFS breadth first search. Here is a small summary
  PIC
- BFS traverses level by level, and DFS first goes to the leaves.

Which approach to choose, BFS or DFS?
- The problem is to return a list of the last elements from all levels, so it's way more natural to implement BFS here.
- Time complexity is the same O(N) both for DFS and BFS since one has to visit all nodes.
- Space complexity is O(H) for DFS and O(D) for BFS, where H is a tree height, and D is a tree diameter. They both result in O(N) space in the worst-case scenarios: skewed tree for DFS and complete tree for BFS.

BFS wins for this problem, so let's use the opportunity to check out three different BFS implementations with the queue.

BFS implementation
- All three implementations use the queue in a standard BFS way:
   1. Push the root into the queue.
   2. Pop-out a node from the left.
   3. Push the left child into the queue, and then push the right child.

   Three BFS approaches
   ====================
- The difference is how to find the end of the level, i.e. the rightmost element:
  1. Two queues, one for the previous level and one for the current.
  2. One queue with a sentinel to mark the end of the level.
  3. One queue + level size measurement.


Approach 1: BFS: Two Queues
Let's use two queues: one for the current level, and one for the next. The idea is to pop the nodes one by one from the current level and push their children into the next level queue. Each time the current queue is empty, we have the right side element in our hands.

Algorithm
- Initiate the list of the right side view rightside.
- Initiate two queues: one for the current level, and one for the next. Add root into nextLevel queue.
- While nextLevel queue is not empty:
  - Initiate the current level: currLevel = nextLevel, and empty the next level nextLevel.
  - While the current level queue is not empty:
    - Pop out a node from the current level queue.
    - Add first left and then right child node into nextLevel queue.
    - Now currLevel is empty, and the node we have in hands is the last one, and makes a part of the right side view. Add it into rightside.
- Return rightside.
*/
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque() {{ offer(root); }};
        ArrayDeque<TreeNode> currLevel = new ArrayDeque();        
        List<Integer> rightside = new ArrayList();
        
        TreeNode node = null;
        while (!nextLevel.isEmpty()) {
            // prepare for the next level
            currLevel = nextLevel;
            nextLevel = new ArrayDeque<>();

            while (! currLevel.isEmpty()) {
                node = currLevel.poll();

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) 
                    nextLevel.offer(node.left);    
                if (node.right != null) 
                    nextLevel.offer(node.right);
            }
            
            // The current level is finished.
            // Its last element is the rightmost one.
            if (currLevel.isEmpty()) 
                rightside.add(node.val);    
        }
        return rightside;
    }
}

/*
Complexity Analysis

Time complexity: O(N) since one has to visit each node.
Space complexity: O(D) to keep the queues, where DDD is a tree diameter. Let's use the last level to estimate the queue size. This level could contain up to N/2 tree nodes in the case of complete binary tree.
*/
