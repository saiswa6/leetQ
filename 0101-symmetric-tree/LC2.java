/*
Approach 2: Iterative
Instead of recursion, we can also use iteration with the aid of a queue. Each two consecutive nodes in the queue should be equal, and their subtrees a mirror of each other. Initially, the queue contains root and root. Then the algorithm works similarly to BFS, with some key differences. Each time, two nodes are extracted and their values are compared. Then, the right and left children of the two nodes are inserted in the queue in opposite order. The algorithm is done when either the queue is empty, or we detect that the tree is not symmetric (i.e. we pull out two consecutive nodes from the queue that are unequal).

*/
public boolean isSymmetric(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    q.add(root);
    while (!q.isEmpty()) {
        TreeNode t1 = q.poll();
        TreeNode t2 = q.poll();
        if (t1 == null && t2 == null) continue;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        q.add(t1.left);
        q.add(t2.right);
        q.add(t1.right);
        q.add(t2.left);
    }
    return true;
}
// Drawback :
//Aren't we comparing every element twice with the approach 2? We first put [1,1] and then [2,2,2,2] and then [3,3,4,4,3,3,4,4]. Approach 2 two makes every comparison twice.
// Use kunal pattern solution
    //q.add(root.left);
    //q.add(root.right);

/*
Complexity Analysis
Time complexity: O(n). Because we traverse the entire input tree once, the total run time is O(n), where nnn is the total number of nodes in the tree.

Space complexity: There is additional space required for the search queue. In the worst case, we have to insert eeeO(n) nodes in the queue. Therefore, space complexity is O(n).
  */
