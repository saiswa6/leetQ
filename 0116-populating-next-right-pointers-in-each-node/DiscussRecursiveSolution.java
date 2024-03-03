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

// 3rd
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null || root.left == null) return;
        connectNodes(root.left, root.right);
    }
    
    public void connectNodes(TreeLinkNode node1, TreeLinkNode node2) {
        node1.next = node2;
        if(node1.left != null) {
            connectNodes(node1.right, node2.left);
            connectNodes(node1.left, node1.right);
            connectNodes(node2.left, node2.right);
        }
    } 
}
