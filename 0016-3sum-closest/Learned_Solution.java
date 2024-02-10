class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int diff = Integer.MAX_VALUE;
        int ans = 0;
        
        for(int first = 0; first < length - 2 && diff != 0 ; first++) {
            int second = first + 1;
            int third = length - 1;
            
            while(second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                if(Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if(sum < target) {
                    second++;
                } else {
                    third--;
                }
            }
        }

        return target - diff;
    }
}
