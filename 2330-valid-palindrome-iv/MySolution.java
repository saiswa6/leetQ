class Solution {
    public boolean makePalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        int count = 2; // As max is 2 operations, check for max 2 cases where start and end char matches. 

        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                if(count <= 0) {
                    return false;
                } 
                count--;
            } 

            start++;
            end--;
        }
        return true;
    }
}
