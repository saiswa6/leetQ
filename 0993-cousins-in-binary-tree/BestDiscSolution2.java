//DFS
//dfs
class Solution {
    TreeNode xParent = null;
    TreeNode yParent = null;
    int xDepth = -1, yDepth = -1;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        getDepthAndParent(root, x, y, 0, null);
        return xDepth == yDepth && xParent != yParent? true: false;
    }
    //get both the depth and parent for x and y
    public void getDepthAndParent(TreeNode root, int x, int y, int depth, TreeNode parent){
        if(root == null){
            return;
        }
        if(root.val == x){
            xParent = parent;
            xDepth = depth;
        }else if(root.val == y){
            yParent = parent;
            yDepth = depth;
        }       
        getDepthAndParent(root.left, x, y, depth + 1, root);
        getDepthAndParent(root.right, x, y, depth + 1, root);
    }
}


/*
I would put getDepthAndParent inside an else. Then if x or y is found, you don't need to go deeper in the tree.

if(root.val == x){
    xParent = parent;
    xDepth = depth;
} else if(root.val == y){
    yParent = parent;
    yDepth = depth;
} else {       
    getDepthAndParent(root.left, x, y, depth + 1, root);
    getDepthAndParent(root.right, x, y, depth + 1, root);
}
*/
