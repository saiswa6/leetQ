/*
Approach 3: Two Pointers
Intuition
- We can also use two pointers to solve this problem, one to iterate over the given string s and the other to point to the position where the most recent non-star character is to be inserted (it will also help in removing the characters).
- We create an array of characters ch having the same size as s. We also create two pointers i = 0 and j = 0.
- We will iterate over s with the pointer i and add and remove non-star characters with the pointer j. The pointer j will point to the index where next non-star character is to be inserted. We will insert the characters in a data structure ch, and after iterating over the entire string s, we will have our required string in ch from index 0 till j - 1 (both inclusive).
- We iterate the given string s from the start. For every index i of the string, if s[i] is a non-star character, we add s[i] to ch at index j. We increment j by 1 to insert the next non-star character at the next position.
- Otherwise, if s[i] is a star character we decrement j by 1, resulting in the removal of the last character.
- If we decrement j, you'll notice that whenever a non-star character is now met, it will override some character previously added in ch at the j index, resulting in the removal of the required non-star character. If there are not enough non-star characters to cover the position until where j previously went, they are still removed because we are only using indices from 0 to j - 1 to form the required string.

*/

class Solution {
    public String removeStars(String s) {
        char[] ch = new char[s.length()];
        int j = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                j--;
            } else {
                ch[j++] = c;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < j; i++) {
            answer.append(ch[i]);
        }

        return answer.toString();
    }
}

/*
Complexity Analysis
Time complexity: O(n)
- It takes O(n) time to initialize a character array of nnn size.
- Iterating over the string s to form the required string in ch also takes O(n) time.
- As there can be at most nnn characters in answer, it takes O(n) time to form it.

Space complexity: O(n)
The character array ch takes O(n) space.
*/
