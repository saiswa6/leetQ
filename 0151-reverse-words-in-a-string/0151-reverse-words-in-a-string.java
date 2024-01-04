class Solution {
    public String reverseWords(String s) {
        String ss = s.trim();
        char tempArray [] = ss.toCharArray();
        int length = ss.length();
        reverse(tempArray, 0, length - 1);
        StringBuilder result = new StringBuilder();
        int st = 0;
        int en = 0;
        boolean isWordFormed = false;
        for (int j=0;j<length;j++) {
            if((j == length - 1 || tempArray[j] == ' ') && (!isWordFormed )) {
                en = (j==length-1)? j : j-1;
                isWordFormed = true;
                char newArray [] = new char[en-st+1];
                int i = 0;
                while (st <= en) {
                    newArray[i] = tempArray[en];
                    i++; 
                    en--;
                }
                result.append(new String(newArray) + " ");
                //newArray[i++] = ' ';
            }
            else if(isWordFormed && (tempArray[j] != ' ')) {
                if (j == length - 1 || tempArray[j+1] == ' ') {
                    result.append(tempArray[j] + " ");
                } else {
                    isWordFormed = false;
                    st = j;
                }

            }
        }
        //newArray[--i] = '\0';
        return result.deleteCharAt(result.length()-1).toString();

    }

    public void reverse(char array[], int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}