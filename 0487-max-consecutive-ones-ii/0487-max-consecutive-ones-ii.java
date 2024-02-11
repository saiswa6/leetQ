class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int right = 0;
        int answer = 0;
        int zeroCount = 0;

        for( ; right < nums.length ; right++) {
            if(nums[right] == 0) {
                zeroCount++;
            }

            while(zeroCount > 1) 
            {
                if(nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}