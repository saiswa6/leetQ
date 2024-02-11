/*
Approach 3: Sliding Window II
Intuition
- In the previous solution, we need to ensure that the current window is always valid. If the window contains more than k distinct characters, we need to continuously remove the leftmost character in the window. During this process, the size of the window may decrease, even smaller than the previous valid window. Taking the figure below as an example, the window on the left is valid, but the window' on the right is not valid, and we need to remove the left characters from it to make it valid.
- However, we don't need to decrease the size of the window.
- If we have already found a window of length max_size, then what we need to do next is to search for a larger valid window, for example, a window with length max_size + 1. Therefore, in the following sliding window process, even if the current window with size max_size is not valid, there is no problem, because we have already found a window of length max_size before, so we may as well continue looking for a larger window.

Understanding this, we can simplify the solution in approach 2:

- Again, we use a hash map counter to keep track of the frequency of each letter in the current window. When we increase the window length by 1, we need to increase the count of the character at the current right boundary counter[s[right]] by 1.
- Once this iteration is over, max_size represents the maximum size of the valid window.

Algorithm
1. Use a hash map counter to keep track of the number of each character in the current window.
2. Set max_size = 0, iterate right from 0 to n - 1, at each step right, increment s[right] by 1, and increment counter[s[right]] by 1.
   - If len(counter) > k, decrement counter[s[right - max_size]] by 1. Delete counter[s[right - max_size]] if its value equals 0.
   - Otherwise, increment max_size by 1.
3. Return max_size when the iteration ends.

Complexity Analysis :
Let n be the length of the input string s and kkk be the maximum number of distinct characters.

Time complexity: O(n)
- In the iteration of the right boundary right, we shift it from 0 to n - 1.
- At each step, we update the number of s[right] and/or the number of s[right - max_size] in the hash map counter, which takes constant time.
- To sum up, the overall time complexity is O(n)

Space complexity: O(k)
- We need to record the occurrence of each distinct character in the valid window. During the iteration, there might be at most O(k+1)O(k + 1)O(k+1) unique characters in the window, which takes O(k)O(k)O(k) space.
*/

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        int maxSize = 0;
        Map<Character, Integer> counter = new HashMap<>();
        
        for (int right = 0; right < n; right++) {
            counter.put(s.charAt(right), counter.getOrDefault(s.charAt(right), 0) + 1);
            
            if (counter.size() <= k) {
                maxSize++;
            } else {
                counter.put(s.charAt(right - maxSize), counter.get(s.charAt(right - maxSize)) - 1);
                if (counter.get(s.charAt(right - maxSize)) == 0) {
                    counter.remove(s.charAt(right - maxSize));
                }
            }
        }

        return maxSize; 
    }
}
