class Solution {
    public String reversePrefix(String word, char ch) {
        int findIndex = word.indexOf(ch);
        if(findIndex == -1) {
            return word;
        }

        char wordArray [] = word.toCharArray();
        int start = 0;
        int end = findIndex;
        while (start < end) {
            char temp = wordArray[start];
            wordArray[start] = wordArray[end];
            wordArray[end] = temp;
            start++;
            end--;
        }

        return new String(wordArray);
    }
}