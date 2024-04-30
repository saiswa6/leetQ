class Solution {
    public String gcdOfStrings(String str1, String str2) {
        String result = (str1.length() > str2.length()) ? str2 : str1 ;
        while(result.length() != 0) {
            if(isDivides(str1, result) && isDivides(str2, result)) {
                return result;
            } else {
                result = result.substring(0, result.length() - 1);
            }
        }
        return "";
    }

    public boolean isDivides(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
    
        if(bLen > aLen) {
            return false;
        }
        int j = 0;
        for(int i = 0 ; i < aLen; i++) {
            if(b.charAt(j) != a.charAt(i)) {
                return false;
            }
            j++;
            if(j == bLen) {
                j = 0;
            }
        }
        return j == 0;

    }
}