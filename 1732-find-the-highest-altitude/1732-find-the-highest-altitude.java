class Solution {
    public int largestAltitude(int[] gain) {
        int answer = Integer.MIN_VALUE;
        int current = 0;
        answer = Math.max(answer, current);
        for(int i = 0 ; i < gain.length; i++) {
            current = current + gain[i];
            answer = Math.max(answer, current);
        }   
    return answer;
    }
}