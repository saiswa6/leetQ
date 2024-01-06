class Solution {
    public int[] diStringMatch(String s) {
        int length = s.length();
        int result[] = new int [length + 1];
        int left = 0;
        int right = length;
        int resultPointer = 0;

        int i = 0;
        while (left <= right) {
            if (left == right) {
                result[resultPointer] = left;
                break;
            } else if(s.charAt(i) == 'I') {
                result[resultPointer] = left++;
                resultPointer++;
            } else if (s.charAt(i) == 'D') {
                result[resultPointer] = right--;
                resultPointer++;
            }
            i++;            
        }

        return result; 
    }
}