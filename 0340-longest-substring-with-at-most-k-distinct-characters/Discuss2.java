/*
Java O(nlogk) using TreeMap to keep last occurrence Interview "follow-up" question!
Solving the problem with O(n) time is not enough, some interviewer may require this solution as a followup. Instead of recording each char's count, we keep track of char's last occurrence. If you consider k as constant, it is also a O(n) algorithm.

inWindow keeps track of each char in window and its last occurrence position

lastOccurrence is used to find the char in window with left most last occurrence. A better idea is to use a PriorityQueue, as it takes O(1) to getMin, However Java's PQ does not support O(logn) update a internal node, it takes O(n). TreeMap takes O(logn) to do both getMin and update.
Every time when the window is full of k distinct chars, we lookup TreeMap to find the one with leftmost last occurrence and set left bound j to be 1 + first to exclude the char to allow new char coming into window.

*/

   public class Solution {
        public int lengthOfLongestSubstringKDistinct(String str, int k) {
            if (str == null || str.isEmpty() || k == 0) {
                return 0;
            }
            TreeMap<Integer, Character> lastOccurrence = new TreeMap<>();
            Map<Character, Integer> inWindow = new HashMap<>();
            int j = 0;
            int max = 1;
            for (int i = 0; i < str.length(); i++) {
                char in = str.charAt(i);
                while (inWindow.size() == k && !inWindow.containsKey(in)) {
                    int first = lastOccurrence.firstKey();
                    char out = lastOccurrence.get(first);
                    inWindow.remove(out);
                    lastOccurrence.remove(first);
                    j = first + 1;
                }
                //update or add in's position in both maps
                if (inWindow.containsKey(in)) {
                    lastOccurrence.remove(inWindow.get(in));
                }
                inWindow.put(in, i);
                lastOccurrence.put(i, in);
                max = Math.max(max, i - j + 1);
            }
            return max;
        }
    }



/*
This is a nice solution and probably deserves more up votes.

I would say using a linkedHashMap data structure makes the performance O(n).
*/

public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int lo = 0, hi = 0;
        int n = s.length();
        int max = 0;
        if (k == 0) return 0;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        
        while (hi < n) {
            char ch = s.charAt(hi);
            if (map.containsKey(ch) || map.size() < k) {
                map.remove(ch);
                map.put(ch, hi);
                max = Math.max(max, hi++ - lo + 1);
            }
            else {
                Character key = map.keySet().iterator().next();
                lo = map.get(key);
                map.remove(key);
                lo++;
            }
        }
        return max;
    }
