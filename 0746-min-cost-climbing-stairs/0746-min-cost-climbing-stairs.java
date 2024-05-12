class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        return helper(cost, n );
    }

    private int helper(int[] cost, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 0;
        }

        int singleStep = cost[n - 1]  + helper(cost, n - 1);
        int doubleStep = cost[n - 2]  + helper(cost, n - 2);

        return Math.min(singleStep, doubleStep);
    }
}