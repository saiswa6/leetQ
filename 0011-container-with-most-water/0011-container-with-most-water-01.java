/*
Approach 1: Brute Force
Algorithm
In this case, we will simply consider the area for every possible pair of lines and find out the maximum area out of those.

Complexity Analysis

Time complexity: O(n2. Calculating area for all n(n−1)/2 height pairs.
Space complexity: O(1). Constant extra space is used.
*/

public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0;
      // A for loop for left pointer
        for (int left = 0; left < height.length; left++) {
          // A for loop for right pointer
            for (int right = left + 1; right < height.length; right++) {
                int width = right - left;
              // Update max area
                maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * width);
            }
        }
      // Return max area
        return maxarea;
    }
}
