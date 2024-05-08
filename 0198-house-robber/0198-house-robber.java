class Solution {
    ;
    public int rob(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        return robHelper(n - 1, nums, dp);
    }

    public int robHelper(int index, int nums[], int dp[]) {
        if (index == 0) {
            return nums[index];
        }
        if (index < 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        int pick = nums[index] + robHelper(index - 2, nums, dp);
        int notPick = 0 + robHelper(index - 1, nums, dp);

        dp[index] = Math.max(pick, notPick);
        return dp[index];
    }

    /*
     * public int rob(int[] nums) {
     * int n = nums.length;
     * int dp[] = new int[n];
     * return robHelper(n - 1 , nums);
     * }
     * 
     * public int robHelper(int index, int nums[]) {
     * if(index == 0) {
     * return nums[index];
     * }
     * if(index < 0) {
     * return 0;
     * }
     * 
     * int pick = nums[index] + robHelper(index - 2, nums);
     * int notPick = 0 + robHelper(index - 1, nums);
     * 
     * return Math.max(pick, notPick);
     * }
     */
}