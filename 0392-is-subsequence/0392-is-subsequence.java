class Solution {
    public boolean isSubsequence(String s, String t) {
     int sPointer = 0;
     int tPointer = 0;
     for(tPointer = 0; tPointer < t.length() ; tPointer++) {
        if(t.charAt(tPointer) == s.charAt(sPointer)) {
            sPointer++;
            if(sPointer == s.length()) {
                return true;
            }
        } 
     }   
     return false;
    }
}