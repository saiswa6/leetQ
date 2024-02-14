/*
Approach #1: Binary Search on Logarithms [Accepted]
Intuition
Because log⁡(∏ixi)=∑ilog⁡xi , we can reduce the problem to subarray sums instead of subarray products. The motivation for this is that the product of some arbitrary subarray may be way too large (potentially 1000^50000), and also dealing with sums gives us some more familiarity as it becomes similar to other problems we may have solved before.

Algorithm
After this transformation where every value x becomes log(x), let us take prefix sums prefix[i+1] = nums[0] + nums[1] + ... + nums[i]. Now we are left with the problem of finding, for each i, the largest j so that nums[i] + ... + nums[j] = prefix[j] - prefix[i] < k.

Because prefix is a monotone increasing array, this can be solved with binary search. We add the width of the interval [i, j] to our answer, which counts all subarrays [i, k] with k <= j.

Complexity Analysis
Time Complexity: O(Nlog⁡N), where NNN is the length of nums. Inside our for loop, each binary search operation takes O(log⁡N) time.

Space Complexity: O(N), the space used by prefix.
*/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            int lo = i + 1, hi = prefix.length;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (prefix[mi] < prefix[i] + logk - 1e-9) {
                    lo = mi + 1;
                }
                else hi = mi;
            }
            ans += lo - i - 1;
        }
        return ans;
    }
}
