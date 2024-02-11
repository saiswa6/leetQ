/*
Approach 3: Sliding Window Optimized
Intuition
- The above solution requires at most 2n steps. In fact, it could be optimized to require only n steps. Instead of using a set to tell if a character exists or not, we could define a mapping of the characters to its index. Then we can skip the characters immediately when we found a repeated character.

Algorithm
The reason is that if s[j] have a duplicate in the range [i,j) with index j′ , we don't need to increase iii little by little. We can skip all the elements in the range [i,j′] and let i to be j′+1 directly.

Complexity Analysis
Time complexity : O(n). Index j will iterate nnn times.

Space complexity : O(min(m,n)). Same as the previous approach.
*/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}

/*
Tips
- All previous implementations have no assumption on the charset of the string s.

- If we know that the charset is rather small, we can mimic what a HashSet/HashMap does with a boolean/integer array as direct access table. Though the time complexity of query or insertion is still O(1), the constant factor is smaller in an array than in a HashMap/HashSet. Thus, we can achieve a shorter runtime by the replacement here.

Commonly used tables are:
- int[26] for Letters 'a' - 'z' or 'A' - 'Z'
- int[128] for ASCII
- int[256] for Extended ASCII

For this implementation, the space complexity is fixed to O(m)O(m)O(m) while the time complexity keeps unchanged. mmm is the size of the charset.
*/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Integer[] chars = new Integer[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);

            Integer index = chars[r];
            if (index != null && index >= left && index < right) {
                left = index + 1;
            }

            res = Math.max(res, right - left + 1);

            chars[r] = right;
            right++;
        }

        return res;
    }
}
