class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            if(height[left] < height[right]) {
                int sampleAns = (right - left) * height[left];
                ans = sampleAns > ans ? sampleAns : ans;
                left++;
            } else {
                int sampleAns = (right - left) * height[right];
                ans = sampleAns > ans ? sampleAns : ans;
                right--;
            }
        }

        return ans;
    }
}