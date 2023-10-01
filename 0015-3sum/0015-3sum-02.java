/*
Before jumping in, let's check the existing solutions and determine the best conceivable runtime (BCR) for 3Sum:

Two Sum uses a hashmap to find complement values, and therefore achieves O(N) time complexity.
Two Sum II uses the two pointers pattern and also has O(N) time complexity for a sorted array. We can use this approach for any array if we sort it first, which bumps the time complexity to O(nlog⁡n).
Considering that there is one more dimension in 3Sum, it sounds reasonable to shoot for O(n2) time complexity as our BCR.

==============================================================================================
Approach 2: Two Pointers
We will follow the same two pointers pattern as in Two Sum II. It requires the array to be sorted, so we'll do that first. As our BCR is O(n2), sorting the array would not change the overall time complexity.

To make sure the result contains unique triplets, we need to skip duplicate values. It is easy to do because repeating values are next to each other in a sorted array.

After sorting the array, we move our pivot element nums[i] and analyze elements to its right. We find all pairs whose sum is equal -nums[i] using the two pointers pattern, so that the sum of the pivot element (nums[i]) and the pair (-nums[i]) is equal to zero.

Algorithm
=========
The implementation is straightforward - we just need to modify twoSumII to produce triplets and skip repeating values.

1. For the main function:
- Sort the input array nums.
- Iterate through the array:
    If the current value is greater than zero, break from the loop. Remaining values cannot sum to zero.
    If the current value is the same as the one before, skip it.
    Otherwise, call twoSumII for the current position i.
    
2. For twoSumII function:
- Set the low pointer lo to i + 1, and high pointer hi to the last index.
- While low pointer is smaller than high:
    If sum of nums[i] + nums[lo] + nums[hi] is less than zero, increment lo.
    If sum is greater than zero, decrement hi.
    Otherwise, we found a triplet:
       Add it to the result res.
       Decrement hi and increment lo.
       Increment lo while the next value is the same as before to avoid duplicates in the result.
3. Return the result res.

Complexity Analysis
Time Complexity: O(n2). twoSumII is O(n), and we call it n times.
Sorting the array takes O(nlog⁡n), so overall complexity is O(nlog⁡n+n2). This is asymptotically equivalent to O(n2).

Space Complexity: from O(log⁡n) to O(n), depending on the implementation of the sorting algorithm. For the purpose of complexity analysis, we ignore the memory required for the output.
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        return res;
    }
    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }
}
