/*
Follow up
- What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Click here to learn how to insert a node into a BST and delete a node from a BST, the time complexity of these operations is O(H), where H is a height of the binary tree. H=log⁡N for the balanced tree and H=N for a skewed tree.
- Hence without any optimisation insert/delete + search of kth element has O(2H+k) complexity. How to optimize that?
- That's a design question, basically, we're asked to implement a structure that contains a BST inside and optimises the following operations :
   1. Insert
   2. Delete
   3. Find kth smallest
- Seems like a database description, isn't it? Let's use here the same logic as for LRU cache design, and combine an indexing structure (we could keep BST here) with a doubly-linked list.

Such a structure would provide:
O(H) time for the insert and delete.
O(k) for the search of kth smallest.

The overall time complexity for insert/delete + search of kth smallest is O(H+k) instead of O(2H+k).

Complexity Analysis
Time complexity for insert/delete + search of kth smallest: O(H+k), where H is a tree height. O(log⁡N+k) in the average case, O(N+k) in the worst case.
Space complexity : O(N) to keep the linked list.
*/

//Read comments section and update here - In comments section, there are many answerss for this.
