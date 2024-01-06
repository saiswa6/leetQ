// Observe the pattern
// If starting char in string is I, then 0 (left). Then itertively, if I -> left
// If starting char in string is D, then n (right). Then itertively, if D -> right

/*
I D I D
0,3,1,4,2
l r l r l==r
*/


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
            if(tempArray[i] == 'I') {   // I -> left
                result[resultPointer] = left++;
                resultPointer++;
            } else {                    // D -> right
                result[resultPointer] = right--;
                resultPointer++;
            }
            i++;            
        }
        result[resultPointer] = left;    // this is when left == right and last left element.

        return result; 
    }
}
