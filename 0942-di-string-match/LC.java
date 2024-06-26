/*
Approach 1: Ad-Hoc
Intuition
If we see say S[0] == 'I', we can always put 0 as the first element; similarly, if we see S[0] == 'D', we can always put N as the first element.
Say we have a match for the rest of the string S[1], S[2], ... using N distinct elements. Notice it doesn't matter what the elements are, only that they are distinct and totally ordered. Then, putting 0 or N at the first character will match, and the rest of the elements (1, 2, ..., N or 0, 1, ..., N-1) can use the matching we have.

Algorithm
Keep track of the smallest and largest element we haven't placed. If we see an 'I', place the small element; otherwise place the large element.

Complexity Analysis
Time Complexity: O(N), where N is the length of S.
Space Complexity: O(1), we don't count the answer as part of the space complexity.
*/

class Solution {
    public int[] diStringMatch(String S) {
        int N = S.length();
        int lo = 0, hi = N;
        int[] ans = new int[N + 1];
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == 'I')
                ans[i] = lo++;
            else
                ans[i] = hi--;
        }

        ans[N] = lo;
        return ans;
    }
}
