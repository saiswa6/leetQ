class Solution {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int answer = 0;

        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if(nums[i] + nums[j] == k) {
                answer++;
                i++;
                j--;
            } else if (nums[i] + nums[j] < k) {
                i++;
            } else {
                j--;
            }
        }

        return answer;
    }
}