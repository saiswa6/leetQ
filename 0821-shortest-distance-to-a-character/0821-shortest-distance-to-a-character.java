class Solution {
    public int[] shortestToChar(String s, char c) {
        int length = s.length();
        int ans [] = new int[length];
        int prev = Integer.MIN_VALUE / 2 ;
        for(int i = 0; i < length ; i++) {
            if(s.charAt(i) == c) {
                prev = i;
            }
            ans[i] = i - prev;
        }

        prev = Integer.MAX_VALUE / 2 ;
        for(int j = length - 1;j >= 0; j--) {
            if(s.charAt(j) == c) {
                prev = j;
            }
            ans[j] = Math.min(ans[j], prev - j);
        }

        return ans;
    }
}