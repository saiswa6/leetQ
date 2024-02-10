/*
Approach 2: Binary Search
- We can adapt the 3Sum Smaller: Binary Search approach to this problem.
- In the two pointers approach, we fix one number and use two pointers to enumerate pairs. Here, we fix two numbers, and use a binary search to find the third complement number. This is less efficient than the two pointers approach, however, it could be more intuitive to come up with.
- Note that we may not find the exact complement number, so we check the difference between the complement and two numbers: the next higher and the previous lower. For example, if the complement is 42, and our array is [-10, -4, 15, 30, 60], the next higher is 60 (so the difference is -18), and the previous lower is 30 (and the difference is 12).

Algorithm
1. Initialize the minimum difference diff with a large value.
2. Sort the input array nums.
3. Iterate through the array (outer loop):
   - For the current position i, iterate through the array starting from j = i + 1 (inner loop):
      - Binary-search for complement (target - nums[i] - nums[j]) in the rest of the array.
      - For the next higher value, check its absolute difference with complement against diff.
      - For the previous lower value, check its absolute difference with complement against diff.
      - Update diff based on the smallest absolute difference.
   - If diff is zero, break from the loop.
4. Return the value of the closest triplet, which is target - diff.

Complexity Analysis
Time Complexity: O(n2log⁡n). Binary search takes O(log⁡n), and we do it n times in the inner loop. Since we are going through n elements in the outer loop, the overall complexity is O(n2log⁡n).

Space Complexity: from O(log⁡n) to O(n), depending on the implementation of the sorting algorithm.
*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            for (int j = i + 1; j < sz - 1; ++j) {
                int complement = target - nums[i] - nums[j];
                var idx = Arrays.binarySearch(nums, j + 1, sz - 1, complement);
                int hi = idx >= 0 ? idx : ~idx, lo = hi - 1;
                if (hi < sz && Math.abs(complement - nums[hi]) < Math.abs(diff))
                    diff = complement - nums[hi];
                if (lo > j && Math.abs(complement - nums[lo]) < Math.abs(diff))
                    diff = complement - nums[lo];
            }
        }
        return target - diff;
    }
}

/*
Further Thoughts
- 3Sum is a well-known problem with many variations and its own Wikipedia page.
- For an interview, we recommend focusing on the Two Pointers approach above. It's easier to get it right and adapt for other variations of 3Sum. Interviewers love asking follow-up problems like 3Sum Smaller!
- If an interviewer asks you whether you can achieve O(1) memory complexity, you can use the selection sort instead of a built-in sort in the Two Pointers approach. It will make it a bit slower, though the overall time complexity will be still O(n2)
*/
