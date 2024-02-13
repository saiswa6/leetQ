/*
🌟 Sliding Window "At Most to Equal" trick
NOTE: this is an extension to my "C++ Maximum Sliding Window Cheatsheet Template!". Please make sure you are familiar with this template first.

- For a find maximum sliding window problem that is looking for windows with exactly k something in it, we can convert it to a problem that is looking for at most k something in it.
- It's because "number of windows with exactly k something in it" = "number of windows with at most k something in it" - "number of windows with at most k - 1 something in it".

- Take 930. Binary Subarrays With Sum (Medium) for example, if we want to use the "find maximum sliding window template" without this trick, we would need to use 3 pointers -- i as the left edge of the window with goal number of 1s in it, j as the left edge of the window with goal - 1 number of 1s in it, and k as the right edge.
*/

// OJ: https://leetcode.com/problems/binary-subarrays-with-sum/
// Author: github.com/lzl124631x
// Time: O(N)
// Space: O(1)
class Solution {
public:
    int numSubarraysWithSum(vector<int>& A, int goal) {
        int i = 0, j = 0, k = 0, N = A.size(), sum1 = 0, sum2 = 0, ans = 0;
        for (; k < N; ++k) {
            sum1 += A[k];
            sum2 += A[k];
            while (i <= k && sum1 > goal) sum1 -= A[i++];
            while (j <= k && sum2 > goal - 1) sum2 -= A[j++];
            ans += j - i;
        }
        return ans;
    }
};

//This code is repetitive -- we need to do similar jobs for i and j, and keep states for both windows.
// By extracting an atMost function, we make the code cleaner and less error-prone.

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
