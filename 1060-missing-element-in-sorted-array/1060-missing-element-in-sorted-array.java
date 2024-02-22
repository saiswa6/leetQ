class Solution {
    public int missingElement(int[] nums, int k) {
        int left = 0;
        int right = nums.length ;

        while(left < right) {
            int mid = left + (right - left) / 2;

            int count_missing = nums[mid] - nums[0] - mid;
            if(count_missing >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[0] + left + k - 1;
    }
}