class Solution {
    public int compress(char[] chars) {
        int slow = 0;
        int length = chars.length;
        for(int fast = 0; fast < length ; ) {
            char letter = chars[fast] ;
            int count = 0;

            while ( fast < length && letter == chars[fast]) {
                fast++;
                count++;
            }

            chars[slow++] = letter;

            if (count > 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[slow++] = c;
                }
            }
        }
        return slow;
    }
}