/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

// Do solution like Linked list

class Solution {
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Node leftMost = root; // Always take leftmost onw as it is a start of each level

        while(leftMost.left != null) {
            Node current = leftMost;

            while(current != null) {
                current.left.next = current.right; //Link left node with right node
                if(current.next != null) {
                    current.right.next = current.next.left; // Link right node with left node of next current node
                }
                current = current.next; // move forward
            }
            leftMost = leftMost.left; // Go leftmost of next level
        }
        return root;
    }

}
