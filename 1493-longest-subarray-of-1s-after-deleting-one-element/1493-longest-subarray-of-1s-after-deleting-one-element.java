class Solution {
    public int longestSubarray(int[] nums) {
        int answer = 0;
        int zeroCount = 0;
        int left = 0;
        
        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0) {
                zeroCount++;
            }

            if(zeroCount > 1) {
                if(nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            answer = Math.max(answer, right - left);
        }
        return answer;


        /* 
        int answer = 0;
        int zeroCount = 0;
        int left = 0;
        
        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0) {
                zeroCount++;
            }

            while(zeroCount > 1) {
                if(nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            answer = Math.max(answer, right - left);
        }
        return answer;
        */
    }
}