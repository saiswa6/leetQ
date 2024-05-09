class Solution {
    public int maxSubArray(int[] nums) {
        int maxSubArraySum = nums[0];
        int currentSubArraySum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSubArraySum = Math.max(nums[i], currentSubArraySum + nums[i]);
            maxSubArraySum = Math.max(currentSubArraySum, maxSubArraySum);
        }

        return maxSubArraySum;
    }
}