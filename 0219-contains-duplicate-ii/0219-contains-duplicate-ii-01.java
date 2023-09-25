/*
Approach 1
Approach #1: Naive Linear Search
Intuition
Look for duplicate element in the previous k elements.

Algorithm
This algorithm is the same as Approach #1 in Contains Duplicate solution, except that it looks at previous k elements instead of all its previous elements.

Another perspective of this algorithm is to keep a virtual sliding window of the previous k elements. We scan for the duplicate in this window.

Complexity Analysis
Time complexity : O(nmin⁡(k,n)).
It costs O(min⁡(k,n)) time for each linear search. Apparently we do at most n comparisons in one search even if k can be larger than n.

Space complexity : O(1)
*/

public boolean containsNearbyDuplicate(int[] nums, int k) {
    for (int i = 0; i < nums.length; ++i) {
        for (int j = Math.max(i - k, 0); j < i; ++j) {
            if (nums[i] == nums[j]) return true;
        }
    }
    return false;
}
// Time Limit Exceeded.
