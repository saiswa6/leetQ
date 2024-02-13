// Take 930. Binary Subarrays With Sum (Medium) for example, if we want to use the "find maximum sliding window template" without this trick, we would need to use 3 pointers -- i as the left edge of the window with goal number of 1s in it, j as the left edge of the window with goal - 1 number of 1s in it, and k as the right edge.

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


// Approach 2
/*
Solution 1: HashMap
Count the occurrence of all prefix sum.

I didn't notice that the array contains only 0 and 1,
so this solution also works if have negatives.

Space O(N)
Time O(N)
*/

    public int numSubarraysWithSum(int[] A, int S) {
        int psum = 0, res = 0, count[] = new int[A.length + 1];
        count[0] = 1;
        for (int i : A) {
            psum += i;
            if (psum >= S)
                res += count[psum - S];
            count[psum]++;
        }
        return res;
    }
