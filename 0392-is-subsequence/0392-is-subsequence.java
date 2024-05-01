class Solution {
    public boolean isSubsequence(String s, String t) {
     if(s.length() == 0) {  // Important
        return true;
     }
     if(t.length() == 0) {  // Important
        return false;
     }
     int sPointer = 0;
     int tPointer = 0;
     for(tPointer = 0; tPointer < t.length() ; tPointer++) {
        if(t.charAt(tPointer) == s.charAt(sPointer)) {  // Equal - increase pointer of s.
            sPointer++;
            if(sPointer == s.length()) {
                return true;
            }
        } 
     }   
     return false;
    }
}
