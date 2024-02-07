class Solution {
    public int compareVersion(String version1, String version2) {
        String [] v1tokens = version1.split("\\.");  
        String [] v2tokens = version2.split("\\.");  
        int v1Length = v1tokens.length;
        int v2Length = v2tokens.length;
        int i = 0, j = 0;

        while (i < v1Length || j < v2Length) {
            int firstToken = (i < v1Length) ? convertStringToInt(v1tokens[i]) : 0;
            int secondToken = (j < v2Length) ? convertStringToInt(v2tokens[j]) : 0;
            if(firstToken < secondToken) {
                return -1;
            } else if (firstToken > secondToken) {
                return 1;
            } else {
                i++;
                j++;
            }   
        }
        return 0;  

    }

    public int convertStringToInt(String num) {
        int result = 0;
        for(int i=0;i<num.length();i++) {
            result = result*10 + (num.charAt(i) - '0');
        }
        return result;
    }
}