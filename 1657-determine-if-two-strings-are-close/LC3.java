/*
Approach 3: Using Bitwise Operation and Frequency Array Map
Intuition
- We just want a way to know if a character exists in a word or not. Instead of iterating over a frequency map to check this condition, we could simply store this information in a single integer. 
   This could be done by making use of Bitwise Operators.

- We could use a integer wordBit, where each bit in the wordBit stores the information about each of the 26 characters (a-z). 
   The rightmost bit represents the character a, the next left bit would represent character b and so on.

- A character exists in the word if it's a corresponding bit is set to 1.

To set a bit represented by a character we must use the bitwise OR operator.
Example,
wordBit ∥ wordBit << 2, sets the 2nd bit, wordBit=100

It must be noted that this approach works because the size of the integer is 32 bits
*/

class Solution {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int word1Map[] = new int[26];
        int word2Map[] = new int[26];
        int word1Bit = 0;
        int word2Bit = 0;
        for (char c : word1.toCharArray()) {
            word1Map[c - 'a']++;
            word1Bit = word1Bit | (1 << (c - 'a'));
        }
        for (char c : word2.toCharArray()) {
            word2Map[c - 'a']++;
            word2Bit = word2Bit | (1 << (c - 'a'));
        }
        if (word1Bit != word2Bit) {
            return false;
        }
        Arrays.sort(word1Map);
        Arrays.sort(word2Map);
        for (int i = 0; i < 26; i++) {
            if (word1Map[i] != word2Map[i]) {
                return false;
            }
        }
        return true;
    }
}

/*
Complexity Analysis
Time Complexity : O(n), where n is the length of the word. The complexity is similar to Approach 2.
Space Complexity: O(1), as we use constant extra space, frequency map of size 26 and word bits of type integer.
*/
