class Solution {
    public void reverseString(char[] s) {
        int sLength = s.length;

        // for( int i = 0; i < sLength/2; i++) {
        //     char temp = s[i];
        //     s[i] = s[sLength-1-i];
        //     s[sLength-1-i] = temp;
        // }

        int start = 0, end = sLength - 1;
        while (start < end)
        {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}