//Sliding Window

class Solution {
    public int maxVowels(String s, int k) {
        int start = 0;
        int countVowel = 0;
        int maxCount = 0;

        for (int end = 0; end < s.length(); end++) {
            if (isVowel(s.charAt(end))) {
                countVowel++;
            }

            if (end - start >= k) {  // Boundary condition
                if (isVowel(s.charAt(start))) {
                    countVowel--;    
                }
                start++;   // Irrespective of vowel and not vowel, increment start.
            }

            if (end - start == k - 1) {
                maxCount = Math.max(maxCount, countVowel);  // Update
            }
        }

        return maxCount;
    }

    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
