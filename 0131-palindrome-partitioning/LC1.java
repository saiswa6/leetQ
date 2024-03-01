/*
Overview
The aim is to partition the string into all possible palindrome combinations. To achieve this, we must generate all possible substrings of a string by partitioning at every index until we reach the end of the string. Example, abba can be partitioned as ["a","ab","abb","abba"]. Each generated substring is considered as a potential candidate if it a palindrome.

Let's look at a few approaches to implement this idea.

Approach 1: Backtracking
Intuition
==================
The first idea is to generate all possible substrings of a given string and expand each possibility if it is a potential candidate. The first thing that comes to mind is Depth First Search. In Depth First Search, we recursively expand potential candidates until the defined goal is achieved. After that, we backtrack to explore the next potential candidate.

Backtracking incrementally build the candidates for the solution and discards the candidates (backtrack) if they doesn't satisfy the condition.

The backtracking algorithms consists of the following steps:
- Choose: Choose the potential candidate. Here, our potential candidates are all substrings that could be generated from the given string.
- Constraint: Define a constraint that must be satisfied by the chosen candidate. In this case, the constraint is that the string must be a palindrome.
- Goal: We must define the goal that determines if have found the required solution and we must backtrack. Here, our goal is achieved if we have reached the end of the string.

Algorithm
===========
In the backtracking algorithm, we recursively traverse over the string in depth-first search fashion. For each recursive call, the beginning index of the string is given as start.
1. Iteratively generate all possible substrings beginning at index start. The index end increments from start until the end of the string.
2. For each of the substrings generated, check if it is a palindrome.
3. If the substring is a palindrome, the substring is a potential candidate. Add the substring to the currentList and perform a depth-first search on the remaining substring. If the current substring ends at index end, end+1 becomes the start index for the next recursive call.
4. Backtrack if start index is greater than or equal to the string length and add the currentList to the result.
*/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, result, new ArrayList<String>(), s);
        return result;
    }

    void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<String>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }
}

// Learn RC and SC from editorial and add here : https://leetcode.com/problems/palindrome-partitioning/editorial/

// There is 2nd method of solving with DP.
// After DP is learnt, add the solution here.
