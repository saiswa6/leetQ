/*
Overview
The problem is a standard String Matching Problem. It can be stated as
- Find one or more occurrences of a pattern x0x1…xm−1  in a text y0y1…yn−1. Elements of xxx and yyy are characters taken from the set Σ\SigmaΣ.
- In this problem, we have to find the first occurrence of needle in a haystack. The characters are taken from the set of lowercase English Characters {a, b, c, ... , y, z}.

There are multiple applications of String Matching, and that's why a lot of research has been done in this field. Multiple algorithms have been devised to solve this problem. Some of the application includes:
    Spell Checker
    Plagiarism Detection
    Text Editors
    Spam Filters
    Digital Forensics
    Matching DNA Sequences
    Intrusion Detection
    Search Engines
    Bioinformatics and Cheminformatics
    Information Retrieval System
    Language Syntax Checker
    
Throughout the article, we will use
m (or m in code) to denote the length of the needle and
n (or n in code) to denote the length of the haystack.
**************************************************************************************************************
Approach 1: Sliding Window
Intuition
- The most naïve approach is to traverse each possible substring of length m in the haystack and check if it is equal to the needle.
   1. First substring of length m will start at index 0 in the haystack and will end at index (m - 1) + 0.
   2. The second substring of length m will start at index 1 in the haystack and will end at index (m - 1) + 1, i.e. m.
   3. The third substring of length m will start at index 2 in the haystack and will end at index (m - 1) + 2 i.e. m + 1.
- Thus, if a substring ends at index (m - 1) + k, then it starts at index k. We know that the last substring ends at index (n - 1). Thus, to find starting index k of the last substring, we can equate (n - 1) with (m - 1) + k, to get k = (n-1) - (m-1), or k = n - m.
   1. Thus, the last substring of length mmm will start at index n - m in the haystack and will end at index n - 1.
- We will create a window of size m and slide it across the haystack. We will keep track of the starting index of the window in a variable window_start. For every window_start, we will iterate till window_start + m. During each iteration,
  1. if the ith character in the window is equal to the ith character in the needle, then we will increment i by 1.
  2. If the ith character in the window is not equal to the ith character in the needle, then we conclude that the substring of length mmm starting from index window_start cannot be equal to the needle, and we will reset window_start to window_start + 1.
- If all the ith characters in the window are equal to the ith characters of needle, then we will return the window_start.

Algorithm
1. Declare m and n as variables, and initialize them with the length of needle and haystack respectively.
2. Declare the window_start variable, and initialize it with 0. Now, iterate window_start till starting index of the last substring of length mmm, i.e till n - m.
3. For each window_start, iterate variable i from 0 to m - 1. Check if the ith character in the window i.e index window_start + i is equal to the ith character in the needle, if yes, then increment i by 1. If not, reset window_start to window_start + 1.
4. If all the ith characters in the window are equal to the ith characters of needle, then return the window_start.
5. If we are done iterating over all values of window_start and none of them return a match, then return -1.

Example: Let haystack be "mississippi" and needle be “issipi”. The sliding window algorithm would get executed like this.


Complexity Analysis :
Time complexity: O(nm). For every window_start, we may have to iterate at most mmm times. There are n−m+1 such window_start's. Thus, it is O((n−m+1)⋅m), which is O(nm)m).

One example where the worst case occurs is when needle is "aaaaab", while haystack is all a's (Let's say, "aaaaaaaaaa"). In this, we always have to take the last character of needle into comparison to conclude that the current m-substring is not equal to needle. Thus, we will have to iterate mmm times for every window_start.

Space complexity: O(1).

There are a handful of variables in the code (m, n, i, window_start), and all of them use constant space, hence, the space complexity is constant.
*/

class Solution {
    public int strStr(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();

        for (int windowStart = 0; windowStart <= n - m; windowStart++) {
            for (int i = 0; i < m; i++) {
                if (needle.charAt(i) != haystack.charAt(windowStart + i)) {
                    break;
                }
                if (i == m - 1) {
                    return windowStart;
                }
            }
        }

        return -1;
    }
}
