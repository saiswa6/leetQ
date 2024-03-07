// WRONG SOLUTION
// This solution is failing in case of missississpi and needle issipi
// Example: Let haystack be "mississipi" and needle be “issipi”. 
class Solution {
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
}
