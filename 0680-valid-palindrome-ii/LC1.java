/*
Approach: Two Pointers
Intuition
- A string is a palindrome if it reads the same forward as backwards.
- For our purposes, we can basically pretend that matched characters no longer exist. For example, after verifying that the first and last characters of 'racecar' match, we can reframe the problem as checking if 'aceca' can be a palindrome with at most one deletion.
- Let's assume we have some string s = 'abbxa'. On its own, s is not a palindrome. However, if we delete the 'x', then s becomes 'abba', which is a palindrome. If we use the same algorithm in checkPalindrome, we will see that the first and last characters match as 'a'. The pointers move inwards, and the "new" string we're focused on is 'bbx'.
- The next check will be a mismatch - 'b' != 'x'. This means that our original string is not a palindrome. However, we can delete one character. If s can be a palindrome after one deletion, the deletion must be of one of these mismatched characters. Deleting the character 'b' gives us 'bx', and deleting the character 'x' gives us 'bb'. Because 'bb' is a palindrome (which we can verify using checkPalindrome), the original string 'abbxa' can become a palindrome with at most one character deletion.

This leaves us two scenarios:
1. s is a palindrome - great, we can just return true.
2. Somewhere in s, there will be a pair of mismatched characters. We must use our allowed deletion on one of these characters. Try both options - if neither results in a palindrome, then return false. Otherwise, return true. We can "delete" the character at j by moving our bounds to (i, j - 1). Likewise, we can "delete" the character at i by moving our bounds to (i + 1, j).

Algorithm
1. Create a helper function checkPalindrome that takes a string s, and two pointers i and j. This function returns a boolean indicating if s.substring(i, j) is a palindrome. Implementation details for this function can be found in the first section of this article.
2. Initialize two pointers, i = 0 and j = s.length() - 1.
3. While i < j, check if the characters at indices i and j match. If they don't, that means we must spend our deletion on one of these characters. Try both options using checkPalindrome. In other words, return true if either checkPalindrome(s, i, j -1) or checkPalindrome(s, i + 1, j) is true.
4. If we exit the while loop, that means the original string is a palindrome. Since we didn't need to use the deletion, we should return true.
*/
class Solution {
    private boolean checkPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            
            i++;
            j--;
        }
        
        return true;
    }
    
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        
        while (i < j) {
            // Found a mismatched pair - try both deletions
            if (s.charAt(i) != s.charAt(j)) {
                return (checkPalindrome(s, i, j - 1) || checkPalindrome(s, i + 1, j));
            }
            
            i++;
            j--;
        }
        
        return true;
    }
}

/*
Complexity Analysis
Given N as the length of s,
Time complexity: O(N)
- The main while loop we use can iterate up to N / 2 times, since each iteration represents a pair of characters. On any given iteration, we may find a mismatch and call checkPalindrome twice. checkPalindrome can also iterate up to N / 2 times, in the worst case where the first and last character of s do not match.
- Because we are only allowed up to one deletion, the algorithm only considers one mismatch. This means that checkPalindrome will never be called more than twice.
- As such, we have a time complexity of O(N).

Space complexity: O(1)
The only extra space used is by the two pointers i and j, which can be considered constant relative to the input size.
*/
