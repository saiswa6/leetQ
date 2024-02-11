class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0;
        int right = 0;
        int answer = 0;
        Map<Character, Integer> hashMap = new HashMap<>();
        int distinctChars = 0;

        for( ; right < s.length() ; right++) {
            char rightChar = s.charAt(right);
            if(hashMap.containsKey(rightChar)) {
                hashMap.put(rightChar, hashMap.get(rightChar) + 1);
            } else {
                hashMap.put(rightChar, 1);
                distinctChars++;
            }

            while(distinctChars > 2) {
                char leftChar = s.charAt(left);
                int count = hashMap.get(leftChar);

                if(count == 1) {
                    hashMap.remove(leftChar);
                    distinctChars--;
                } else {
                    hashMap.put(leftChar, count - 1);
                }
                left++;
            }
            answer = Math.max(answer, right - left + 1);
        }
        return answer;
    }
}