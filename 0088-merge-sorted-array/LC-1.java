/*
Approach 1: Merge and sort
Intuition
A naive approach would be to simply write the values from nums2 into the end of nums1, and then sort nums1. Remember that we do not need to return a value, as we should modify nums1 in-place. While straightforward to code, this approach has a high time complexity as we're not taking advantage of the existing sorting.

Time complexity: O((n+m)log⁡(n+m)).
The cost of sorting a list of length x using a built-in sorting algorithm is O(xlog⁡x. Because in this case, we're sorting a list of length m+n, we get a total time complexity of O((n+m)log⁡(n+m).

Space complexity: O(n), but it can vary.
Most programming languages have a built-in sorting algorithm that uses O(n) space.
*/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
