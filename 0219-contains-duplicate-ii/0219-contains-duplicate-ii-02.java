/*
Approach #2: Binary Search Tree
Intuition
Keep a sliding window of k elements using self-balancing Binary Search Tree (BST).

Algorithm
The key to improve upon Approach #1 above is to reduce the search time of the previous k elements. Can we use an auxiliary data structure to maintain a sliding window of k elements with more efficient search, delete, and insert operations? Since elements in the sliding window are strictly First-In-First-Out (FIFO), queue is a natural data structure. A queue using a linked list implementation supports constant time delete and insert operations, however the search costs linear time, which is no better than Approach #1.

A better option is to use a self-balancing BST. A BST supports search, delete and insert operations all in O(log⁡k) time, where k is the number of elements in the BST. In most interviews you are not required to implement a self-balancing BST, so you may think of it as a black box. Most programming languages provide implementations of this useful data structure in its standard library. In Java, you may use a TreeSet or a TreeMap. In C++ STL, you may use a std::set or a std::map.

If you already have such a data structure available, the pseudocode is:

Loop through the array, for each element do
= Search current element in the BST, return true if found
= Put the current element in the BST
= If the size of the BST is larger than k, remove the oldest item.
Return false

Complexity Analysis
Time complexity : O(nlog⁡(min⁡(k,n))). We do n operations of search, delete and insert. Each operation costs logarithmic time complexity in the sliding window which size is min⁡(k,n). Note that even if k can be greater than n, the window size can never exceed n.

Space complexity: O(min⁡(n,k)).
Space is the size of the sliding window which should not exceed n or k.

Note = The algorithm still gets Time Limit Exceeded for large nnn and kkk.
*/

public boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> set = new TreeSet<>();
    for (int i = 0; i < nums.length; ++i) {
        if (set.contains(nums[i])) return true;
        set.add(nums[i]);
        if (set.size() > k) {
            set.remove(nums[i - k]);
        }
    }
    return false;
}
// Time Limit Exceeded.
