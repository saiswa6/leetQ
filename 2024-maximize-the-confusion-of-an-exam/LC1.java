/*
Refer the link for diagrams and explanation : 

Approach 1: Binary Search + Fixed Size Sliding Window
Intuition
- Since we are asked to find the longest sequence of identical answers. We could first set up a target length max_size, then we shall iterate over answerKey to look for each substring of length max_size. If we could flip at most k answers in a substring to make all answers identical, then this substring is valid and we can make a confusion of at least max_size.

- In order to make a string valid, we can either:
  - Flip every T to F in the string so that it is all F.
  - Flip every F to T in the string so that it is all T.

- However, if both F and T in the substring are more than k, we can never make it valid by k flips. Therefore, we can determine if a substring is valid by comparing k with the smaller value between the count of F and the count of T in it.
  - If min(count(T), count(F)) <= k, this substring is valid.
  - If min(count(T), count(F)) > k, this substring is invalid.
The method described in this solution is also known as the sliding window algorithm. During the process from left to right, we ensure that the length of the subsequence remains unchanged as max_size, just like moving a window of fixed length. As shown in the pictures below, if we set the window length to m = 3, we can find some valid windows.

- During the iteration, when we move the right boundary of the window from right - 1 to right, we don't need to recalculate the count of each answer over again, note that two adjacent windows only differ by two answers (answerKey[right], answerKey[right - m]). We only need to increment the count of answerKey[right] by 1 and decrement the count of answerKey[right - m] by 1, based on the result of the previous window.

- To quickly find the maximum valid window length, we can use binary search. To begin, we need to define a search space that ensures the maximum window length we are looking for is within this range. We can set the left boundary of the search space to left = 1, which represents the smallest window length, and the right boundary to right = n, which is the maximum possible window length.

- Next, we perform a binary search within the interval [left, right]. At each iteration, we find the midpoint of the interval, denoted as mid, and slide a window of length mid using the previous approach to check whether there exists at least one valid window. If such a window exists, we continue to search for a larger window length in [mid, right], the right half of the interval. Otherwise, if mid is still too large, we continue our search in [left, mid - 1], the left half of the search space.


Algorithm
1. Initialize the search space as left = 1, right = n.
2. Define a function isValid to help verify if a window of size size is valid:
  - Count the number of T and F in answerKey[:size] in a counter count, return true if min(counter[T], counter[F]) <= k
  - Iterate the index of the right boundary of the window from size - 1 to n. At each step i, increment counter[answerKey[i]] by 1 and decrement counter[answerKey[i - size]] by 1, return true if min(counter[T], counter[F]) <= k at any point in this iteration
  - Return false if we finish iterating without finding a valid window.
3. While left < right:
  - Find the middle value as mid = right - (right - left) / 2.
  - Check if the window of size mid is valid.
  - If isValid(mid) is true, let left = mid and repeat step 3.
  - If isValid(mid) is false, let right = mid - 1 and repeat step 3.
4. Return left once the search ends.

Complexity Analysis :
Let n be the length of the input string answerKey.
- Time complexity: O(n⋅log⁡n)
   - We set the search space to [1, n], it takes at most O(log⁡n) search steps.
   - At each step, we iterate over answerKey which takes O(n) time.
- Space complexity: O(1)

We only need to update some parameters left, right. During the iteration, we need to count the number of T and F, which also takes O(1) space.
*/

class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int left = k, right = n;
        
        while (left < right) {
            int mid = (left + right + 1) / 2;
            
            if (isValid(answerKey, mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    private boolean isValid(String answerKey, int size, int k) {
        int n = answerKey.length();
        Map<Character, Integer> counter = new HashMap<>();
        
        for (int i = 0; i < size; i++) {
            char c = answerKey.charAt(i);
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        
        if (Math.min(counter.getOrDefault('T', 0), counter.getOrDefault('F', 0)) <= k) {
            return true;
        }
        
        for (int i = size; i < n; i++) {
            char c1 = answerKey.charAt(i);
            counter.put(c1, counter.getOrDefault(c1, 0) + 1);
            char c2 = answerKey.charAt(i - size);
            counter.put(c2, counter.getOrDefault(c2, 0) - 1);
            
            if (Math.min(counter.getOrDefault('T', 0), counter.getOrDefault('F', 0)) <= k) {
                return true;
            }
        }
        
        return false;
    }
}
