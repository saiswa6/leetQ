class Solution {
    public int equalSubstring(String s, String t, int maxCost) {

        /*int n = s.length(), i = 0, j;
        for (j = 0; j < n; ++j) {
            maxCost -= Math.abs(s.charAt(j) - t.charAt(j));
            if (maxCost < 0) {
                maxCost += Math.abs(s.charAt(i) - t.charAt(i));
                ++i;
            }
        }
        return j - i;
        */
        int left = 0;
        int right = 0;
        int answer = 0;
        int sum = 0;
        int length = s.length();
        int cost [] = new int [length];
        for(int i = 0; i < length ; i++) {
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }

        for( ; right < length; right++) {
            sum += cost[right];

            if(sum > maxCost) {
                sum -= cost[left];
                left++;
            }

            answer = Math.max(right - left + 1, answer);
        }

        return answer;
        



/*
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
 */

    }
}