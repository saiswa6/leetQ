class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    public int atMost(int[] nums, int goal) {
        int left = 0;
        int right = 0;
        int answer = 0;
        int sum = 0;

        for( ; right < nums.length ; right++) {
            sum += nums[right];

            while(left <= right && sum > goal) {
                sum -= nums[left];
                left++;
            }
            answer = answer + (right - left + 1);
        }
        return answer;
    }
}
