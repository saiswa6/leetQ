class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int start = 0;
        int end = 0;
        int length = nums.length;
        int ans = 0;

        for(int i = 0; i < length; i++) {
            if(nums[i] == 1) {
                if(i == 0 || nums[i-1] == 0) { // if element is starting one or number 1 just after 0, point it as start.
                    start = i;
                }
                if(i == length - 1 || nums[i + 1] == 0) { // if element is last one one or number 1 just before 0, point it as end and calculate window length.
                    end = i;
                    ans = Math.max(ans, end - start + 1);
                }
            }
        }
        return ans;
    }
}
