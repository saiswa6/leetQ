class Solution {
    int dp[];

    public int tribonacci(int n) {
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(n);
    }

    public int helper(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        }
        int result = 0;

        result = result + (dp[n - 1] != -1 ? dp[n - 1] : helper(n - 1));
        result = result + (dp[n - 2] != -1 ? dp[n - 2] : helper(n - 2));
        result = result + (dp[n - 3] != -1 ? dp[n - 3] : helper(n - 3));
        return result;
    }
}

/*
 * Recursion :
 * class Solution {
 * 
 * public int tribonacci(int n) {
 * return helper(n);
 * }
 * 
 * public int helper(int n) {
 * if (n == 0) {
 * return 0;
 * } else if (n == 1) {
 * return 1;
 * } else if (n == 2) {
 * return 1;
 * }
 * 
 * return helper(n - 1) + helper(n - 2) + helper(n - 3);
 * }
 * }
 */