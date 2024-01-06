class Solution {
    public int[] diStringMatch(String s) {
        int length = s.length();
        int result[] = new int [length + 1];
        int left = 0;
        int right = length;
        int resultPointer = 0;
        char tempArray[] = s.toCharArray();

        int i = 0;
        while (left < right) {
            if(tempArray[i] == 'I') {
                result[resultPointer] = left++;
                resultPointer++;
            } else {
                result[resultPointer] = right--;
                resultPointer++;
            }
            i++;            
        }
        result[resultPointer] = left;

        return result; 
    }
}