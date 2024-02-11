/*
Approach 2: Advanced Sliding Window
Intuition
- This approach is an extension of the previous one.
- Notice that the only thing we care about is the length of the longest window. We don't need to know what the window itself is. As we slide the window over the array, let's say we find a valid window with a length of len. We no longer care about any windows with lengths less than len, because they could not possibly improve on our answer.

- The purpose of the while loop in the previous approach is to shrink the window until it is valid again. In this approach, we will not shrink the window - we will just try to grow it as large as we can.

- We will keep the same condition in the while loop that checks if the current window [left, right] is valid, but instead of using a while loop, we will just use an if statement. This means left never increases by more than 1 per iteration. Because right also increases by 1 per iteration, if we cannot find a valid window, we will simply be sliding a window with static size across the array.

- However, if we add an element nums[right] to the window and the window is valid, then the if statement will not trigger, and left will not be incremented. Thus, we will increase our window size by 1. In this scenario, it implies the current window [left, right] is the best window we have seen so far.

- As you can see, it is actually impossible for our window size to decrease, since each iteration increases right by 1 and left by either 0 or 1.

- Because our window size cannot decrease, it also means that the size of the window always represents the length of the best window we have found so far - analogous to ans from the previous approach.

- At the end of the iteration, the size of our window is n - left. We return this as the answer.

Algorithm
1. Sort nums.
2. Initialize the following integers:
   - left = 0, the left pointer.
   - curr = 0, the sum of the elements currently in our window.
3. Iterate right over the indices of nums:
   - Consider target = nums[right].
   - Add target to curr.
   - If the size of the window right - left + 1 multiplied by target, minus curr is greater than k:
     - Subtract nums[left] from curr.
     - Increment left.
4. Return nums.length - left.

Complexity Analysis
Given n as the length of nums,
Time complexity: O(n⋅log⁡n)
- Each iteration of the for loop costs O(1). This means the sliding window process runs in O(n)
- However, we need to sort the array, which costs O(n⋅log⁡n)

Space Complexity: O(log⁡n) or O(n)
- We only use a few integer variables, but some space is used to sort.
- The space complexity of the sorting algorithm depends on the implementation of each programming language:

In Java, Arrays.sort() for primitives is implemented using a variant of the Quick Sort algorithm, which has a space complexity of O(log⁡n)
*/

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        long curr = 0;
        
        for (int right = 0; right < nums.length; right++) {
            int target = nums[right];
            curr += target;
            
            if ((right - left + 1) * target - curr > k) {
                curr -= nums[left];
                left++;
            }
        }
        
        return nums.length - left;
    }
}
