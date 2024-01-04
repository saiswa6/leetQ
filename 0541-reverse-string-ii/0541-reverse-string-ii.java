class Solution {
    public String reverseStr(String s, int k) {
        int length = s.length();
        char sArray [] = s.toCharArray();
        for(int i = 0 ; i < length; ) {
            int start = i;
            int end = (start + k - 1 >= length) ? length - 1 : start + k - 1;
            if( start < length && end < length) {
                swap(sArray, start, end);
            }
            i = i + 2*k;
        }

        return new String(sArray);
    }

    public void swap(char sArray[], int start, int end) {
        
        while(start < end) {
            char temp = sArray[start];
            sArray[start] = sArray[end];
            sArray[end] = temp;
            start++;
            end--;
        }

    }
}