/*
Approach 1: Stack of Letters
Intuition and Algorithm
- Collect the letters of S separately into a stack, so that popping the stack reverses the letters. (Alternatively, we could have collected the letters into an array and reversed the array.)
- Then, when writing the characters of S, any time we need a letter, we use the one we have prepared instead.

Complexity Analysis
Time Complexity: O(N), where NNN is the length of S.
Space Complexity: O(N).
*/

class Solution {
    public String reverseOnlyLetters(String S) {
        Stack<Character> letters = new Stack();
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }
}
