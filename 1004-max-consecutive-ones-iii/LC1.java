/*
The problem has asked for longest contiguous subarray that contains only 1s. What makes this problem a little trickier is the k flips allowed from 0 --> 1. This means a contiguous subarray of 1's might not just contain 1's but also may contain some 0's. The number of 0's allowed in a given subarray is given by k.


Approach: Sliding Window
Intuition
- To find the longest subarray with contiguous 1's we might need to find all the subarrays first. But do we really need to do that? If we find all the subarrays we are essentially finding out so many unnecessary overlapping subarrays too.
- We can use a simple sliding window approach to solve this problem.
- In any sliding window based problem we have two pointers. One right pointer whose job is to expand the current window and then we have the left pointer whose job is to contract a given window. At any point in time only one of these pointers move and the other one remains fixed.
- The solution is pretty intuitive. We keep expanding the window by moving the right pointer. When the window has reached the limit of 0's allowed, we contract (if possible) and save the longest window till now.
- The answer is the longest desirable window.

Algorithm
1. Initialize two pointers. The two pointers help us to mark the left and right end of the window/subarray with contiguous 1's.
    left = 0, right = 0, window_size = 0
2. We use the right pointer to expand the window until the window/subarray is desirable. i.e. number of 0's in the window are in the allowed range of [0, k].
3. Once we have a window which has more than the allowed number of 0's, we can move the left pointer ahead one by one until we encounter 0 on the left too. This step ensures we are throwing out the extra zero.

Complexity Analysis
Time Complexity: O(N), where NNN is the number of elements in the array. In worst case we might end up visiting every element of array twice, once by left pointer and once by right pointer.
Space Complexity: O(1). We do not use any extra space.
*/

class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, right;
        for (right = 0; right < nums.length; right++) {
            // If we included a zero in the window we reduce the value of k.
            // Since k is the maximum zeros allowed in a window.
            if (nums[right] == 0) {
                k--;
            }
            // A negative k denotes we have consumed all allowed flips and window has
            // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
            if (k < 0) {
                // If the left element to be thrown out is zero we increase k.
                k += 1 - nums[left];
                left++;
            }
        }     
        return right - left;
    }
}



// Best Shrinkable Sollution from 2 ms
class Solution {
    public int longestOnes(int[] nums, int k) {
        int start=0;
        int end=0;
        int zeros=0;

        while(end<nums.length){
            if(nums[end] == 0){
                zeros++;
            }
            end++;
            if(zeros>k){    // Shrinkable vERSION
                if(nums[start] == 0){
                    zeros--;
                }
                start++;
            }
        }
        return end-start;
    }
}
