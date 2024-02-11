/*
Approach 2: Sliding Window
Intuition
- We can also find the longest valid window with fewer traversals. Unlike the previous fixed-length sliding window solution, this time we can adjust the window length based on the situation. We will still use the counter counter to record the counter of each type of character within the window.
- Specifically, if the current window is valid, we can try to expand the window by moving the right boundary one position to the right, right = right + 1. On the other hand, if the current window is invalid, we keep moving the left boundary to the right (equivalent to removing the leftmost character from the window) until the window becomes valid, that is left = left + 1. During this process, we constantly record the longest valid window seen so far.

Algorithm
1. Use a hash map counter to record the number of each character in the current window.
2. Set left = 0 and max_size = 0, iterate right from 0 to n - 1, at each step right, increment s[right] by 1:
   - Increment counter[s[right]] by 1.
   - While len(counter) > k, decrement counter[s[left]] by 1. Delete s[left] from counter if counter[s[left] = 0, and increment left by 1.
   - Now the window is valid, update the maximum size of valid window as max_size = max(max_size, right - left + 1).
3. Return max_size when the iteration ends.

Complexity Analysis
- Let n be the length of the input string s and kkk be the maximum number of distinct characters.
Time complexity: O(n)
- In the iteration of the right boundary right, we shift it from 0 to n - 1. Although we may move the left boundary left in each step, left always stays to the left of right, which means left moves at most n - 1 times.
- At each step, we update the value of an element in the hash map counter, which takes constant time.
- To sum up, the overall time complexity is O(n).

Space complexity: O(k)
- We need to record the occurrence of each distinct character in the valid window. During the iteration, there might be at most O(k+1) unique characters in the window, which takes O(k) space.
*/

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        int maxSize = 0;
        Map<Character, Integer> counter = new HashMap<>();
        
        int left = 0;
        for (int right = 0; right < n; right++) {
            counter.put(s.charAt(right), counter.getOrDefault(s.charAt(right), 0) + 1);
            
            while (counter.size() > k) {
                counter.put(s.charAt(left), counter.get(s.charAt(left)) - 1);
                if (counter.get(s.charAt(left)) == 0) {
                    counter.remove(s.charAt(left));
                }
                left++;
            }
            
            maxSize = Math.max(maxSize, right - left + 1);
        }
                    
        return maxSize;  
    }
}
