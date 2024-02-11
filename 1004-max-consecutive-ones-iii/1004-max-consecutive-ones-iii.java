class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int answer = 0;
        int zeroCount = 0;

        for( ; right < nums.length ; right++) {
            if(nums[right] == 0) {
                zeroCount++;
            }

            if(zeroCount > k) 
            {
                if(nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            //answer = Math.max(answer, right - left + 1);
        }

        return right-left;
    }
}