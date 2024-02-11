/*
Overview
- The primary challenge in this problem is to find an efficient way to get all possible longest substrings that contain no duplicate characters. To achieve this, we need to take advantage of Hash Table, which checks if a character occurs before quickly.

- In the following three approaches, we utilize a hash table to guarantee substrings have no repeating characters and optimize the algorithm to query possible substrings step by step: the first approach is intuitive but may cause a TLE, and the second one uses a slide window method to narrow down the search range, and the third make further use of HashMap to reduce the search range faster.

Approach 1: Brute Force
Intuition
- Check all the substring one by one to see if it has no duplicate character.

Algorithm
- Suppose we have a function boolean allUnique(String substring) which will return true if the characters in the substring are all unique, otherwise false. We can iterate through all the possible substrings of the given string s and call the function allUnique. If it turns out to be true, then we update our answer of the maximum length of substring without duplicate characters.

Now let's fill the missing parts:
1. To enumerate all substrings of a given string, we enumerate the start and end indices of them. Suppose the start and end indices are iii and jjj, respectively. Then we have 0≤i<j≤n (here end index j is exclusive by convention). Thus, using two nested loops with iii from 0 to n−1 and j from i+1 to nnn, we can enumerate all the substrings of s.

2. To check if one string has duplicate characters, we can use a set. We iterate through all the characters in the string and put them into the set one by one. Before putting one character, we check if the set already contains it. If so, we return false. After the loop, we return true.

Complexity Analysis
Time complexity : O(n3)
- To verify if characters within index range [i,j) are all unique, we need to scan all of them. Thus, it costs O(j−i) time.
- For a given i, the sum of time costed by each j∈[i+1,n] is ∑i+1nO(j−i)
- Thus, the sum of all the time consumption is: O(n3)

Space complexity : O(min(n,m)). We need O(k) space for checking a substring has no duplicate characters, where k is the size of the Set. The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
*/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (checkRepetition(s, i, j)) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }

    private boolean checkRepetition(String s, int start, int end) {
        Set<Character> chars = new HashSet<>();

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if(chars.contains(c)){
                return false;
            }
            chars.add(c);
        }

        return true;
    }
}
