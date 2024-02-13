/*
Approach 2: Sliding Window
Intuition
- We can also find the longest valid window with fewer traversals. Unlike the previous fixed-length sliding window solution, this time we can adjust the window length based on the situation. We will still use the counter count to record the count of each type of answer within the window.

- Specifically, if the current window is valid, we can try to expand the window by moving the right boundary one position to the right, right = right + 1. On the other hand, if the current window is invalid, we keep moving the left boundary to the right (equivalent to removing the leftmost answer from the window) until the window becomes valid, that is left = left + 1. During this process, we constantly record the longest valid window seen so far.

Algorithm
1. Use a hash map count to record the count of T and F in the current window.
2. Set left = 0 and max_size = 0, iterate right from 0 to n - 1, at each step right, increment answerKey[right] by 1:
   - Increment count[answerKey[right]] by 1.
   - While min(count['T'], count['F']) > k, decrement count[answerKey[left]] by 1 and increment left by 1.
   - Now the window is valid, update the maximum size of valid window as max_size = max(max_size, right - left + 1).
3. Return max_size when the iteration ends.

Complexity Analysis
Let n be the length of the input string answerKey.
Time complexity: O(n)
- In the iteration of the right boundary right, we shift it from 0 to n - 1. Although we may move the left boundary left in each step, left always stays to the left of right, which means left moves at most n - 1 times.
- At each step, we update the value of an element in the hash map count, which takes constant time.
To sum up, the overall time complexity is O(n)

Space complexity: O(1)
- We only need to update two indices left and right. During the iteration, we need to count the number of T and F, which also takes O(1) space.
*/

class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int maxSize = k;
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < k; i++) {
            count.put(answerKey.charAt(i), count.getOrDefault(answerKey.charAt(i), 0) + 1);
        }
        
        int left = 0;
        for (int right = k; right < answerKey.length(); right++) {
            count.put(answerKey.charAt(right), count.getOrDefault(answerKey.charAt(right), 0) + 1);
            
            while (Math.min(count.getOrDefault('T', 0), count.getOrDefault('F', 0)) > k) {
                count.put(answerKey.charAt(left), count.get(answerKey.charAt(left)) - 1);
                left++;
            }
            
            maxSize = Math.max(maxSize, right - left + 1);
        }
                    
        return maxSize;
    }
}
