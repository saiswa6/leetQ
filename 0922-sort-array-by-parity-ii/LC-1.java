/*
Approach 1: Two Pass
Intuition and Algorithm
Read all the even integers and put them into places ans[0], ans[2], ans[4], and so on.
Then, read all the odd integers and put them into places ans[1], ans[3], ans[5], etc.

Complexity Analysis
Time Complexity: O(N), where NNN is the length of A.
Space Complexity: O(N).
*/

class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int N = A.length;
        int[] ans = new int[N];

        int t = 0;
        for (int x: A) if (x % 2 == 0) {
            ans[t] = x;
            t += 2;
        }

        t = 1;
        for (int x: A) if (x % 2 == 1) {
            ans[t] = x;
            t += 2;
        }

        return ans;
    }
}
