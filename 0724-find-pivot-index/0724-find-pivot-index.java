// 1st : Brute Force - check for each element. T.C : O(N2)
// 2nd : Use prefix and suffix array. check from left for 1st occurence. T.C : O(N2) , S.C : O(1)
// 3rd : No Space. It is LC Solution. Check LC1.


class Solution {
    public int pivotIndex(int[] nums) {
        int length = nums.length;
        int prefixSum[] = new int[length];
        int suffixSum[] = new int[length];

        prefixSum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i]; // Be careful while calculating prefixSum.
            // Last prefixSum Element + current Element
        }

        suffixSum[length - 1] = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i]; // Be careful while calculating prefixSum.
            // Next suffixSum Element + current Element
        }

        for (int i = 0; i < length; i++) {
            if (prefixSum[i] == suffixSum[i]) {// FInd first one from left side.
                return i;
            }
        }
        return -1;
    }
}
