
//Approach 2: Two-Pointers

class Solution {

    public boolean isSubsequence(String s, String t) {
        Integer leftBound = s.length(), rightBound = t.length();
        Integer pLeft = 0, pRight = 0;

        while (pLeft < leftBound && pRight < rightBound) {
            // move both pointers or just the right pointer
            if (s.charAt(pLeft) == t.charAt(pRight)) {
                pLeft += 1;
            }
            pRight += 1;
        }
        return pLeft == leftBound;
    }
}

/*
Complexity Analysis
Let ∣S∣ be the length of the source string, and ∣T∣ be the length of the target string.
Time Complexity: O(∣T∣)
The analysis is the same as the previous approach.

Space Complexity: O(1)
We replace the recursion with iteration. In the iteration, a constant memory is consumed regardless of the input.
*/

// Solution 3 & 4 in LC but not intuitive. - Greedy with HashMap & DP - Check once
