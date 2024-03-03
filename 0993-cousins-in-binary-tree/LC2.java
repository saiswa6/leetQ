/*
// For clear explanation, read editorial

Approach 2: Breadth First Search with Early Stopping
- If Node x or Node y is lying very shallow in the tree, then does it make any sense to iterate down the entire tree?

- Since this problem is about finding cousins, i.e. nodes lying at the same level/depth, it seems more natural to do a level order traversal of the tree.
- If we do a level order traversal for the aforementioned example, we would only traverse until depth 2. At depth 2, we discover Node 4, but we do not find Node 6 at the same level. Hence we can just stop our traversal and conclude that the nodes are not cousins.
- Note, if the nodes are cousins, we would find both the nodes at the same depth.
However, this is also true for siblings. We need to figure out how to determine when two nodes are siblings. One way to find out that they are siblings is when we are adding the nodes to the queue. If Node x and Node y are left and right children of a node, this would mean that they are siblings. Therefore, we would return false.
- There is a cleaner implementation for the level order traversal for this problem, though. For each node, we can add a delimiter to the queue after its children are added. These delimiters help us define boundaries for each parent and the siblings that are confined within those.

Algorithm
----------
1. Do a level order traversal of the tree using a queue.
2. For every node that is popped off the queue, check if the node is either Node x or Node y. If it is, then for the first time, set both siblings and cousins flags as true. The flags are set as true to mark the possibility of siblings or cousins.
3. To distinguish siblings from cousins, we insert markers in the queue. After inserting the children for each node, we also insert a null marker. This marker defines a boundary for each set of siblings and hence helps us to differentiate a pair of siblings from cousins.
4. Whenever we encounter the null marker during our traversal, we set the siblings flag to false. This is because the marker marks the end of the siblings territory.
5. The second time we encounter a node which is equal to Node x or Node y we will have clarity about whether or not we are still within the siblings boundary. If we are within the siblings boundary, i.e. if the siblings flag is still true, then we return false. Otherwise, we return true.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public boolean isCousins(TreeNode root, int x, int y) {

        // Queue for BFS
        Queue <TreeNode> queue = new LinkedList <> ();
        queue.add(root);

        while (!queue.isEmpty()) {
            boolean siblings = false;
            boolean cousins = false;

            int nodesAtDepth = queue.size();

            for (int i = 0; i < nodesAtDepth; i++) {
                // FIFO
                TreeNode node = queue.remove();

                // Encountered the marker.
                // Siblings should be set to false as we are crossing the boundary.
                if (node == null) {
                    siblings = false;
                } else {
                    if (node.val == x || node.val == y) {
                        // Set both the siblings and cousins flag to true
                        // for a potential first sibling/cousin found.
                        if (!cousins) {
                            siblings = cousins = true;
                        } else {
                            // If the siblings flag is still true this means we are still
                            // within the siblings boundary and hence the nodes are not cousins.
                            return !siblings;
                        }
                    }

                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                    // Adding the null marker for the siblings
                    queue.add(null);
                }
            }
            // After the end of a level if `cousins` is set to true
            // This means we found only one node at this level
            if (cousins) return false;
        }
        return false;
    }
}

/*
Complexity Analysis
Time Complexity: O(N), where NNN is the number of nodes in the binary tree. In the worst case, we might have to visit all the nodes of the binary tree. Similar to approach 1 this approach would also have a complexity of O(N) when the Node x and Node y are present at the last level of the binary tree. The algorithm would follow the standard BFS approach and end up in checking each node before discovering the desired nodes.

Space Complexity: O(N). In the worst case, we need to store all the nodes of the last level in the queue. The last level of a binary tree can have a maximum of N/2 nodes. Not to forget we would also need space for N/4 null markers, one for each pair of siblings. That results in a space complexity of O(3N/4) = O(N) (You are right Big-O notation doesn't care about constants).
*/
