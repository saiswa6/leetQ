/*
Approach 2: Using previously established next pointers
Intuition
- Let's look at the two types of next pointer connections we need to establish for a given tree.
1. This first case is the one where we establish the next pointers between the two children of a given node. This is the easier of the two cases since both the children are accessible via the same node. We can simply do the following to establish this connection.
    node.left.next = node.right
2. This next case is not too straightforward to handle. In addition to establishing the next pointers between the nodes having a common parent, we also need to set-up the correct pointers between nodes which have a different parent.
   - If we simply had the parent pointers available with each node, this problem would have been trivial to solve. However, we don't have any such pointers available. The basic idea for this approach is based on the fact that:
   - We only move on to the level N+1 when we are done establishing the next pointers for the level N. Since we have access to all the nodes on a particular level via the next pointers, we can use these next pointers to establish the connections for the next level or the level containing their children.

Algorithm
============
1. We start at the root node. Since there are no more nodes to process on the first level or level 0, we can establish the next pointers on the next level i.e. level 1. An important thing to remember in this algorithm is that we establish the next pointers for a level NNN while we are still on level N−1N-1N−1 and once we are done establishing these new connections, we move on to NNN and do the same thing for N+1.
2. As we just said, when we go over the nodes of a particular level, their next pointers are already established. This is what helps get rid of the queue data structure from the previous approach and helps save space. To start on a particular level, we just need the leftmost node. From there on out, its just a linked list traversal.
3. Based on these ideas, our algorithm will have the following pseudocode:

       leftmost = root
       while (leftmost.left != null)
       {
           head = leftmost
           while (head != null)
           {
               1) Establish Connection 1
               2) Establish Connection 2 using next pointers
               head = head.next
           }
           leftmost = leftmost.left
       }

4. The Connection 1 and Connection 2 mentioned above correspond to the two kinds of connections we looked at earlier on in the intuition section of this approach.
  a. The first one is fairly simple to establish given that it's between the two nodes having a common parent. So, we could simply do something like this to link the two children:
      node.left.next = node.right
  b. For the second type of connection, we have to make use of the next pointers on the current level. Remember that this second type of connection is between nodes that have different parents. More specifically, it's the link between the right child of a node and the left child of the next node. Since we already have the next pointers set up on the current level, we use this to set up the correct pointers on the next level.
      node.right.next = node.next.left

5. Once we are done with the current level, we move on to the next one. One last thing that's left here is to update the leftmost node. We need that node to start traversal on a particular level. Think of it as the head of the linked list. Since this is a perfect binary tree, the leftmost node will always be the left child of the current leftmost node. The only nodes which don't have any children are the ones on the final level and these would be the leaves of the tree.

*/

class Solution {
    public Node connect(Node root) {
        
        if (root == null) {
            return root;
        }
        
        // Start with the root node. There are no next pointers
        // that need to be set up on the first level
        Node leftmost = root;
        
        // Once we reach the final level, we are done
        while (leftmost.left != null) {
            
            // Iterate the "linked list" starting from the head
            // node and using the next pointers, establish the 
            // corresponding links for the next level
            Node head = leftmost;
            
            while (head != null) {
                
                // CONNECTION 1
                head.left.next = head.right;
                
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                
                // Progress along the list (nodes on the current level)
                head = head.next;
            }
            
            // Move onto the next level
            leftmost = leftmost.left;
        }
        
        return root;
    }
}

/*
Complexity Analysis
Time Complexity: O(N) since we process each node exactly once.
Space Complexity: O(1) since we don't make use of any additional data structure for traversing nodes on a particular level like the previous approach does.
*/
