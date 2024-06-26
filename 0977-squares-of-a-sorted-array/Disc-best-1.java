/*

*/

class Solution {
    public int[] sortedSquares(int[] A) {
        if (A == null) return null;
        
        int [] ans = new int[A.length];
     
        int start = 0, end = A.length - 1;
        int i = end; // insert position.
        while (start <= end) { // <  or <=  ?   be careful about ==
            int pow1 = A[start] * A[start];   // compare with squares only. Then, no need of absolute and directly insert after comparision.
            int pow2 = A[end] * A[end];
            if (pow1 > pow2) {
                ans[i--] = pow1;
                start++;
            } else {
                ans[i--] = pow2;
                end--;
            }
        }
        return ans;
    }
}
