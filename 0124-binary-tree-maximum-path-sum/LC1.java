/*
Approach: Post Order DFS
Intuition
One way to find the maximum path sum would be to look at all possible paths, calculate their path sums, and then find the maximum path sum. However, this would be a "brute force" approach. If there are nnn nodes in the tree, creating all the paths and computing their path sums would take O(n^2) time.

Let's put this formally now. Let's name our recursive function gain_from_subtree. It takes the root of a subtree as the input. The function has two responsibilities.
1. The function returns the path sum gain contributed by the subtree.
    - The path sum gain contributed by the subtree can be derived from a path that includes at most one child of the root. You may wonder, why can't we include both children? If we include both children in the path, the path would have to make a fork at the root. The root is already connected to its parent. Now, if we include both children as well, with three connections, it wouldn't be a valid path anymore. Therefore, we can say that the path would consist of at most one child of the root.
    - The recursive function compares left_gain and right_gain and adds the maximum of the two to the value of the root. The sum, gain_from_subtree is then returned to the caller.
      left_gain = max(gain_from_left_subtree, 0)
      right_gain = max(gain_from_right_subtree, 0)
      gain_from_subtree = max(left_gain, right_gain) + root.val
    - We use max(gain_from_left_subtree, 0) because we want to consider the gain only if it is positive. If it is negative, we ignore it or consider it as zero.

2. The function keeps track of the maximum path sum.
    - Assuming that the maximum path sum passes through the root of the subtree, as explained earlier, we consider all four possibilities - 
    (1) The path goes through the left subtree 
    (2) The path goes through the right subtree 
    (3) The path goes through both left and right subtrees 
    (4) The path doesn't involve left or right subtrees. So we include the left and right gain if they are positive and the value of the root node. We compare this sum with the current maximum path sum and update it if necessary. The following code segment takes care of all four possibilities.
    - max_path_sum = max(max_path_sum, left_gain + right_gain + root.val)

A note on the base case
Let's say our recursive algorithm reaches a node with no left child. Because there is no left child, our path cannot go toward the left subtree. In other words, the gain from the left subtree is 000. So, when the function is called on the non-existent child of the node, it returns 000. This would be true for a non-existent right child as well. Therefore, we can safely say that the base case is when the root is null.
*/

class Solution {
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gainFromSubtree(root);
        return maxSum;
    }

    private int maxSum;

    // post order traversal of subtree rooted at `root`
    private int gainFromSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // add the path sum from left subtree. Note that if the path
        // sum is negative, we can ignore it, or count it as 0.
        // This is the reason we use `Math.max` here.
        int gainFromLeft = Math.max(gainFromSubtree(root.left), 0);

        // add the path sum from right subtree. 0 if negative
        int gainFromRight = Math.max(gainFromSubtree(root.right), 0);

        // if left or right path sum are negative, they are counted
        // as 0, so this statement takes care of all four scenarios
        maxSum = Math.max(maxSum, gainFromLeft + gainFromRight + root.val);

        // return the max sum for a path starting at the root of subtree
        return Math.max(gainFromLeft + root.val, gainFromRight + root.val);
    }
}

/*

Complexity Analysis
Let n be the number of nodes in the tree.
Time complexity: O(n)
Each node in the tree is visited only once. During a visit, we perform constant time operations, including two recursive calls and calculating the max path sum for the current node. So the time complexity is O(n).

Space complexity: O(n)
We don't use any auxiliary data structure, but the recursive call stack can go as deep as the tree's height. In the worst case, the tree is a linked list, so the height is n. Therefore, the space complexity is O(n).
  */
