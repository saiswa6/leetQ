Approach 1: Binary Search
Intuition
- Starting from the brute-force approach, if we simply generate all possible subsequences and then iterate through each subsequence to find its maximum and minimum elements, this approach is feasible but the time complexity will be very high. Assuming the size of the array is n, we will have O(2 ^ n) non-empty subsequences, resulting in a time complexity of O(2^n*n). This method is very expensive and indicates that we need a more efficient way to traverse the subsequence.

Note that we only need to consider the minimum and maximum elements of a subsequence to determine whether it is valid, and elements with values between them do not affect our judgment. Therefore, we can traverse all possible (minimum, maximum) combinations and check if they are valid.

If there are k subsequences with min as the minimum value and max as the maximum value, we only need to check if min + max <= target. The number of such subsequences k depends on how many elements have values that are between min and max. Therefore, we need to sort nums first, so that the number of values between min and max can be represented by their index difference.

Here, we let left and right be the pointers to the minimum element and the maximum element. That is nums[left] = min.

Now we need to traverse each possible minimum value. For min = nums[left], we need to ensure that the subsequences with this number as the minimum value are valid, which means the maximum element of these subsequences cannot be greater than target-nums[left]. In other words, we need to find the largest element that is not greater than target-nums[left]. As shown in the picture below, we have found that nums[right] = 12 is the rightmost element that is smaller than or equal to 17 - nums[left], then we can freely pick elements within the range [left + 1, right] to make valid subsequences.

Since the array is already sorted, we can use binary search to find the insertion position of target - nums[left]. Note that we are looking for the rightmost element that is smaller than or equal to target - nums[left], and the binary search finds the index of the smallest element that is larger than target - nums[left]. Therefore, once we find the rightmost insertion position as k, we have right = k - 1.

Now we have the left and right indices left and right. For each number in the range [left + 1, right], there are 2 options: we can either take it or not take it, so there are a total of 2^(right - left) valid subsequences that have nums[left] as the minimum element.

Next, we move to the next min by moving the left pointer left one step to the right. We repeat the process by using binary search to find the new insertion position of target - nums[left].

Sometimes we may encounter situations where left > right and we don't need to consider these subsequences because they have already been calculated before and there is no need to recalculate them again.

Algorithm
1. Initialize answer = 0 and the length of nums as n.
2. Iterate over the left index left from 0 to n - 1, for each index left:
   - Use binary search to locate the rightmost index right which nums[right] <= target - nums[left].
   - If left <= right, count the total number of valid subsequences as 2^right - left
   - Increment answer by the number of valid subsequences.
3. Return answer once the iteration ends.

Complexity Analysis:
Let n be the length of the input string s.
Time complexity: O(n⋅log⁡n)
-We sort nums, which takes O(n⋅log⁡n) time.
-In Java, we initialize an array power and precompute the value of 2 to the power of each value. It takes O(n) time.
-We iterate over nums, for each left index left, we use binary search to locate the rightmost element that is smaller than or equal to target - nums[left].
-Binary search over array of size nnn takes O(log⁡n) on average.
-To sum up, the overall time complexity is O(n⋅log⁡n)

Space complexity: O(n)
-In Java, we initialize an array power and precompute the value of 2 to the power of each value. It takes O(n)O(n)O(n) space.
-In Python, sorting is done with Timsort, which uses O(n) space.
-During the iterate over left, we only need to compute right and update answer, both take constant space.
-Therefore, the space complexity is O(n).

class Solution {
    public static int binarySearchRightmost(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int mod = 1_000_000_007;
        Arrays.sort(nums);
        
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; ++i) {
            power[i] = (power[i - 1] * 2) % mod;
        }
        
        int answer = 0;

        // Iterate over each left pointer.
        for (int left = 0; left < n; left++) {
            // Find the insertion position for `target - nums[left]`
            // 'right' equals the insertion index minus 1.
            int right = binarySearchRightmost(nums, target - nums[left]) - 1;
            if (right >= left) {
                answer += power[right - left];
                answer %= mod;
            }
            
        }
        
        return answer;
    }
}
