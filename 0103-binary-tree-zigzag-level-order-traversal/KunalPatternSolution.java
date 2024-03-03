/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
//Use Deque to add First. Last and remove First and Last
//If Normal Order -> Remove from Front, Add to Back
//If Reverse Order - Remove from Last, Add to Front
// Use some flag to make sure about order
//1
//3,2
//4,5,6,7

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean reverse = false;

        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {
                if(!reverse) {
                    TreeNode currentNode = queue.removeFirst();
                    currentLevel.add(currentNode.val);
                    
                    if(currentNode.left != null) {
                        queue.addLast(currentNode.left);
                    }
                    if(currentNode.right != null) {
                        queue.addLast(currentNode.right);
                    }
                } else {
                    TreeNode currentNode = queue.removeLast();
                    currentLevel.add(currentNode.val);

                    if(currentNode.right != null) {
                        queue.addFirst(currentNode.right);
                    }
                    if(currentNode.left != null) {
                        queue.addFirst(currentNode.left);
                    }
                }
            }

            result.add(new ArrayList<>(currentLevel));
            reverse = !reverse;
        }

        return result;
    }
}

// T.C : O(N)
// S.C: O(H) or O(N) in case of skewed BT
