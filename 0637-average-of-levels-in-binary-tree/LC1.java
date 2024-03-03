/*
Approach #1 Using Depth First Search [Accepted]
Algorithm
- One of the methods to solve the given problem is to make use of Depth First Search. In DFS, we try to exhaust each branch of the given tree during the tree traversal before moving onto the next branch.
- To make use of DFS to solve the given problem, we make use of two lists countcountcount and resresres. Here, count[i]count[i]count[i] refers to the total number of nodes found at the ith level(counting from root at level 0) till now, and res[i] refers to the sum of the nodes at the ith level encountered till now during the Depth First Search.
- We make use of a function average(t, i, res, count), which is used to fill the resresres and countcountcount array if we start the DFS from the node t at the ith level in the given tree. We start by making the function call average(root, 0, res, count). After this, we do the following at every step:
  1. Add the value of the current node to the resresres(or sum) at the index corresponding to the current level. Also, increment the count at the index corresponding to the current level.
  2. Call the same function, average, with the left and the right child of the current node. Also, update the current level used in making the function call.
  3. Repeat the above steps till all the nodes in the given tree have been considered once.
  4 Populate the averages in the resultant array to be returned.
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
        List < Integer > count = new ArrayList < > ();
        List < Double > res = new ArrayList < > ();
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++)
            res.set(i, res.get(i) / count.get(i));
        return res;
    }
    public void average(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if (t == null)
            return;
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + t.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left, i + 1, sum, count);
        average(t.right, i + 1, sum, count);
    }
}

/*
Complexity Analysis
Time complexity : O(n). The whole tree is traversed once only. Here, n refers to the total number of nodes in the given binary tree.
Space complexity : O(h). resresres and countcountcount array of size hhh are used. Here, h refers to the height(maximum number of levels) of the given binary tree. Further, the depth of the recursive tree could go upto hhh only.
*/
