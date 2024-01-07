//Two Pointer Approach - Fast and slow pointer

class Solution {
    public int compress(char[] chars) {
        int slow = 0;   // slow pointer is used to store the char and count in array after fast pointer count calculation is done.
        int length = chars.length;
        for(int fast = 0; fast < length ; ) {  // fast pointer is used to iterate through each character.
            char letter = chars[fast] ;
            int count = 0;

            while ( fast < length && letter == chars[fast]) { // fast pointer - check how many times char is counted.
                fast++;
                count++;
            }

            chars[slow++] = letter;

            if (count > 1) {  // Add only when count > 1
                for (char c : Integer.toString(count).toCharArray()) { // Convert Integer to String to Characters.. to add to chars. 
                    chars[slow++] = c;
                }
            }
        }
        return slow;
    }
}
