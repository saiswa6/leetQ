class Solution {
    public void connectNext(Node root){
        if(root == null){
            return;
        }
        if(root.left != null){
            root.left.next = root.right;
        }
        if(root.right != null && root.next != null){
            root.right.next = root.next.left;
        }
        connectNext(root.left);
        connectNext(root.right);
    }
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        //root.next = null;
        connectNext(root);
        return root;
    }
}

// 2nd similar
var connect = function(root) {
    if(!root) return root
    
    if(root.left) root.left.next = root.right
    if(root.right && root.next) root.right.next = root.next.left
    
    connect(root.left)
    connect(root.right)
    return root
};
