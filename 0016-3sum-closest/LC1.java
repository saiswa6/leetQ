/*
- This problem is a variation of 3Sum. The main difference is that the sum of a triplet is not necessarily equal to the target. Instead, the sum is in some relation with the target, which is closest to the target for this problem. In that sense, this problem shares similarities with 3Sum Smaller.

- Before jumping in, let's check solutions for the similar problems:
1. 3Sum fixes one number and uses either the two pointers pattern or a hash set to find complementary pairs. Thus, the time complexity is O(n2)
2. 3Sum Smaller, similarly to 3Sum, uses the two pointers pattern to enumerate smaller pairs. Note that we cannot use a hash set here because we do not have a specific value to look up.

- For the same reason as for 3Sum Smaller, we cannot use a hash set approach here. So, we will focus on the two pointers pattern and shoot for O(n2) time complexity as the best conceivable runtime (BCR).
*/

/*
Approach 1: Two Pointers
- The two pointers pattern requires the array to be sorted, so we do that first. As our BCR is O(n2), the sort operation would not change the overall time complexity.
- In the sorted array, we process each value from left to right. For value v, we need to find a pair which sum, ideally, is equal to target - v. We will follow the same two pointers approach as for 3Sum, however, since this 'ideal' pair may not exist, we will track the smallest absolute difference between the sum and the target. The two pointers approach naturally enumerates pairs so that the sum moves toward the target.

Algorithm
1. Initialize the minimum difference diff with a large value.
2. Sort the input array nums.
3. Iterate through the array:
   - For the current position i, set lo to i + 1, and hi to the last index.
   - While the lo pointer is smaller than hi:
      - Set sum to nums[i] + nums[lo] + nums[hi].
      - If the absolute difference between sum and target is smaller than the absolute value of diff:
        - Set diff to target - sum.
      - If sum is less than target, increment lo.
      - Else, decrement hi.
  - If diff is zero, break from the loop.
4 Return the value of the closest triplet, which is target - diff.


Complexity Analysis
Time Complexity: O(n2). We have outer and inner loops, each going through nnn elements.
- Sorting the array takes O(nlog⁡n), so overall complexity is O(nlog⁡n+n2). This is asymptotically equivalent to O(n2).

Space Complexity: from O(log⁡n) to O(n), depending on the implementation of the sorting algorithm.
*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            int lo = i + 1;
            int hi = sz - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum < target) {
                    ++lo;
                } else {
                    --hi;
                }
            }
        }
        return target - diff;
    }
}


/// Similar Discuss Solution

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2]; // arbitrary ans
        
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // if |sum - target| < |ans - target|, update ans
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                
                if (sum > target) k--;      // if sum > target, try smaller sum to approach target 
                else if (sum < target) j++; // if sum < target, try bigger sum to approach target 
                else return target;         // if sum == target, return target
            }
        }
        return ans;
    }
}
