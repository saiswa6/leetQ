/*
Approach 1: Two Sets
- Intuition
- The naive approach would be to iterate along the first array nums1 and to check for each value if this value is nums2 or not. If yes - add the value to the output. Such an approach would result in a pretty bad O(n×m) time complexity, where n and m are arrays' lengths.

- To solve the problem in linear time, let's use the structure set, which provides in/contains operation in O(1) time in average case.

The idea is to convert both arrays into sets, and then iterate over the smallest set checking the presence of each element in the larger set. The time complexity of this approach is O(n+m) in the average case.

Complexity Analysis
Time complexity: O(n+m), where n and m are arrays' lengths. O(n) time is used to convert nums1 into set, O(m) time is used to convert nums2, and contains/in operations are O(1) in the average case.

Space complexity: O(m+n) in the worst case when all elements in the arrays are different.
*/

class Solution {
  public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> set1 = new HashSet<Integer>();
    for (Integer n : nums1) set1.add(n);
    HashSet<Integer> set2 = new HashSet<Integer>();
    for (Integer n : nums2) set2.add(n);

    if (set1.size() < set2.size()) return set_intersection(set1, set2);
    else return set_intersection(set2, set1);
  }

  public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
    int [] output = new int[set1.size()];
    int idx = 0;
    for (Integer s : set1)
      if (set2.contains(s)) output[idx++] = s;

    return Arrays.copyOf(output, idx);
  }
}
