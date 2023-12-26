/*
Algorithm
1. The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line. Further, the farther the lines, the more will be the area obtained.
2. We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines. Further, we maintain a variable maxarea to store the maximum area obtained till now. At every step, we find out the area formed between them, update maxarea, and move the pointer pointing to the shorter line towards the other end by one step.

How does this approach work?
Initially, we consider the area constituting the exterior most lines. Now, to maximize the area, we need to consider the area between the lines of larger lengths. If we try to move the pointer at the longer line inwards, we won't gain any increase in area, since it is limited by the shorter line. But moving the shorter line's pointer could turn out to be beneficial, as per the same argument, despite the reduction in the width. This is done since a relatively longer line obtained by moving the shorter line's pointer might overcome the reduction in area caused by the width reduction.

--FROM DISCUSSION
- I found a lot of the discussion and proof about this quite opaque, but one thing helped it finally clicked for me (which is sort of proof by contradiction i guess)
- You have two heights H_left and H_right, and H_right < H_left, then we know we have two choices, we want to move one of them. If we move the larger one, we cannot increase the height for the simple reason that we are always limited by the shortest, and we would be decreasing j-i, the width as well.
- To clarify: let's say we kept the shortest forever, what would happen? Well, j-i would decrease, and either we come across a taller block, which doesn't matter because our shorter one we kept only mattered, or we find a shorter one, in which case that one matters.
- Either way we end up with a smaller area, so we must move the shorter one because moving the larger one cannot give an increase in area.

Complexity Analysis
Time complexity: O(n). Single pass.
Space complexity: O(1). Constant space is used.
*/

public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0;
        int left = 0; 
        int right = height.length - 1;
        while (left < right) {
            int width = right - left;
            maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * width);
          // Move the smallest bar to next based on comparison
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxarea;
    }
}
