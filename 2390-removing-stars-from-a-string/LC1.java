/*
We have to perform some operations on the given string such that for each star, we must remove both the closest non-star character to its left and the star itself.

Approach 1: Stack
Intuition
- To solve the problem, we must keep track of the most recently seen non-star character while iterating from the beginning to the end of the string. 
   Our task should be easy if we are able to use a data structure or write an algorithm that keeps track of the most recent non-star character, which when removed gives the second most recent non-star character, 
   which when removed gives the third most recent non-star character, and so on.

- There are several approaches to solving such a problem, one of which is to use a stack data structure.
*/

class Solution {
    public String removeStars(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                st.pop();
            } else {
                st.push(s.charAt(i));
            }
        }

        StringBuilder answer = new StringBuilder();
        while (!st.isEmpty()) {
            answer.append(st.pop());
        }

        return answer.reverse().toString();
    }
}

/*
Complexity Analysis
Here, n is the length of s.
Time complexity: O(n)
- We iterate over s and for every character we either push it in the stack or pop the top character from the stack which takes O(1) time per character. It takes O(n) time for nnn characters.
- To form the answer string, we remove all the characters from the stack. Because a stack can have maximum of nnn characters, it would also take O(n) time in that case.
We also require O(n) time to reverse answer which can have nnn characters.

Space complexity: O(n)
The stack used in the solution can grow to a maximum size of nnn. We would need O(n) space in that case.
*/
