/*

Approach 3: Advanced Sliding Window
Intuition
- In the previous solution, we need to ensure that the current window is always valid. If the window contains more than k occurrences of T and F, we need to continuously remove the leftmost answer in the window. During this process, the size of the window may decrease, even smaller than the previous valid window. Taking the figure below as an example, the window on the left is valid, but the window' on the right is not valid, and we need to remove the left two answers from it to make it valid.

- However, we don't need to decrease the size of the window.

- If we have already found a window of length max_size, then what we need to do next is to search for a larger valid window, for example, a window with length max_size + 1. Therefore, in the following sliding window process, even if the current window with size max_size is not valid, there is no problem, because we have already found a window of length max_size before, so we may as well continue looking for a larger window.


Algorithm
1. Use a hash map count to keep track of the number of T and F in the current window.
2. Set max_size = 0, iterate right from 0 to n - 1, at each step right, increment answerKey[right] by 1, and increment count[answerKey[right]] by 1.
   - If min(count['T'], count['F']) > k, decrement count[answerKey[right - max_size]] by 1.
   - Otherwise, increment max_size by 1.
3. Return max_size when the iteration ends.

Complexity Analysis
Let n be the length of the input string answerKey.

Time complexity: O(n)
- In the iteration of the right boundary right, we shift it from 0 to n - 1.
At each step, we update the number of answerKey[right] and/or the number of answerKey[right - max_size] in the hash map count, which takes constant time.
To sum up, the overall time complexity is O(n)

Space complexity: O(1)
- We only need to update two parameters max_size and right. During the iteration, we need to count the number of T and F, which also takes O(1) space.
*/
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int maxSize = 0;
        Map<Character, Integer> count = new HashMap<>();
        
        for (int right = 0; right < answerKey.length(); right++) {
            count.put(answerKey.charAt(right), count.getOrDefault(answerKey.charAt(right), 0) + 1);
            int minor = Math.min(count.getOrDefault('T', 0), count.getOrDefault('F', 0));
            
            if (minor <= k) {
                maxSize++;
            } else {
                count.put(answerKey.charAt(right - maxSize), count.get(answerKey.charAt(right - maxSize)) - 1);
            }
        }

        return maxSize;
    }
}
