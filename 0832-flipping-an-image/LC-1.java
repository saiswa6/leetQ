/*
Approach #1: Direct [Accepted]
Intuition and Algorithm
We can do this in place. In each row, the ith value from the left is equal to the inverse of the ith value from the right.
We use (C+1) / 2 (with floor division) to iterate over all indexes i in the first half of the row, including the center.

Negation using XOR
0^1 = 1
1^1 = 0

Complexity Analysis :
Time Complexity: O(N), where N is the total number of elements in A.
Space Complexity: O(1) in additional space complexity.
*/

class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int C = A[0].length;
        for (int[] row: A)
            for (int i = 0; i < (C + 1) / 2; ++i) { // This covers for both 0dd and even, no extra condition needed.
                int tmp = row[i] ^ 1;
                row[i] = row[C - 1 - i] ^ 1;
                row[C - 1 - i] = tmp;
            }

        return A;
    }
}
