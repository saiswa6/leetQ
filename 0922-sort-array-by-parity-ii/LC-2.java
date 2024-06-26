/*
Intuition
We are motivated (perhaps by the interviewer) to pursue a solution where we modify the original array A in place.
First, it is enough to put all even elements in the correct place, since all odd elements will be in the correct place too. So let's only focus on A[0], A[2], A[4], ...
Ideally, we would like to have some partition where everything to the left is already correct, and everything to the right is undecided.
Indeed, this idea works if we separate it into two slices even = A[0], A[2], A[4], ... and odd = A[1], A[3], A[5], .... Our invariant will be that everything less than i in the even slice is correct, and everything less than j in the odd slice is correct.

Algorithm
For each even i, let's make A[i] even. To do it, we will draft an element from the odd slice. We pass j through the odd slice until we find an even element, then swap. Our invariant is maintained, so the algorithm is correct.

Complexity Analysis
Time Complexity: O(N), where N is the length of A.
Space Complexity: O(1).
*/

class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i += 2)
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1)
                    j += 2;

                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

        return A;
    }
}
