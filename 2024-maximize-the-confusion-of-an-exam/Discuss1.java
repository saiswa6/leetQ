/*
Link : https://leetcode.com/problems/maximize-the-confusion-of-an-exam/solutions/1499049/java-c-python-sliding-window-strict-o-n/

Intuition

Explanation
maxf means the max frequency of the same character in the sliding window.
To better understand the solution,
you can firstly replace maxf with max(count.values()),
Now I improve from O(26n) to O(n) using a just variable maxf.


Complexity
Time O(n)
Space O(128)

*/

    public int characterReplacement(String s, int k) {
        int res = 0, maxf = 0, count[] = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            maxf = Math.max(maxf, ++count[s.charAt(i)]);
            if (res - maxf < k)
                res++;
            else
                count[s.charAt(i - res)]--;
        }
        return res;
    }


/*
Solution 2
Another version of same idea.
In a more standard format of sliding window.
Maybe easier to understand

Time O(N)
Space O(26)
*/

    public int characterReplacement(String s, int k) {
        int maxf = 0, i = 0, n = s.length(), count[] = new int[26];
        for (int j = 0; j < n; ++j) {
            maxf = Math.max(maxf, ++count[s.charAt(j) - 'A']);
            if (j - i + 1 > maxf + k)
                --count[s.charAt(i++) - 'A'];
        }
        return n - i;
    }
