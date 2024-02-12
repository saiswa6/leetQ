class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int right = 0;
        int answer = 0;
        int sum = 0;

        for( ; right < s.length(); right++) {
            sum += Math.abs(s.charAt(right) - t.charAt(right));

            while(sum > maxCost) {
                sum -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            answer = Math.max(right - left + 1, answer);
        }

        return answer;
    }
}