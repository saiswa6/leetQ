/*
Approach 2: Sliding Window

Intuition
- The naive approach works but our interviewer is not convinced. Let's see how we can optimize the code we just wrote.
- The brute force solution had a time complexity of O(n2). What was the bottleneck? Checking every single consecutive sequence. Intuitively, we know we're doing repeated work because sequences overlap. We are checking consecutive sequences blindly. We need to establish some rules on how to move our sequence forward.

-  If our sequence is valid, let's continue expanding our sequence (because our goal is to get the largest sequence possible).
-  If our sequence is invalid, let's stop expanding and contract our sequence (because an invalid sequence will never count towards our largest sequence).

The pattern that comes to mind for expanding/contracting sequences is the sliding window. Let's define valid and invalid states.
  - Valid State = one or fewer 0's in our current sequence
  - Invalid State = two 0's in our current sequence

  
Algorithm
Great. How do we apply all this to the sliding window?

- Let's use left and right pointers to keep track of the current sequence a.k.a. our window. Let's expand our window by moving the right pointer forward until we reach a point where we have more than one 0 in our window. When we reach this invalid state, let's contract our window by moving the left pointer forward until we have a valid window again. By expanding and contracting our window from valid and invalid states, we are able to traverse the array efficiently without repeated overlapping work.

- Now we can break this approach down into a few actionable steps:

While our window is in the bounds of the array...
1. Add the rightmost element to our window.
2. Check if our window is invalid. If so, contract the window until valid.
3. Update the longest sequence we've seen so far.
4. Continue to expand our window.

Complexity Analysis
Let n be equal to the length of the input nums array.
Time complexity : O(n). Since both the pointers only move forward, each of the left and right pointers traverse a maximum of n steps. Therefore, the time complexity is O(n)
Space complexity : O(1). Same as the previous approach. We don't store anything other than variables. Thus, the space we use is constant because it is not correlated to the length of the input array.
*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int longestSequence = 0;
        int left = 0;
        int right = 0;
        int numZeroes = 0;

        // While our window is in bounds
        while (right < nums.length) {

            // Increase numZeroes if the rightmost element is 0
            if (nums[right] == 0) {
                numZeroes++;
            }

            //If our window is invalid, contract our window
            while (numZeroes == 2) {
                if (nums[left] == 0) {
                    numZeroes--;
                }
                left++;
            }

            // Update our longest sequence answer
            longestSequence = Math.max(longestSequence, right - left + 1);

            // Expand our window
            right++;
        }
        return longestSequence;
    }
}
