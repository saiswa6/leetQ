class Solution {
    public String reverseWords(String s) {
        char tempArray [] = s.toCharArray();
        int length = s.length();
        boolean isWordFormed = false;
        int start = 0;
        int end = 0;

        for(int i = 0; i < length; i++) {
            if (!isWordFormed && (tempArray[i] == ' ' || i == length - 1)) {
                end = (i == length - 1) ? i : i - 1;
                isWordFormed = true;
                swap(tempArray, start, end);
            } else if(isWordFormed && tempArray[i] != ' ') {
                 start = i;
                 isWordFormed = false;
            }
        }
        return new String(tempArray);
    }

    public void swap(char tempArray [], int start, int end) {
        while (start < end) {
            char temp = tempArray[start];
            tempArray[start] = tempArray[end];
            tempArray[end] = temp;
            start++;
            end--;
        }
    }
}