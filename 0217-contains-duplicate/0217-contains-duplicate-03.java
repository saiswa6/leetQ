/*
Approach #3 (Hash Table) [Accepted]
Intuition
Utilize a dynamic data structure that supports fast search and insert operations.

Algorithm
From Approach #1 we know that search operations is O(n) in an unsorted array and we did so repeatedly. Utilizing a data structure with faster search time will speed up the entire algorithm.

There are many data structures commonly used as dynamic sets such as Binary Search Tree and Hash Table. The operations we need to support here are search() and insert(). For a self-balancing Binary Search Tree (TreeSet or TreeMap in Java), search() and insert() are both OO(logn) time. For a Hash Table (HashSet or HashMap in Java), search() and insert() are both O(1)O(1)O(1) on average. Therefore, by using hash table, we can achieve linear time complexity for finding the duplicate in an unsorted array.

Complexity Analysis
Time complexity: O(n)
We do search() and insert() for nnn times and each operation takes constant time.

Space complexity: O(n)
The space used by a hash table is linear with the number of elements in it.

Note
For certain test cases with not very large n, the runtime of this method can be slower than Approach #2. The reason is hash table has some overhead in maintaining its property. One should keep in mind that real world performance can be different from what the Big-O notation says. The Big-O notation only tells us that for sufficiently large input, one will be faster than the other. Therefore, when n is not sufficiently large, an O(n) algorithm can be slower than an O(nlogn) algorithm.
*/

public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length);
    for (int x: nums) {
        if (set.contains(x)) return true;
        set.add(x);
    }
    return false;
}
