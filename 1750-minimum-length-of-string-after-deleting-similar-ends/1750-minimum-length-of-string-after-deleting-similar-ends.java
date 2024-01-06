class Solution {
    public int minimumLength(String s) {
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int answer = -1;
        int left = 0, right = length - 1;
        while(left <= right) {
            if (right - left == 0) {
                answer = 1;
            }
            if(s.charAt(left) == s.charAt(right)) {
                char toRemove = s.charAt(left);
                left++;
                right--;
                while (left <= right && s.charAt(left) == toRemove) {
                    left++;
                }
                while (left <= right && s.charAt(right) == toRemove) {
                    right--;
                }
            }
            else {
                break;
            }
        }
        if (answer == -1 ) {
            answer = (left > right)? 0 : (right - left + 1) ;
        }
        return answer;
    }
}