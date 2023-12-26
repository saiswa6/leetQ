/*
Approach 1: Two Pointers
Intuition
- we will initialize two pointers, one pointer (referred as left) pointing to the left end of the input string and the other pointer (named as right) pointing to the right end of the string.

- To achieve this, we would initialize a left pointer to 0 and keep incrementing it until we get a vowel. Similarly, we initialize the right pointer to the last index and keep decrementing it until it points to a vowel. At each such iteration where both the pointers are pointing to the vowel, we would swap the characters at these pointers.


Algorithm
1. Initialize the left pointer start to 0, and the right pointer end to s.size() - 1.
2. Keep iterating until the left pointer catches up with the right pointer:
   a. Keep incrementing the left pointer start until it's pointing to a vowel character.
   b. Keep decrementing the right pointer end until it's pointing to a vowel character.
   c. Swap the characters at the start and end.
   d. Increment the start pointer and decrement the end pointer.
3. Return the string s.

Complexity Analysis : 
Time complexity: O(N). Here, N is the length of the string s.
- It might be tempting to say that there are two nested loops and hence the complexity would be O(N2). However, if we observe closely the pointers start and end will only traverse the index once. Each element of the string s will be iterated only once either by the left or right pointer and not both. We swap characters when both pointers point to vowels which are O(1) operation. Hence the total time complexity will be O(N).
- Note that in Java we need to convert the string to a char array as strings are immutable and hence it would take O(N) time.

Space complexity: O(N)
In C++ we only need an extra temporary variable to perform the swap and hence the space complexity is O(1). However, in Java, we need to convert the string to a char array that would take O(N) space, and therefore the space complexity for Java would be O(N).
*/

// MY SOLUTION
class Solution {
    public String reverseVowels(String s) {

        int length = s.length();
        int start = 0;
        int end = length - 1;
        char sArray [] = s.toCharArray();

        while(start < end) {
            while(!isVowel(sArray[start]) && start < end ) {
                start++;
            }
            while(!isVowel(sArray[end]) && start < end) {
                end--;
            }

            char temp = sArray[start];
            sArray[start] = sArray[end];
            sArray[end] = temp;
            start++;
            end--;
         }

         return new String(sArray);
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


//LEETCODE SOLUTION
class Solution {
    // Return true if the character is a vowel (case-insensitive)
    boolean isVowel(char c) {
        return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u'
            || c == 'A' || c == 'I' || c == 'E' || c == 'O' || c == 'U';
    }
    
    // Function to swap characters at index x and y
    void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
    
    public String reverseVowels(String s) {
        int start = 0;
        int end = s.length() - 1;
        // Convert String to char array as String is immutable in Java
        char[] sChar = s.toCharArray();
        
        // While we still have characters to traverse
        while (start < end) {
            // Find the leftmost vowel
            while (start < s.length () && !isVowel(sChar[start])) {
                start++;
            }
            // Find the rightmost vowel
            while (end >= 0 && !isVowel(sChar[end])) {
                end--;
            }
            // Swap them if start is left of end
            if (start < end) {
                swap(sChar, start++, end--);
            }
        }
        
        // Converting char array back to String
        return new String(sChar);
    }
};
