class Solution {
    public boolean validPalindrome(String s) {

        int start = 0;
        int end = s.length() - 1;

        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return (checkPalindrome(s, start, end - 1) || checkPalindrome(s, start + 1, end));
            }
            start++;
            end--;
        }
        return true;

        /*char sarray [] = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        char startChar = '&';
        char startChar2 = '&';
        char endChar = '&';
        char endChar2 = '&';
        boolean isDeleteOne = false;
        while (start < end) {
            startChar = sarray[start];
            endChar = sarray[end];
            if(sarray[start] == sarray[end]) {
                start++;
                end--;
                continue;
            }
            else if (sarray[start] != sarray[end]) {
                if (isDeleteOne) {
                    return false;
                }  else if (sarray[start] == sarray[end - 1]) {
                    endChar = sarray[end ];
                    endChar2 = sarray[end - 1];
                    startChar = sarray[start];
                    isDeleteOne = true;
                    end--;
                } else if (sarray[start + 1] == sarray[end]) {
                    startChar = sarray[start];
                    startChar2 = sarray[start + 1];
                    endChar = sarray[end];
                    isDeleteOne = true;
                    start ++;
                }else {
                    return false;
                }
            }
        }
        return true;*/
    }

    public boolean checkPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}