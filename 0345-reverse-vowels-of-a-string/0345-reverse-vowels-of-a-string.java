class Solution {
    public String reverseVowels(String s) {
        int length = s.length();
        int start = 0;
        int end = length - 1;
        char sarray [] = s.toCharArray();

        while (start < end)
        {
            if(!isVowel(sarray[start]) && start < end) {
                start++;
                continue;
            } else if (!isVowel(sarray[end]) && start < end) {
                end--;
                continue;
            } else {
                char temp = sarray[start];
                sarray[start] = sarray[end];
                sarray[end] = temp;
                start++;
                end--;
            }
        }

        return new String(sarray);
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