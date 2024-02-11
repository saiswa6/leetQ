/*
Approach 1: Binary Search + Fixed Size Sliding Window
Intuition
- Since we are asked to find the longest substring of identical characters, we could first set up a target length max_size. To check if a valid substring with this length exists, we iterate over s and check each substring of length max_size. If we find a substring that contains k or fewer unique characters, then this substring is considered valid.

- The method described in this solution is also known as the fixed sliding window algorithm. As we traverse the sequence from left to right, we maintain a fixed length max_size for the subarray, which can be visualized as moving a window of that length over the input. As shown in the pictures below, if we set the window length to max_size = 3, we can find some valid windows that only contain 2 or fewer unique characters.

******
Refer Approach1 of Leetcode : https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/editorial/


Algorithm
1. If k >= n, we don't need to perform the binary search, just return n.
2. Initialize the search space as left = k, right = n.
3. Define a function isValid to help verify if there exists a valid subarray of length size:
   - Count the number of each character in s[:size] in a hash map counter, return true if len(counter) <= k
   - Iterate the index of the right boundary of the window from size - 1 to n. At each step i, increment counter[s[i]] by 1 and decrement counter[s[i - size]] by 1, if counters[s[i - size]] = 0, we delete this item from counter.
   - Return true if len(counter) <= k at any point in this iteration
   - Return false if we finish iterating without finding a valid window.
4. While left < right:
   - Compute the middle value as mid = right - (right - left) / 2.
   - Check if the window of size mid is valid using the helper method.
   - If isValid(mid) is true, let left = mid and repeat.
   - If isValid(mid) is false, let right = mid - 1 and repeat.
5. Return left once the binary search ends.

Complexity Analysis :
Let n be the length of the input string s.
- Time complexity: O(n⋅log⁡n)
We set the search space as [k, n], it takes at most O(log⁡n) binary search steps.
At each step, we iterate over s which takes O(n) time.

- Space complexity: O(n)
We need to update the boundary indices left and right.
During the iteration, we use a hash map counter which could contain at most O(n) distinct characters.
*/

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (k >= n) {
            return n;
        }
        
        int left = k, right = n;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            
            if (isValid(s, mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    private boolean isValid(String s, int size, int k) {
        int n = s.length();
        Map<Character, Integer> counter = new HashMap<>();
        
        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        
        if (counter.size() <= k) {
            return true;
        }
        
        for (int i = size; i < n; i++) {
            char c1 = s.charAt(i);
            counter.put(c1, counter.getOrDefault(c1, 0) + 1);
            char c2 = s.charAt(i - size);
            counter.put(c2, counter.getOrDefault(c2, 0) - 1);
            if (counter.get(c2) == 0) {
                counter.remove(c2);
            }
            if (counter.size() <= k) {
                return true;
            }
        }
        
        return false;
    }
}
