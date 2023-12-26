/*
Approach 2: Dynamic Programming
Intuition
In brute force, we iterate over the left and right parts again and again just to find the highest bar size upto that index. But, this could be stored. Voila, dynamic programming.

Algorithm
Find maximum height of bar from the left end upto an index i in the array left_max.
Find maximum height of bar from the right end upto an index i in the array right_max.
Iterate over the height array and update ans:
Add min⁡(left_max[i],right_max[i])−height[i] to ans

Complexity analysis
Time complexity: O(n)
We store the maximum heights upto a point using 2 iterations of O(n) each.
We finally update ans\text{ans}ans using the stored values in O(n).

Space complexity: O(n) extra space.
Additional O(n) space for left_max and right_max arrays than in Approach 1.

*/

class Solution {
public:
    int trap(vector<int>& height)
    {
        if(height.empty())
            return 0;
        int ans = 0;
        int size = height.size();
        vector<int> left_max(size), right_max(size);
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }
};
