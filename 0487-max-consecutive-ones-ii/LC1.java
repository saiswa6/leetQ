/*
Approach 1: Brute Force

Algorithm:
- A brute force solution usually involves trying to check every single possibility. It'll look something like this:
  - Check every possible consecutive sequence
  - Count how many 0's are in each sequence
  - If our sequence has one or fewer 0's, check if that's the longest consecutive sequence of 1's.

**Interview Tip: Often the interviewer doesn't need to see you code the brute force solution. State the brute force approach out loud and discuss his/her expectations. Either way, communicating proactively will give you major bonus points.

Complexity Analysis
Let n be equal to the length of the input nums array.
Time complexity : O(n2). The nested for loops turn our approach into a quadratic solution because, for every index, we have to check every other index in the array.

Space complexity : O(1). We are using 4 variables: left, right, numZeroes, and longestSequence. The number of variables is constant and does not change based on the size of the input.
*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int longestSequence = 0;
        for (int left = 0; left < nums.length; left++) {
            int numZeroes = 0;

            //Check every consecutive sequence
            for (int right = left; right < nums.length; right++) {
                // Count how many 0's
                if (nums[right] == 0) {
                    numZeroes += 1;
                }
                // Update answer if it's valid
                if (numZeroes <= 1) {
                    longestSequence = Math.max(longestSequence, right - left + 1);
                }
            }
        }
        return longestSequence;
    }
}
