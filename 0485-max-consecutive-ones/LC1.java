/*
Approach: One pass
Intuition
The intuition is pretty simple. We keep a count of the number of 1's encountered. And reset the count whenever we encounter anything other than 1 (which is 0 for this problem). Thus, maintaining count of 1's between zeros or rather maintaining counts of contiguous 1's. It's the same as keeping a track of the number of hours of sleep you had, without waking up in between.

Algorithm
1. Maintain a counter for the number of 1's.
2. Increment the counter by 1, whenever you encounter a 1.
3. Whenever you encounter a 0
   a. Use the current count of 1's to find the maximum contiguous 1's till now.
   b. Afterwards, reset the counter for 1's to 0.
4. Return the maximum in the end.
*/

class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
    int count = 0;
    int maxCount = 0;
    for(int i = 0; i < nums.length; i++) {
      if(nums[i] == 1) {
        // Increment the count of 1's by one.
        count += 1;
      } else {
        // Find the maximum till now.
        maxCount = Math.max(maxCount, count);
        // Reset count of 1.
        count = 0;
      }
    }
    return Math.max(maxCount, count);
  }
}

/*
Complexity Analysis
Time Complexity: O(N), where N is the number of elements in the array.
Space Complexity: O(1). We do not use any extra space.
  */
