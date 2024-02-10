/*
Approach 2: Binary Search
Intuition
- Before we solve the threeSum problem, solve this simpler twoSum version:
- Given a nums array, find the number of index pairs i, j with 0≤i<j<n that satisfy the condition nums[i]+nums[j]<target

- If we sort the array first, then we can apply binary search to find the largest index j such that nums[i]+nums[j]<target for each i. Once we have found that largest index j, we know there must be j−i pairs that satisfy the above condition with i's value fixed.
- Finally, we can now apply the twoSum solution to threeSum directly by wrapping an outer for-loop around it.

- Note that in the above binary search we choose the upper middle element (left+right+1/2) instead of the lower middle element (left+right2). The reason is due to the terminating condition when there are two elements left. If we chose the lower middle element and the condition nums[mid]<target evaluates to true, then the loop would never terminate. Choosing the upper middle element will guarantee termination.

Complexity analysis
Time complexity: O(n2log⁡n).
The binarySearch function takes O(log⁡n) time, therefore the twoSumSmaller takes O(nlog⁡n). The threeSumSmaller wraps with another for-loop, and therefore is O(n2log⁡n) time.

Space complexity: O(1) because no additional data structures are used.
*/

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        for (int i = startIndex; i < nums.length - 1; i++) {
            int j = binarySearch(nums, i, target - nums[i]);
            sum += j - i;
        }
        return sum;
    }

    private int binarySearch(int[] nums, int startIndex, int target) {
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
