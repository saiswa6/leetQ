class Solution {
    public boolean isPalindrome(String s) {
        if(s.isEmpty()) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;

        while(left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);

            if(!Character.isLetterOrDigit(l)) {
                left++;
            } else if(!Character.isLetterOrDigit(r)) {
                right--;
            } else {
                if(Character.toLowerCase(l) != Character.toLowerCase(r)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;


        /*int start = 0;
        int end = s.length() - 1;

        while(start < end) {
            while(start < end && !isAlphaNumeric(s.charAt(start))) {
                start++;
            }
            while(start < end && !isAlphaNumeric(s.charAt(end))) {
                end--;
            }

            if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;*/
    }

    public boolean isAlphaNumeric(char ch) {
        return (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ('0' <= ch && ch <= '9')); 
    }
}