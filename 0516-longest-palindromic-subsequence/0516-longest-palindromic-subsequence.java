
class Solution {
    public int longestPalindromeSubseq(String s) {

        String reverseString = new StringBuilder(s).reverse().toString();
        int length1 = s.length() + 1;
        int length2 = reverseString.length() + 1;

        int dp[][] = new int[length1][length2];

        for (int i = 0; i < length1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < length2; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                if (s.charAt(i - 1) == reverseString.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[length1 - 1][length2 - 1];
    }
}