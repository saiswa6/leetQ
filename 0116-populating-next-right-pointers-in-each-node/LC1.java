/*
Approach 1: Level Order Traversal
Intuition
- There are two basic kinds of traversals on a tree or a graph. One is where we explore the tree in a depth first manner i.e. one branch at a time. The other one is where we traverse the tree breadth-wise i.e. we explore one level of the tree before moving on to the next one. For trees, we have further classifications of the depth first traversal approach called preorder, inorder, and the postorder traversals. Breadth first approach to exploring a tree is based on the concept of the level of a node. The level of a node is its depth or distance from the root node. We process all the nodes on one level before moving on to the next one.
- Now that we have the basics out of the way, it's pretty evident that the problem statement strongly hints at a breadth first kind of a solution. We need to link all the nodes together which lie on the same level and the level order or the breadth first traversal gives us access to all such nodes.

Algorithm
1. Initialize a queue, Q which we will be making use of during our traversal. There are multiple ways to implement the level order traversal especially when it comes to identifying the level of a particular node.
   a. We can add a pair of (node,level) to the queue and whenever we add the children of a node, we add (node.left,    parent_level+1) and (node.right, parent_level+1). This approach wouldn't be very efficient for our algorithm since we need all the nodes on the same level and we would need another data structure just for that.
   b. A more memory efficient way of segregating the same level nodes is to use some demarcation between the levels. Usually, we insert a NULL entry in the queue which marks the end of the previous level and the start of the next level. This is a great approach but again, it would still consume some memory proportional to the number of levels in the tree.
  c. The approach we will be using here would have a nested loop structure to get around the requirement of a NULL pointer. Essentially, at each step, we record the size of the queue and that always corresponds to all the nodes on a particular level. Once we have this size, we only process these many elements and no more. By the time we are done processing size number of elements, the queue would contain all the nodes on the next level. Here's a pseudocode for the same:

       while (!Q.empty())
       {
           size = Q.size()
           for i in range 0..size
           {
               node = Q.pop()
               Q.push(node.left)
               Q.push(node.right)
           }
       }
2. We start off by adding the root of the tree in the queue. Since there is just one node on the level 0, we don't need to establish any connections and can move onto the while loop.
3. The first while loop from the pseudocode above essentially iterates over each level one by one and the inner for loop iterates over all the nodes on the particular level. Since we have access to all the nodes on the same level, we can establish the next pointers easily.
4. When we pop a node inside the for loop from the pseudocode above, we add its children at the back of the queue. Also, the element at the head of the queue is the next element in order, on the current level. So, we can easily establish the new pointers.
*/

class Solution {
    public Node connect(Node root) {
        
        if (root == null) {
            return root;
        }
        
        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<Node> Q = new LinkedList<Node>(); 
        Q.add(root);
        
        // Outer while loop which iterates over 
        // each level
        while (Q.size() > 0) {
            
            // Note the size of the queue
            int size = Q.size();
            
            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {
                
                // Pop a node from the front of the queue
                Node node = Q.poll();
                
                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only 
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {   //=============================THIS IS IMPORTANT
                    node.next = Q.peek();
                }
                
                // Add the children, if any, to the back of
                // the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        
        // Since the tree has now been modified, return the root node
        return root;
    }
}

/*
Complexity Analysis
Time Complexity: O(N) since we process each node exactly once. Note that processing a node in this context means popping the node from the queue and then establishing the next pointers.

Space Complexity: O(N). This is a perfect binary tree which means the last level contains N/2 nodes. The space complexity for breadth first traversal is the space occupied by the queue which is dependent upon the maximum number of nodes in particular level. So, in this case, the space complexity would be O(N).
*/
