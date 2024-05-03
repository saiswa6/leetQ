class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int start = 0;
        double answer = -Double.MAX_VALUE; // (Link :
                                           // https://stackoverflow.com/questions/3884793/why-is-double-min-value-in-not-negative#:~:text=in%20the%20documents%2C-,Double.,%2C%202%5E(%2D1074).&text=The%20mantissa%2C%20always%20a%20positive,of%20the%20floating%2Dpoint%20number.)
        // Double.MIN_VALUE represent Double.MIN_VALUE is the least positive value
        // (since that represents the least possible magnitude)
        // Double.MAX_VALUE should be seen as maximum magnitude
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
