class Solution {
    public String reverseVowels(String s) {
        int length = s.length();
        int start = 0;
        int end = length - 1;
        char sarray [] = s.toCharArray();

        while (start < end)
        {
            // If left side char is not vowel, increment start++ and go for next element
            if(!isVowel(sarray[start]) && start < end) {
                start++;
                continue;
                // If right side char is not vowel, decrement end-- and go for next element
            } else if (!isVowel(sarray[end]) && start < end) {
                end--;
                continue;
                // when both are vowels, then it comes to this else block. swap them.
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
