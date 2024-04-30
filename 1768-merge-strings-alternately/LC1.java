/*
Approach 1: Two Pointers
Intuition
- An intuitive method is to use two pointers to iterate over both strings. Assume we have two pointers, i and j, with i pointing to the first letter of word1 and j pointing to the first letter of word2. We also create an empty string results to store the outcome.
- We append the letter pointed to by pointer i i.e., word1[i], and increment i by 1 to point to the next letter of word1. Because we need to add the letters in alternating order, next we append word2[j] to results. We also increase j by 1.
- We continue iterating over the given strings until both are exhausted. We stop appending letters from word1 when i reaches the end of word1, and we stop appending letters from word2' when j reaches the end of word2.

Algorithm
1. Create two variables, m and n, to store the length of word1 and word2.
2. Create an empty string variable result to store the result of merged words.
3. Create two pointers, i and j to point to indices of word1 and word2. We initialize both of them to 0.
4. While i < m || j < n:
   If i < m, it means that we have not completely traversed word1. As a result, we append word1[i] to result. We increment i to point to next index of words.
   If j < n, it means that we have not completely traversed word2. As a result, we append word2[j] to result. We increment j to point to next index of words.
5. Return results.

- java: The String class is immutable in java. So we used the mutable StringBuilder to concatenate letters to result.
*/

class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;

        while (i < m || j < n) {
            if (i < m) {
                result.append(word1.charAt(i++));
            }
            if (j < n) {
                result.append(word2.charAt(j++));
            }
        }

        return result.toString();
    }
}

/*
Complexity Analysis
Here, m is the length of word1 and nnn is the length of word2.
Time complexity: O(m+n)
We iterate over word1 and word2 once and push their letters into result. It would take O(m+n) time.

Space complexity: O(1)
Without considering the space consumed by the input strings (word1 and word2) and the output string (result), we do not use more than constant space.
*/