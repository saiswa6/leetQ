/*
Two strings are considered close if you can attain one from the other using the following operations:
Operation 1: Swap any two existing characters.
             For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
             For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)

Now, performing these 2 operations for every 2 characters in strings and determining the closeness after every operation would be costly. How can we optimize it?
From the given operations, we could observe that if any 2 strings are close, they always satisfy follow conditions,

Condition 1: Every character that exists in word1 must exist in word2 as well, irrespective of the frequency.
-----------
In all the transformations of string uua, the character u and a are always present. Thus, if any string is close to uua it must contain the characters u and a.

Condition 2: The frequency of all the characters is always the same. In the above example for string uua, regardless of the operation, following condition always holds :
-----------
There exists 1 character that occurs once (frequency = 1) and 1 character that occurs twice (frequency = 2)

==========================================================================================
Approach 1: Using HashMap
Intuition
- As discussed above, we have to check for the following conditions to determine if given strings word1 and word1 are close:
   - The strings word1 and word2 must have the same characters (Condition 1).
     We can build a set that contains the characters in word1 and word2 and check if both sets are equal.
   - The occurrence or frequency of characters in word1 and word2 must be the same. (Condition 2).
      One way to get the frequency of each character in a string is to use a hashmap. We could build a hashmap with each character as a key and it's frequency as a value of hashmap. Now, we have to verify if there are equal number of characters with a particular frequency. For this, we can sort the frequency values in the hashmap and check for equality.

Instead of building a separate set to check for Condition 1, we can only build one hashmap and check if the keys (that represent each character in the string) are present in both maps.
*/

class Solution {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();
        for (char c : word1.toCharArray()) {
            word1Map.put(c, word1Map.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            word2Map.put(c, word2Map.getOrDefault(c, 0) + 1);
        }
        if (!word1Map.keySet().equals(word2Map.keySet())) {
            return false;
        }
        List<Integer> word1FrequencyList = new ArrayList<>(word1Map.values());
        List<Integer> word2FrequencyList = new ArrayList<>(word2Map.values());
        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);
        return word1FrequencyList.equals(word2FrequencyList);
    }
}

/*
Complexity Analysis
Time Complexity: O(n). We iterate over each word to build the hashmap which would take O(n) time.
Further, we sort the character keys and frequency values of each hashmap. The maximum size of hashmap would be 26, as we store each character a-z only once. 
In the worst case, all the sort operations would take O(26log⁡26) time to sort those frequency values.
This gives us total time complexity as O(n)

Space Complexity: O(1), as the maximum size of each hashmap would be 26, we are using constant extra space.
*/
