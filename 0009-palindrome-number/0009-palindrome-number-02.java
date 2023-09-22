/*
Approach 2 (My solution) :
Second idea would be reverting the number itself, and then compare the number with original number, if they are the same, then the number is a palindrome. However, if the reversed number is larger than int.MAX, we will hit integer overflow problem.
*/


class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0)
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
