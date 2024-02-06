class Solution {
    public int strStr(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();

        for (int windowStart = 0; windowStart <= n - m; windowStart++) {
            for (int i = 0; i < m; i++) {
                if (needle.charAt(i) != haystack.charAt(windowStart + i)) {
                    break;
                }
                if (i == m - 1) {
                    return windowStart;
                }
            }
        }

        return -1;
    }
}

/*class Solution {
    public int strStr(String haystack, String needle) {
        int hayLength = haystack.length();
        int needleLength = needle.length();

        if(needleLength > hayLength) {
            return -1;
        }
        char hayArray [] = haystack.toCharArray();
        char needleArray[] = needle.toCharArray();


        int startPoint = -1;
        int hayPointer = 0;
        int needlePointer = 0;

        while(hayPointer < hayLength) {
            if(hayArray[hayPointer] == needleArray[needlePointer]) {
                if(needlePointer == 0) {
                    startPoint = hayPointer;
                } 
                if(hayPointer-startPoint + 1 == needleLength) {
                    return startPoint;
                }
                hayPointer++;
                needlePointer++;

            } else {
                needlePointer = 0;
                hayPointer++;
                startPoint = -1;
            }
        }

        return startPoint;
    }
}*/