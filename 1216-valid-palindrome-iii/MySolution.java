// My Solution works for low k numbers. It fails when k is 216. DP needed here. When DP is learnt, I will solve this again.

class Solution {


    public boolean isValidPalindrome(String s, int k) {
        int start = 0;
        int end = s.length() - 1;
        int count = k;

        while(start < end) {
            if(s.charAt(start) != s.charAt(end) && count > 0) {
                return checkPalindrome(s, start, end, count); // call subfunction
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean checkPalindrome(String s, int start, int end, int count) { // Here recursively check for count times deleting , whether it becomes palindrome.
        while(start < end) {
            if(s.charAt(start) == s.charAt(end)) { // If equal, increment
                start++;
                end--;
            } else if(s.charAt(start) != s.charAt(end) & count > 0) {
                return (checkPalindrome(s, start + 1, end, count - 1) || checkPalindrome(s, start , end - 1, count - 1)) ; // check for both cases.
            } else {
                return false; // If count exhausted, return false
            }
        }
        return true;
    }
}
