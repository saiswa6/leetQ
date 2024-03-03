/*
// Bad Implementation. Follow Kunal Pattern Soln.


Algorithm
Another method to solve the given problem is to make use of a Breadth First Search. In BFS, we start by pushing the root node into a queue. Then, we remove an element(node) from the front of the queue. For every node removed from the queue, we add all its children to the back of the same queuequeuequeue. We keep on continuing this process till the queue becomes empty. In this way, we can traverse the given tree on a level-by-level basis.

But, in the current implementation, we need to do a slight modification, since we need to separate the nodes on one level from that of the other.
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
public class Solution {
    public List < Double > averageOfLevels(TreeNode root) {
        List < Double > res = new ArrayList < > ();
        Queue < TreeNode > queue = new LinkedList < > ();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;
            Queue < TreeNode > temp = new LinkedList < > ();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null)
                    temp.add(n.left);
                if (n.right != null)
                    temp.add(n.right);
            }
            queue = temp;
            res.add(sum * 1.0 / count);
        }
        return res;
    }
}

/*
Complexity Analysis
Time complexity : O(n). The whole tree is traversed at most once. Here, nnn refers to the number of nodes in the given binary tree.
Space complexity : O(m). The size of queuequeuequeue or temptemptemp can grow upto at most the maximum number of nodes at any level in the given binary tree. Here, mmm refers to the maximum mumber of nodes at any level in the input tree.
*/
