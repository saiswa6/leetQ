/*
Algorithm

We use hash table to avoid the O(n) time required for searching the elements.

1. Iterate through all elements in nums and set up key/value pair.
2. Return the element which appeared only once.

Complexity Analysis

Time complexity : O(n⋅1)=O(n). Time complexity of for loop is O(n). 

Space complexity : O(n). The space required by hash_table is equal to the number of elements in nums.
*/

class Solution {
  public int singleNumber(int[] nums) {
    HashMap<Integer, Integer> hash_table = new HashMap<>();

    for (int i : nums) {
      hash_table.put(i, hash_table.getOrDefault(i, 0) + 1);
    }
    for (int i : nums) {
      if (hash_table.get(i) == 1) {
        return i;
      }
    }
    return 0;
  }
}
