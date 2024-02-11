/*
Intuition
Almost exactly same as 1004. Max Consecutive Ones III
Given an array A of 0s and 1s,
we may change up to k = 1 values from 0 to 1.
Return the length - 1 of the longest (contiguous) subarray that contains only 1s.

I felt we discuss the same sliding window solution every two weeks..
And I continue to receive complaints that I didn't give explantion again and again.


Complexity
Time O(N)
Space O(1)

Solution 1:
Sliding window with at most one 0 inside.
*/
    public int longestSubarray(int[] A) {
        int i = 0, j, k = 1, res = 0;
        for (j = 0; j < A.length; ++j) {
            if (A[j] == 0) {
                k--;
            }
            while (k < 0) {
                if (A[i] == 0) {
                    k++;
                }
                i++;
            }
            res = Math.max(res, j - i);
        }
        return res;
    }

/*

Solution 2
Sliding window that size doesn't shrink.


*/

    public int longestSubarray(int[] A) {
        int i = 0, j, k = 1;
        for (j = 0; j < A.length; ++j) {
            if (A[j] == 0) k--;
            if (k < 0 && A[i++] == 0) k++;
        }
        return j - i - 1;
    }
