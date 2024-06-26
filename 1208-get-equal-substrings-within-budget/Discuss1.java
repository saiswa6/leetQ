/*
Intuition
Change the input of string s and t into an array of difference.
Then it's a standard sliding window problem.


Complexity
Time O(N) for one pass
Space O(1)
*/

    public int equalSubstring(String s, String t, int k) {
        int n = s.length(), i = 0, j;
        for (j = 0; j < n; ++j) {
            k -= Math.abs(s.charAt(j) - t.charAt(j));
            if (k < 0) {
                k += Math.abs(s.charAt(i) - t.charAt(i));
                ++i;
            }
        }
        return j - i;
    }

