class Solution {
    public int[] minOperations(String boxes) {
        /*
         * Similar to 238. Product of Array Except Self and 42. Trapping Rain Water.
         * For each index, the cost to move all boxes to it is sum of the cost leftCost
         * to move all left boxes to it, and the cost rightCost to move all right boxes
         * to it.
         * 
         * leftCost for all indexes can be calculted using a single pass from left to
         * right.
         * rightCost for all indexes can be calculted using a single pass from right to
         * left.
         * 
         * Example:
         * boxes 11010
         * leftCount 01223
         * leftCost 01358
         * rightCount 21100
         * rightCost 42100
         * ans 43458
         */
        int n = boxes.length();

        int[] left = new int[n];
        int[] right = new int[n];
        int[] ans = new int[n];

        int count = boxes.charAt(0) - '0';
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + count;
            count += boxes.charAt(i) - '0';
        }

        count = boxes.charAt(n - 1) - '0';
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + count;
            count += boxes.charAt(i) - '0';
        }

        for (int i = 0; i < n; i++) {
            ans[i] = left[i] + right[i];
        }

        return ans;
    }
}
