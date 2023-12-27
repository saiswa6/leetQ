class Solution {
    public String reverseOnlyLetters(String s) {
        int slength = s.length();
        int start = 0;
        int end = slength - 1;
        char sarray [] = s.toCharArray();

        while (start < end) {
            while (start < end && !isAlphabet(sarray[start])) {
                start++;
            }

            while (start < end && !isAlphabet(sarray[end])) {
                end--;
            }

            char temp = sarray[start];
            sarray[start] = sarray[end];
            sarray[end] = temp;
            start++;
            end--;
        }

        return new String(sarray);
    }

    public static boolean isAlphabet(char ch) {
        if ((ch>='A' && ch <='Z') || (ch>='a' && ch <='z')) {
            return true;
        }
        else {
            return false;
        }
    }
}