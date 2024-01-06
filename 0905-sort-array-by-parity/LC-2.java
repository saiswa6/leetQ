/*
Approach 2: Two Pass
Intuition and Algorithm
Write all the even elements first, then write all the odd elements.

Complexity Analysis
Time Complexity: O(N), where NNN is the length of A.
Space Complexity: O(N), the space used by the answer.
*/

class Solution {
    public int[] sortArrayByParity(int[] A) {
        int[] ans = new int[A.length];
        int t = 0;

        for (int i = 0; i < A.length; ++i)
            if (A[i] % 2 == 0)
                ans[t++] = A[i];

        for (int i = 0; i < A.length; ++i)
            if (A[i] % 2 == 1)
                ans[t++] = A[i];

        return ans;
    }
}
