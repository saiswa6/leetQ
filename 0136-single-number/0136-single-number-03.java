/*
Concept
2∗(a+b+c)−(a+a+b+b+c)=c

Complexity Analysis
Time complexity : O(n+n)=O(n). sum will call next to iterate through nums\text{nums}nums.
We can see it as sum(list(i, for i in nums)) which means the time complexity is O(n) because of the number of elements(n) in nums.

Space complexity : O(n+n)=O(n). set needs space for the elements in nums
*/

class Solution {
  public int singleNumber(int[] nums) {
    int sumOfSet = 0, sumOfNums = 0;
    Set<Integer> set = new HashSet();

    for (int num : nums) {
      if (!set.contains(num)) {
        set.add(num);
        sumOfSet += num;
      }
      sumOfNums += num;
    }
    return 2 * sumOfSet - sumOfNums;
  }
}
