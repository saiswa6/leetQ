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
                trackMap.put(r, trackMap.get(r) + 1);
                repeatedChars++;
            } else {
                trackMap.put(r, 1);
            }
            //trackMap.put(r, trackMap.getOrDefault(r,0) + 1);
            // if(trackSet.contains(s.charAt(right))) {
            //     repeatedChars++;
            // } 
            // trackSet.add(s.charAt(right));

            while(repeatedChars > 0) {
                Character leftChar = s.charAt(left);
                    
                    int count = trackMap.get(leftChar);
                    if(count == 1) {
                        trackMap.remove(leftChar);
                    } else {
                        repeatedChars--;
                        trackMap.put(leftChar, count - 1);
                    }
                    
                    

                
                left++;
            }

            answer = Math.max(answer, right-left + 1);
        }
        return answer;
    }
}