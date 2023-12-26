/*For each element in the array, we find the maximum level of water it can trap after the rain, which is equal to the minimum of maximum height of bars on both the sides minus its own height.

Algorithm

Initialize ans=0
Iterate the array from left to right:
Initialize left_max=0 and right_max=0
    Iterate from the current element to the beginning of array updating:
       left_max=max⁡(left_max,height[j])
    Iterate from the current element to the end of array updating:
       right_max=max⁡(right_max,height[j])
    Add min⁡(left_max,right_max)−height[i] to ans


Complexity Analysis:
Time complexity: O(n2). For each element of array, we iterate the left and right parts.
Space complexity: O(1) extra space.
  */

class Solution {
public:
    int trap(vector<int>& height)
    {
        int ans = 0;
        int size = height.size();
        for (int i = 1; i < size - 1; i++) {
            int left_max = 0, right_max = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                left_max = max(left_max, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                right_max = max(right_max, height[j]);
            }
            ans += min(left_max, right_max) - height[i];
        }
        return ans;
    }
};
