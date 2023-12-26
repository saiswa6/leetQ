class Solution {
    public String reverseVowels(String s) {

        int length = s.length();
        int start = 0;
        int end = length - 1;
        char sArray [] = s.toCharArray();

        while(start < end) {
            while(!isVowel(sArray[start]) && start < end ) {
                start++;
            }
            while(!isVowel(sArray[end]) && start < end) {
                end--;
            }

            char temp = sArray[start];
            sArray[start] = sArray[end];
            sArray[end] = temp;
            start++;
            end--;
         }

         return new String(sArray);
    }

    public  static boolean isVowel(char ch) {
        if(ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O' || ch == 'u' || ch == 'U'   ) {
            return true;
        }
        else {
        return false;
        }
    }
}















/* 
        if(s.length() == 1)
        {
            return s;
        }
        String first = "";
        String second = "";
        int length = s.length();
        int start = 0;
        int end = length - 1;

        while(start < end) {
            while(!isVowel(s.charAt(start)) && start < end ) {
                first = first + s.charAt(start);
                start++;
            }
            while(!isVowel(s.charAt(end)) && start < end) {
                second = s.charAt(end) + second;
                end--;
            }

            if (isVowel(s.charAt(start)) && isVowel(s.charAt(end))) {
                first = first + s.charAt(end);
                second = s.charAt(start)+ second ;
                start++;
                end--;
            }

            if(start + 1 == end) {
                first += s.charAt(end);
            }
 
         }

         return first + second;
*/