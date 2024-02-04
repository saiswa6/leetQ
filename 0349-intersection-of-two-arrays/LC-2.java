/*
Approach 2: Built-in Set Intersection
Intuition : -
There are built-in intersection facilities, which provide O(n+m) time complexity in the average case and O(n×m) time complexity in the worst case.

In Python it's intersection operator, in Java - retainAll() function.

Complexity Analysis
Time complexity: O(n+m) in the average case and O(n×m) in the worst case when the load factor is high enough.
Space complexity: O(n+m) in the worst case when all elements in the arrays are different.
*/

class Solution {
  public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> set1 = new HashSet<Integer>();
    for (Integer n : nums1) set1.add(n);
    HashSet<Integer> set2 = new HashSet<Integer>();
    for (Integer n : nums2) set2.add(n);

    set1.retainAll(set2);

    int [] output = new int[set1.size()];
    int idx = 0;
    for (int s : set1) output[idx++] = s;
    return output;
  }
}
