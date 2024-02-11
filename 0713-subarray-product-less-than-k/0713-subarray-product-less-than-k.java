class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int answer = 0;
        int product = 1;

        for( ; right < nums.length; right++) {
            product = product * nums[right];

            while(product >= k) {
                product = product / nums[left];
                left++;
            }
            answer += (right - left + 1);
        }
        return answer;
    }
}