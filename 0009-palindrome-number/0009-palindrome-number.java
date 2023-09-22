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