/*
Sliding Window (Shrinkable)
===========================
state: cnt[ch] is the number of occurrence of character ch in window.
invalid: cnt[s[j]] > 1 is invalid.
// OJ: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Author: github.com/lzl124631x
// Time: O(N)
// Space: O(1)
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int i = 0, j = 0, N = s.size(), ans = 0, cnt[128] = {};
        for (; j < N; ++j) {
            cnt[s[j]]++;
            while (cnt[s[j]] > 1) cnt[s[i++]]--;
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};


Sliding Window (Non-shrinkable)
===============================
Note that since the non-shrinkable window might include multiple duplicates, we need to add a variable to our state.

state: dup is the number of different kinds of characters that has duplicate in the window. For example, if window contains aabbc, then dup = 2 because a and b has duplicates.
invalid: dup > 0 is invalid
// OJ: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Author: github.com/lzl124631x
// Time: O(N)
// Space: O(1)
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int i = 0, j = 0, N = s.size(), cnt[128] = {}, dup = 0;
        for (; j < N; ++j) {
            dup += ++cnt[s[j]] == 2;
            if (dup) dup -= --cnt[s[i++]] == 1;
        }
        return j - i;
    }
};
*/
