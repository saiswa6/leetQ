/*
Approach 2 (My solution) :
Second idea would be reverting the number itself, and then comparing the number with the original number, if they are the same, then the number is a palindrome. However, if the reversed number is larger than int.MAX, we will hit integer overflow problem.

Same solution on leetcode with Overflow prevention. check that solution.
*/


class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) // Negative number can't be a palindrome.
        {
            return false;
        }

        if(x % 10 == 0 && x != 0) // If the last digit of the number is 0, in order to be a palindrome,the first digit of the number also needs to be 0. Only 0 satisfy this property. Other number ends with 0, can't be a palindrome.
        {
            return false;
        }
        
        int num = x;
        int result = 0;
        while(num > 0)
        {
            int mod = num % 10;
            num = num / 10;
            result = result * 10 + mod ; 
        }
        return result == x;
    }
}
