/*
Soln 1 :
Initial result array.
Loop twice on the string S.
First forward pass to find shortest distant to character on left.
Second backward pass to find shortest distant to character on right.

  */
    public int[] shortestToChar(String S, char C) {
        int n = S.length(), pos = -n, res[] = new int[n];
        for (int i = 0; i < n; ++i) {
            if (S.charAt(i) == C) pos = i;
            res[i] = i - pos;
        }
        for (int i = pos - 1; i >= 0; --i) {
            if (S.charAt(i) == C)  pos = i;
            res[i] = Math.min(res[i], pos - i);
        }
        return res;
    }

//Solution DP
  /** "loveleetcode" "e"
 *  1. put 0 at all position equals to e, and max at all other position
 *     we will get [max, max, max, 0, max, 0, 0, max, max, max, max, 0]
 *  2. scan from left to right, if =max, skip, else dist[i+1] = Math.min(dp[i] + 1, dp[i+1]), 
 *     we can get [max, max, max, 0, 1, 0, 0, 1, 2, 3, 4, 0]
 *  3. scan from right to left, use dp[i-1] = Math.min(dp[i] + 1, dp[i-1])
 *     we will get[3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0] 
 */
class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) continue;
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n-1; i++) {
            if (dist[i] == Integer.MAX_VALUE) continue;
            else dist[i + 1] = Math.min(dist[i+1], dist[i] + 1);
        }
        for (int i = n-1; i > 0; i--) {
            dist[i-1] = Math.min(dist[i-1], dist[i] + 1);
        }
        return dist; 
    }
}
