/*
Intuition
Delete the characters from both ends when characters match.

Approach
Use 2 pointer i.e., left and right pointer. When left and right characters match, delete the characters on both staring and ending side until they are same. Otherwise, return the answer.
Take care about edge cases
Ex ccc -> 0
   cbc -> 1

Complexity
Time complexity:
O(n)

Space complexity:
O(1)
*/

class Solution {
    public int minimumLength(String s) {
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int answer = -1;
        int left = 0, right = length - 1;
        while(left < right) {
            if(s.charAt(left) == s.charAt(right)) {
                char toRemove = s.charAt(left);
                left++;
                right--;
                while (left <= right && s.charAt(left) == toRemove) {
                    left++;
                }
                while (left <= right && s.charAt(right) == toRemove) {
                    right--;
                }
            }
            else {
                break;
            }
        }
        answer = (left > right)? 0 : (right - left + 1) ;

        return answer;
    }
}
