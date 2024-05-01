/*
Approach 2: Using Frequency Array Map
Approach 2: Using Frequency Array Map
Intuition

We know that the string contains all the lowercase characters (a-z) only. So, instead of using a hashmap to track the frequency of characters, we could build an array of size 26 as a frequency map, 
where each array element represents a character's frequency (0th index = a, 1st index = b and so on). 
In order to check if all characters exist in both words, we could simply iterate over the fixed size frequency map.
*/

class Solution {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int word1Map[] = new int[26];
        int word2Map[] = new int[26];
        for (char c : word1.toCharArray()) {
            word1Map[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            word2Map[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((word1Map[i] == 0 && word2Map[i] > 0) ||
                (word2Map[i] == 0 && word1Map[i] > 0)) {
                return false;
            }
        }
        Arrays.sort(word1Map);
        Arrays.sort(word2Map);
        return Arrays.equals(word1Map, word2Map);
    }
}

/*
Complexity Analysis
Time Complexity : O(n), where n is the length of word.
We iterate over words of size n to build the frequency map which takes O(n).
To check if both words have the same characters and frequency, we iterate over a fixed-size array of size 262626 which takes constant time. 
The sort operation on the array also takes constant time, as the array is of size 26.

This gives us time complexity of O(n)+O(1)+O(1)=O(n)

Space Complexity: O(1), as we use constant extra space of size 262626 to store the frequency map.
*/
