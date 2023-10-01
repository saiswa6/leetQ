/*
Approach 3: Hashset
Since triplets must sum up to the target value, we can try the hash table approach from the Two Sum solution. 

We move our pivot element nums[i] and analyze elements to its right. We find all pairs whose sum is equal -nums[i] using the Two Sum: One-pass Hash Table approach, so that the sum of the pivot element (nums[i]) and the pair (-nums[i]) is equal to zero.

To do that, we process each element nums[j] to the right of the pivot, and check whether a complement -nums[i] - nums[j] is already in the hashset. If it is, we found a triplet. Then, we add nums[j] to the hashset, so it can be used as a complement from that point on.

Like in the approach above, we will also sort the array so we can skip repeated values. We provide a different way to avoid duplicates in the "No-Sort" approach below.

Algorithm
The main function is the same as in the Two Pointers approach above. Here, we use twoSum (instead of twoSumII), modified to produce triplets and skip repeating values.

1. For the main function:
- Sort the input array nums.
- Iterate through the array:
    If the current value is greater than zero, break from the loop. Remaining values cannot sum to zero.
    If the current value is the same as the one before, skip it.
    Otherwise, call twoSum for the current position i.

2. For twoSum function:
- For each index j > i in A:
    Compute complement value as -nums[i] - nums[j].
    If complement exists in hashset seen:
        We found a triplet - add it to the result res.
        Increment j while the next value is the same as before to avoid duplicates in the result.
    Add nums[j] to hashset seen

3. Return the result res.

Time Complexity: O(n2). twoSum is O(n), and we call it n times.
Sorting the array takes O(nlog⁡n), so overall complexity is O(nlog⁡n+n2). This is asymptotically equivalent to O(n2).

Space Complexity: O(n) for the hashset.
*/


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, res);
            }
        return res;
    }
    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        var seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    ++j;
            }
            seen.add(nums[j]);
        }
    }
}
