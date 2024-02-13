// Time Complexity: O(N) where N is the length of strings s, t
// Space Complexity: O(N) for diff
// Note: O(1) space can be achieved if we don't create the diff array - we can just get the values on the fly.

class Solution {
    public int equalSubstring(String s, String t, int maxCost) {

        // Non - shrinkable version
        int left = 0;
        int right = 0;
        int answer = 0;
        int sum = 0;
        int length = s.length();
        int cost [] = new int [length];
        for(int i = 0; i < length ; i++) { // calculate the cost and keep in array , so that time complexity reduces little.
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }

        for( ; right < length; right++) {
            sum += cost[right];

            if(sum > maxCost) {
                sum -= cost[left];
                left++;
            }

            //answer = Math.max(right - left + 1, answer);
        }

        return right - left;
        



/*
// Shrinkable Version 
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
