/*
Approach 1: Sort
Intuition and Algorithm
Create an array of the squares of each element, and sort them.

Complexity Analysis :
Time Complexity: O(Nlog⁡N), where N is the length of A.
Space complexity : O(N) or O(log⁡N)
The space complexity of the sorting algorithm depends on the implementation of each program language.
For instance, the list.sort() function in Python is implemented with the Timsort algorithm whose space complexity is O(N).
*/

class Solution {
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; ++i)
            ans[i] = A[i] * A[i];

        Arrays.sort(ans);
        return ans;
    }
}
