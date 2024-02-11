class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int answer = 0;
        int repeatedChars = 0;
        Map<Character, Integer> trackMap = new HashMap<>();

        for( ; right < s.length() ; right++) {
            char r = s.charAt(right);
            if(trackMap.containsKey(r)) {
                trackMap.put(r, trackMap.get(r) + 1); // Increase frequency of char
                repeatedChars++;
            } else {
                trackMap.put(r, 1);
            }

            while(repeatedChars > 0) {
                Character leftChar = s.charAt(left);
                    int count = trackMap.get(leftChar);
                    if(count == 1) {
                        trackMap.remove(leftChar);  // If one element only, then apply, remove completely
                    } else {
                        repeatedChars--;
                        trackMap.put(leftChar, count - 1); // Reduce the count of leftChar
                    }
                left++; // Shrink the window
            }

            answer = Math.max(answer, right-left + 1); // Track for max answer.
        }
        return answer;
    }
}
