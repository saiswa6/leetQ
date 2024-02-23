//1ms Solution from submission

//For this problem, since we are not deleting anything, we just have to count the occurrences of mismatches and determine if it is less than 3.

class Solution {
    public boolean makePalindrome(String s) {
        if(s.length() <= 3) return true;
        char[] charArr = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        int counter = 0;
        while(i<j) {
            if(charArr[i] != charArr[j]) 
                if(++counter > 2) return false;  
            i++;j--;
        }
        return true;
    }
}
