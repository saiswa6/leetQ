/*
C++ Maximum Sliding Window Cheatsheet Template!


Problems Solvable using this template
3. Longest Substring Without Repeating Characters
159. Longest Substring with At Most Two Distinct Characters (Medium)
340. Longest Substring with At Most K Distinct Characters
424. Longest Repeating Character Replacement
487. Max Consecutive Ones II
713. Subarray Product Less Than K
1004. Max Consecutive Ones III
1208. Get Equal Substrings Within Budget (Medium)
1493. Longest Subarray of 1's After Deleting One Element
1695. Maximum Erasure Value
1838. Frequency of the Most Frequent Element
2009. Minimum Number of Operations to Make Array Continuous
2024. Maximize the Confusion of an Exam
The following problems are also solvable using the shrinkable template with the "At Most to Equal" trick

930. Binary Subarrays With Sum (Medium)
992. Subarrays with K Different Integers
1248. Count Number of Nice Subarrays (Medium)
2062. Count Vowel Substrings of a String (Easy)


Template 1: Sliding Window (Shrinkable)
===========================================
The best template I've found so far:

int i = 0, j = 0, ans = 0;
for (; j < N; ++j) {
    // CODE: use A[j] to update state which might make the window invalid
    for (; invalid(); ++i) { // when invalid, keep shrinking the left edge until it's valid again
        // CODE: update state using A[i]
    }
    ans = max(ans, j - i + 1); // the window [i, j] is the maximum window we've found thus far
}
return ans;
- Essentially, we want to keep the window valid at the end of each outer for loop.

Solution for this question:
 - What should we use as the state? It should be the sum of numbers in the window
 - How to determine invalid? The window is invalid if (j - i + 1) * A[j] - sum > k.

 
// OJ: https://leetcode.com/problems/frequency-of-the-most-frequent-element/
// Author: github.com/lzl124631x
// Time: O(NlogN)
// Space: O(1)
class Solution {
public:
    int maxFrequency(vector<int>& A, int k) {
        sort(begin(A), end(A));
        long i = 0, N = A.size(), ans = 1, sum = 0;
        for (int j = 0; j < N; ++j) {
            sum += A[j];
            while ((j - i + 1) * A[j] - sum > k) sum -= A[i++];
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};


FAQ:
====================
1) Why is the time complexity O(NlogN)?
 - The sorting takes O(NlogN). The two pointer part only takes O(N) because both the pointers i and j traverse the array ONLY ONCE.

2) Why is (j - i + 1) * A[j] - sum <= k valid?
 - (j - i + 1) is the length of the window [i, j]. We want to increase all the numbers in the window to equal A[j], the number of operations needed is (j - i + 1) * A[j] - sum which should be <= k. For example, assume the window is [1,2,3], increasing all the numbers to 3 will take 3 * 3 - (1 + 2 + 3) operations.

Template 2: Sliding Window (Non-shrinkable)
============================================
int i = 0, j = 0;
for (; j < N; ++j) {
    // CODE: use A[j] to update state which might make the window invalid
    if (invalid()) { // Increment the left edge ONLY when the window is invalid. In this way, the window GROWs when it's valid, and SHIFTs when it's invalid
        // CODE: update state using A[i]
        ++i;
    }
    // after `++j` in the for loop, this window `[i, j)` of length `j - i` MIGHT be valid.
}
return j - i; // There must be a maximum window of size `j - i`.

- Essentially, we GROW the window when it's valid, and SHIFT the window when it's invalid.
- Note that there is only a SINGLE for loop now!

Solution for this question:
---------------------------
// OJ: https://leetcode.com/problems/frequency-of-the-most-frequent-element/
// Author: github.com/lzl124631x
// Time: O(NlogN)
// Space: O(1)
class Solution {
public:
    int maxFrequency(vector<int>& A, int k) {
        sort(begin(A), end(A));
        long i = 0, j = 0, N = A.size(), sum = 0;
        for (; j < N; ++j) {
            sum += A[j];
            if ((j - i + 1) * A[j] - sum > k) sum -= A[i++];
        }
        return j - i;
    }
};
*/
