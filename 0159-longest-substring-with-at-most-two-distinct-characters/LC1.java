/*
Approach 1: Sliding Window
Intuition
- To solve the problem in one pass let's use here sliding window approach with two set pointers left and right serving as the window boundaries.
- The idea is to set both pointers in the position 0 and then move right pointer to the right while the window contains not more than two distinct characters. If at some point we've got 3 distinct characters, let's move left pointer to keep not more than 2 distinct characters in the window.
- Basically, that's the algorithm: to move the sliding window along the string, to keep not more than 2 distinct characters in the window, and to update the max substring length at each step.
- There is just one more question to reply - how to move the left pointer to keep only 2 distinct characters in the string?
- Let's use for this purpose a hashmap containing all characters in the sliding window as keys and their rightmost positions as values. At each moment, this hashmap could contain not more than 3 elements.
- For example, using this hashmap one knows that the rightmost position of character e in "eeeeeeeet" window is 8 and so one has to move left pointer in the position 8 + 1 = 9 to exclude the character e from the sliding window.
- Do we have here the best possible time complexity? Yes, we do - it's the only one who passes along the string with N characters and the time complexity is O(N)\mathcal{O}(N)O(N).

Algorithm
1. Return N if the string length N is smaller than 3.
2. Set both set pointers at the beginning of the string left = 0 and right = 0 and init max substring length max_len = 2.
3. While right pointer is less than N:
  - If the hashmap contains less than 3 distinct characters, add the current character s[right] in the hashmap and move right pointer to the right.
  - If the hashmap contains 3 distinct characters, remove the leftmost character from the hashmap and move the left pointer so that the sliding window contains 2 distinct characters only.
  - Update max_len.

Complexity Analysis
Time complexity: O(N) where N is the number of characters in the input string.
Space complexity: O(1) since additional space is used only for a hashmap with at most 3 elements.
*/

class Solution {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int n = s.length();
    if (n < 3) return n;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position
    // in the sliding window
    HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

    int max_len = 2;

    while (right < n) {
      // when the slidewindow contains less than 3 characters
      hashmap.put(s.charAt(right), right++);

      // slidewindow contains 3 characters
      if (hashmap.size() == 3) {
        // delete the leftmost character
        int del_idx = Collections.min(hashmap.values());
        hashmap.remove(s.charAt(del_idx));
        // move left pointer of the slidewindow
        left = del_idx + 1;
      }

      max_len = Math.max(max_len, right - left);
    }
    return max_len;
  }
}


/*
Java HashMap values()
=====================

hashmap.values()
Here, hashmap is an object of the HashMap class.

values() Parameters
The values() method does not take any parameter.

values() Return Value
returns a collection view of all values of the hashmap
------------------------------------------------------------------

Java Collections min(coll) Method
---------------------------------
It is the collection whose minimum element is to be determined.
The min() method of Java Collections class is used to get the minimum element of the given collection, according to the natural ordering of its elements.
*/



/*
  I found this statement confusing "delete the leftmost character". Its actually not considering the left most character in the sliding window but in hash map(after sorting based on last seen index of the character). For example "eceba" is the given input. If we just consider the first character inserted in hash map then actually 'e' will be removed when encounter more than 2 distinct characters in string. But in fact we remove 'c' from hash map.
  */
