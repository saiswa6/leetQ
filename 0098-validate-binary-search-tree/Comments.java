/*
Heap is a BT not a BST.
in Heap, value of each node in lesser than value of its children.
in BST, value of each node is between the values of its children.

 the key problem isn't about whether heap is implemented as arrays, but whether it's a binary "search" tree. Simply by the definition of BST, the root of a BST cannot be its min nor max, unless the tree is significantly unbalanced and has only its left/right subtree filled (which breaks the O(logN) advantage of using a heap and the convenience of array indexing). A heap is a complete binary tree, not a binary "search" tree, and it is implemented as a flat array thanks to the "complete" property.

 =======================
why BFS is slower than DFS?
 
There's two primary factors here:
Cache Locality
It's because the stack data structure backed by an array/vector is laid out in contiguous blocks of memory. This is awesome for performance, because when you read an item from memory, the neighboring items become accessible in L1/L2 cache (relatively very fast). Queues are generally backed by linked lists, which can be sparse in memory and is prone to cache misses. On a cache miss CPU has to wait to retrieve the data from RAM which is relatively very slow.

Data Structure Size
(Stack)Array/Vector has 1 (large) block of memory and a few supporting variables (e.g. size). An equivalently sized Queue(LinkedList) uses all the memory a Stack does PLUS has a pointer to the next node for each item.

Although all the algorithms will have the same Time Complexity, DFS generally will have faster execution speed because of the above. The exception to this is if BFS can be short-circuited faster (e.g. for shortest path problems)
*/
