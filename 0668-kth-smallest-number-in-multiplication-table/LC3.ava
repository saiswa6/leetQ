/*
Approach #3: Binary Search [Accepted]
Intuition
As k and m*n are up to 9∗10 pow 8, linear solutions will not work. This motivates solutions with log⁡ complexity, such as binary search.

Algorithm
Let's do the binary search for the answer A.

- Say enough(x) is true if and only if there are k or more values in the multiplication table that are less than or equal to x. Colloquially, enough describes whether x is large enough to be the kth value in the multiplication table.
- Then (for our answer A), whenever x ≥ A, enough(x) is True; and whenever x < A, enough(x) is False.
- In our binary search, our loop invariant is enough(hi) = True. In the beginning, enough(m*n) = True, and whenever hi is set, it is set to a value that is "enough" (enough(mi) = True). That means hi will be the lowest such value at the end of our binary search.
- This leaves us with the task of counting how many values are less than or equal to x. For each of m rows, the ith row looks like [i, 2*i, 3*i, ..., n*i]. The largest possible k*i ≤ x that could appear is k = x // i. However, if x is really big, then perhaps k > n, so in total there are min(k, n) = min(x // i, n) values in that row that are less than or equal to x.
- After we have the count of how many values in the table are less than or equal to x, by the definition of enough(x), we want to know if that count is greater than or equal to k.

Complexity Analysis
Time Complexity: O(m∗log⁡(m∗n)). Our binary search divides the interval [lo, hi] into half at each step. At each step, we call enough which requires O(m) time.

Space Complexity: O(1). We only keep integers in memory during our intermediate calculations.
*/

class Solution {
    public boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }

    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!enough(mi, m, n, k)) lo = mi + 1;
            else hi = mi;
        }
        return lo;
    }
}
