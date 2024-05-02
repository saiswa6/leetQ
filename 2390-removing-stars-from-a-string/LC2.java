/*
Approach 2: Strings
Intuition
We can also use strings in place of a stack to handle the required operations. It can provide similar operations as stack when dealing with characters.
*/

class Solution {
    public String removeStars(String s) {
        int j = 0;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                answer.deleteCharAt(answer.length() - 1);
            } else {
                answer.append(s.charAt(i));
            }
        }
        return answer.toString();
    }
}

/*
Complexity Analysis
Time complexity: O(n)
We iterate over s and for every character we either append it to answer or delete the last character from answer which takes O(1) time per character. It takes O(n) time for nnn characters.

Space complexity: O(n)
The answer string can have a maximum of nnn characters, requiring O(n)) space. Normally, we do not count the answer towards the space complexity, 
but in this case we are performing logic on the answer variable, so we are counting it.

*/
