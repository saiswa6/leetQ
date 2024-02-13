/*
Solution 2: Sliding Window
We have done this hundreds time.

It's because "number of windows with exactly k something in it" = "number of windows with at most k something in it" - "number of windows with at most k - 1 something in it".


Intution :
This is my understanding of the sliding window solution. If there is anything wrong, please point it out.
atMost(A, S) counts the number of the subarrays of A the sum of which is at most(less than or equal to) S. Therefore, we can use atMost(A, S) - atMost(A, S - 1) to get the number of the subarrays the sum of which is exactly S.
In the atMost function, the i to j window represents the subarrays. We use the j pointer to expand the window, when the sum of all numbers in the window is bigger than S, it's time for us to move the i pointer to shorten the window. Through this process, we can count the number of the subarrays.

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
