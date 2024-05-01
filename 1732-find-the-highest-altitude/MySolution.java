// Compare and update altitude at each level
// Be careful, compare with altitude 0.

class Solution {
    public int largestAltitude(int[] gain) {
        int answer = Integer.MIN_VALUE; // Min Value in Integer
        int current = 0;
        answer = Math.max(answer, current); // Important. compare with current altiude 0.

        for (int i = 0; i < gain.length; i++) {
            current = current + gain[i]; // Update the current positon after each altitude
            answer = Math.max(answer, current); // Update the answer
        }
        return answer;
    }
}
