// Aditya Verma YouTube Solution from LCS (Longest Common Subsequence)

//String1 = s, String2 = reverse(s)

/*
LCS : 
1. Recusrsion
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        return lcsHelper(text1,text2,text1.length(),text2.length());
    }

    int lcsHelper(String word1, String word2, int len1, int len2){

        if(len1 == 0 || len2 == 0){
            return 0;
        }

        if(word1.charAt(len1-1) == word2.charAt(len2-1)){
             return 1 + lcsHelper(word1,word2,len1-1,len2-1);
        }else{
           return Math.max(lcsHelper(word1,word2,len1-1,len2),
                           lcsHelper(word1,word2,len1,len2-1));
        }

    }
}

2. Bottom Up Memorization
class Solution {
    
    private int[][] memo;
    
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        memo = new int[len1+1][len2+1];
        for(int[] arr : memo)
            Arrays.fill(arr,-1);
        return lcsHelper(text1,text2,len1,len2);
    }

    int lcsHelper(String word1, String word2, int len1, int len2){

        if(len1 == 0 || len2 == 0){
            return 0;
        }
        
        if(memo[len1][len2] != -1)
            return memo[len1][len2];

        if(word1.charAt(len1-1) == word2.charAt(len2-1)){
            memo[len1-1][len2-1] = 1 + lcsHelper(word1,word2,len1-1,len2-1);
            return memo[len1-1][len2-1];
        }else{
            memo[len1-1][len2-1] = Math.max(lcsHelper(word1,word2,len1-1,len2), lcsHelper(word1,word2,len1,len2-1));
            return memo[len1-1][len2-1];
        }

    }
}

3. Top Down 

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        
        int len1 = text1.length()+1;
        int len2 = text2.length()+1;
        
        int[][] dp = new int[len1][len2];
        
        
        for(int i=0; i<len1; i++) {
             dp[i][0] = 0;
        }
        
        for(int i=0; i<len2; i++) {
             dp[0][i] = 0;
        }
        
        
        for(int i=1; i<len1; i++){
            for(int j=1; j<len2; j++){
                
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                
            }
        }   
        
        return dp[len1-1][len2-1];
        
    }
}

*/

class Solution {
    public int longestPalindromeSubseq(String s) {

        String reverseString = new StringBuilder(s).reverse().toString();
        int length1 = s.length() + 1;
        int length2 = reverseString.length() + 1;

        int dp[][] = new int[length1][length2];

        for (int i = 0; i < length1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < length2; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                if (s.charAt(i - 1) == reverseString.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[length1 - 1][length2 - 1];
    }
}
