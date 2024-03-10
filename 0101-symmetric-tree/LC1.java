/*
Approach 1: Recursive
A tree is symmetric if the left subtree is a mirror reflection of the right subtree.
- Therefore, the question is: when are two trees a mirror reflection of each other?
- Two trees are a mirror reflection of each other if:
  1. Their two roots have the same value.
  2. The right subtree of each tree is a mirror reflection of the left subtree of the other tree.

This is like a person looking at a mirror. The reflection in the mirror has the same head, but the reflection's right arm corresponds to the actual person's left arm, and vice versa.
*/
public boolean isSymmetric(TreeNode root) {
    return isMirror(root, root);
}

public boolean isMirror(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null || t2 == null) return false;
    return (t1.val == t2.val)
        && isMirror(t1.right, t2.left)
        && isMirror(t1.left, t2.right);
}

/*
Complexity Analysis
Time complexity: O(n). Because we traverse the entire input tree once, the total run time is O(n), where nnn is the total number of nodes in the tree.

Space complexity: The number of recursive calls is bound by the height of the tree. In the worst case, the tree is linear and the height is in O(n). Therefore, space complexity due to recursive calls on the stack is O(n) in the worst case.
*/



/*
Enhancement
Approach 1 as currently written is doing every comparison twice! This is because isMirror is called with root as the value for both t1 and t2. So we'll end up validating that the left subtree is a mirror of the right subtree but also that the right subtree is a mirror of the left subtree. An easy fix would be to do the null check in the main method and call the recursive method with root->left and root->right. In C++ that would look like this:



*/
bool isSymmetric(TreeNode* root) {
        if (!root) return true;
        return isSymmetric(root->left, root->right);
    }
    
    bool isSymmetric(TreeNode* t1, TreeNode* t2){
        if (!t1 && !t2) return true;
        if (!t1 || !t2) return false;
        return t1->val == t2->val
            && isSymmetric(t1->left, t2->right)
            && isSymmetric(t1->right, t2->left);
    }
