/*
Approach #1: Min Array [Accepted]
Intuition
For each index S[i], let's try to find the distance to the next character C going left, and going right. The answer is the minimum of these two values.

Algorithm
When going left to right, we'll remember the index prev of the last character C we've seen. Then the answer is i - prev.
When going right to left, we'll remember the index prev of the last character C we've seen. Then the answer is prev - i.
We take the minimum of these two answers to create our final answer.

Complexity Analysis
Time Complexity: O(N), where N is the length of S. We scan through the string twice.
Space Complexity: O(N), the size of ans.

Integer overflow, for case when going left and c is not found yet,
lets say prev = INT_MIN and
ans[i] = min(ans[i] , i-prev);
here i-prev is = i- INT_MIN = i - (-2147483647) = i+2147483647 , can be overflowed.
That's why / 2 is done 
*/

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

// Best similar Disc Solution

class Solution {
    public int[] shortestToChar(String S, char C) {
        char[] arrS=S.toCharArray(); 
        int[] dist=new int[arrS.length];
        int disToL=S.length(), disToR=S.length(); 
        
        for(int i=0;i<arrS.length;i++){ //pass 1, determine distance to nearest C on the left 
            if(arrS[i]==C)
                disToL=0;
            dist[i]=disToL;
            disToL++;
        }
        
        for(int i=arrS.length-1;i>=0;i--){ //pass 2, determine distance to nearest C on the right, compare with previous pass and take minimum 
            if(arrS[i]==C)
                disToR=0;
            dist[i]=Math.min(dist[i],disToR);
            disToR++;
        }
        
        return dist;
    }
}
