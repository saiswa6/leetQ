/*
Approach: Sliding Window
Intuition
We can use a sliding window to solve this problem. The term "subarray of length k" in the problem is actually equivalent to "window of length k". Since the length of the window (substring) is fixed as k, we only need to create a window at the leftmost side of the string s, and move it one character to the right each time. This way, the window can cover all subarrays of length k. Then, we simply count the number of vowels in each window and record the maximum count according to the requirement. 


. If we move the window one character to the right as [i - k + 1, i].
- If the newly added character s[i] is a vowel, we increase count by 1.
- If the newly removed character s[i - k] is a vowel, we reduce count by 1.
*/

class Solution {
    public int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        
        // Build the window of size k, count the number of vowels it contains.
        int count = 0;
        for (int i = 0; i < k; i++) {
            count += vowels.contains(s.charAt(i)) ? 1 : 0;
        }
        int answer = count;
        
        // Slide the window to the right, focus on the added character and the
        // removed character and update "count". Record the largest "count".
        for (int i = k; i < s.length(); i++) {
            count += vowels.contains(s.charAt(i)) ? 1 : 0;
            count -= vowels.contains(s.charAt(i - k)) ? 1 : 0;
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
}


/*
Complexity Analysis
Let n be the length of the input string s.

Time complexity: O(n)
We apply 1 iteration over s.
At each step in the iteration, we check if the newly added character and the removed character are in vowels, which takes constant time.
To sum up, the time complexity is O(n)

Space complexity: O(1)
We need to record several parameters, count and answer, which takes O(1) space.
The set vowels contains 5 vowel letters which takes O(1) space.
*/
