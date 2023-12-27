/*
Approach 2: Reverse Pointer
Intuition
Write the characters of S one by one. When we encounter a letter, we want to write the next letter that occurs if we iterated through the string backwards.
So we do just that: keep track of a pointer j that iterates through the string backwards. When we need to write a letter, we use it.
we will use i to keep track of symbols(other alphabets). j to write the letters to write from backwards. 


Complexity Analysis
Time Complexity: O(N), where NNN is the length of S.
Space Complexity: O(N).
*/


class Solution {
    public String reverseOnlyLetters(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); ++i) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                ans.append(S.charAt(j--));
            } else {
                ans.append(S.charAt(i));
            }
        }

        return ans.toString();
    }
}
