class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int start = 0;
        double answer = Double.MIN_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            if (end - start >= k) {
                sum = sum - nums[start];
                start++;
            }

            if (end - start == k - 1) {
                answer = Math.max(answer, (double) sum / k);
            }
        }

        return answer;
    }
}