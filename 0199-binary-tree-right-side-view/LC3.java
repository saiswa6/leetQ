/*
Approach 3: BFS: One Queue + Level Size Measurements
Instead of using the sentinel, we could write down the length of the current level.

Algorithm
1. Initiate the list of the right side view rightside.
2. Initiate the queue by adding a root.
3. While the queue is not empty:
    a. Write down the length of the current level: levelLength = queue.size().
    b. Iterate over i from 0 to level_length - 1:
       - Pop the current node from the queue: node = queue.poll().
       - If i == levelLength - 1, then it's the last node in the current level, push it to rightsize list.
       - Add first left and then right child node into the queue.
4. Return rightside.
*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        
        ArrayDeque<TreeNode> queue = new ArrayDeque(){{ offer(root); }};
        List<Integer> rightside = new ArrayList();
        
        while (!queue.isEmpty()) {
            int levelLength = queue.size();

            for(int i = 0; i < levelLength; ++i) {
                TreeNode node = queue.poll();

                // if it's the rightmost element
                if (i == levelLength - 1) {
                    rightside.add(node.val);    
                }

                // add child nodes in the queue
                if (node.left != null) {
                    queue.offer(node.left);    
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return rightside;
    }
}

/*
Complexity Analysis

Time complexity: O(N) since one has to visit each node.

Space complexity: O(D) to keep the queues, where DDD is a tree diameter. Let's use the last level to estimate the queue size. This level could contain up to N/2 tree nodes in the case of complete binary tree.
*/
