/*
Solution 2: Sliding Window
We have done this hundreds time.

It's because "number of windows with exactly k something in it" = "number of windows with at most k something in it" - "number of windows with at most k - 1 something in it".

Space O(1)
Time O(N)
*/

// OJ: https://leetcode.com/problems/binary-subarrays-with-sum/
// Author: github.com/lzl124631x
// Time: O(N)
// Space: O(1)
class Solution {
    int atMost(vector<int> &A, int goal) {
        int i = 0, j = 0, N = A.size(), sum = 0, ans = 0;
        for (; j < N; ++j) {
            sum += A[j];
            while (i <= j && sum > goal) sum -= A[i++];
            ans += j - i;
        }
        return ans;
    }
public:
    int numSubarraysWithSum(vector<int>& A, int goal) {
        return atMost(A, goal) - atMost(A, goal - 1);
    }
};

/////////////////////////////////////////////////////////////////////Similar 
    public int numSubarraysWithSum(int[] A, int S) {
        return atMost(A, S) - atMost(A, S - 1);
    }

    public int atMost(int[] A, int S) {
        if (S < 0) return 0;
        int res = 0, i = 0, n = A.length;
        for (int j = 0; j < n; j++) {
            S -= A[j];
            while (S < 0)
                S += A[i++];
            res += j - i + 1;
        }
        return res;
    }
