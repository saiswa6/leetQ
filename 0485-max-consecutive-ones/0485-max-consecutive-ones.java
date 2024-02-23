class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int start = 0;
        int end = 0;
        int length = nums.length;
        int ans = 0;

        for(int i = 0; i < length; i++) {
            if(nums[i] == 0) {
                continue;
            } else {
                if(i == 0 || nums[i-1] == 0) {
                    start = i;
                }
                if(i == length - 1 || nums[i + 1] == 0) {
                    end = i;
                    ans = Math.max(ans, end - start + 1);
                }
            }
        }
        return ans;
    }
}