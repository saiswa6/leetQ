/*
Approach 2: One Pointer
Intuition
- To merge the given words, we can also use a single pointer.
- Let i be the pointer that we'll use. We begin with i = 0 and progress to the size of the longer word between word1 and word2, i.e., till i = max(word1.length(), word2.length()).
- As we progress to the size of a longer word, we check each time if i points to an index that is in bounds of the words or not. If i < word1.length(), we append word1[i] to results. Similarly if i < word2.length(), we append word2[i] to results.
- However, if i exceeds the length of any word, we don't have any letters to add from that word, so we ignore it and continue adding the letter from the longer word.

Algorithm
1. Create two variables, m and n, to store the length of word1 and word2.
2. Create an empty string variable result to store the result of merged words.
3. Iterate over word1 and word2 using a loop running from i = 0 to i < max(m, n) and keep incrementing i by 1 after each iteration:
   - If i < m, it means that we have not completely traversed word1. As a result, we append word1[i] to result.
   - If i < n, it means that we have not completely traversed word2. As a result, we append word2[i] to result.
4. Return results.
*/
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < Math.max(m, n); i++) {
            if (i < m) {
                result.append(word1.charAt(i));
            }
            if (i < n) {
                result.append(word2.charAt(i));
            }
        }

        return result.toString();
    }
}


/*
Complexity Analysis
Here, m is the length of word1 and nnn is the length of word2.
Time complexity: O(m+n)
We iterate over word1 and word2 once pushing their letters into result. It would take O(m + n) time.

Space complexity: O(1)
Without considering the space consumed by the input strings (word1 and word2) and the output string (result), we do not use more than constant space.
 */