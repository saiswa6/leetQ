/*
Ok, My nightmare just came TRUE! I was asked the follow-up of this question.
Follow-up: print the path of the diameter?

Is there a way to find the actual path along with the diameter?
My approach:

return the node (node here refers to the root(not the actual root) for our diameter)
I will be having two variables, max_left, and max_right
based on these max_left and max_right values, I need to explore all the paths until these values are zero and our pointer is in the leaf node.
I know my approach is lame but is there a better way to do this?
I also tried to optimize it by having two ques.
left queue and right queue, if left is grater than len(left_queue) add it to the left_que
if the right is greater than len(right queue) then add the node.val to the right_que

====================
no need to panic !!. when you update the diameter also store the current node as root of the new diameter. later do inorder traversal from saved root.

====================
class Solution:
    def diameterOfBinaryTree(self, root): 
        def dfs(node):
            if not node:
                return 0, []
            
            nonlocal diameter
            nonlocal diameterPath

            left, leftPath = dfs(node.left)
            right, rightPath = dfs(node.right)

            if left + right > diameter:
                diameter = left + right
                diameterPath = leftPath + [node.val] + rightPath[::-1]

            if left > right:
                return 1 + left, leftPath + [node.val]
            return 1 + right, rightPath + [node.val]

        diameter = 0
        diameterPath = []
        dfs(root)
        return diameter, diameterPath
*/
