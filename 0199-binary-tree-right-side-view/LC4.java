/*
Approach 4: Recursive DFS
Everyone likes recursive DFS, so let's add it here as well. The idea is simple: to traverse the tree level by level, starting each time from the rightmost child.
*/
class Solution {
    List<Integer> rightside = new ArrayList();
    
    public void helper(TreeNode node, int level) {
        if (level == rightside.size()) 
            rightside.add(node.val);
        
        if (node.right != null) 
            helper(node.right, level + 1);  
        if (node.left != null) 
            helper(node.left, level + 1);
    }    
    
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return rightside;
        
        helper(root, 0);
        return rightside;
    }
}
/*
Complexity Analysis
Time complexity: O(N) since one has to visit each node.
Space complexity: O(H) to keep the recursion stack, where H is a tree height. The worst-case situation is a skewed tree when H=N.
*/
