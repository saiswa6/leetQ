class Solution {
    public String mergeAlternately(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();
        int totalLength = word1Length + word2Length;
        char result[]  = new char[totalLength];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < word1Length || j < word2Length ) {
            if(i < word1Length) {
                result[k] = word1.charAt(i);
                i++;
                k++;
            }

            if(j < word2Length) {
                result[k] = word2.charAt(j);
                j++;
                k++;
            }

        }

        return new String(result);
        
    }
}
