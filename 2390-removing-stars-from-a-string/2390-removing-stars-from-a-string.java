class Solution {
    public String removeStars(String s) {
        char sArray[] = s.toCharArray();
        int length = s.length();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < length; i++) { // Push element when it is non-star, pop when it is '*'
            if (sArray[i] == '*' && !st.isEmpty()) { 
                st.pop();
            } else {
                st.push(sArray[i]);
            }
        }

        StringBuilder stringBuilder = new StringBuilder(); // Traverse and append
        while(!st.isEmpty()) {
            stringBuilder =  stringBuilder.append(st.pop());
        }

        return stringBuilder.reverse().toString();
    }
}

/*
// Brute Force
class Solution {
    public String removeStars(String s) {
        char sArray[] = s.toCharArray();
        int length = s.length();

        for (int i = 0; i < length; i++) { // '0' is used to mark char as non existent as deletion is costly.
            if (sArray[i] == '*') { // If '*' is found, traverse back to see first non star and also check not '0'
                for (int j = i - 1; j >= 0; j--) {
                    if (sArray[j] != '0') { // Replace with '0' when non-star and '0'
                        sArray[j] = '0';
                        break; // Important break after one char is found.
                    }
                }
                sArray[i] = '0'; // Mark '*' also '0' to indicate as deleted
            }
        }

        StringBuilder stringBuilder = new StringBuilder(); // Traverse and append
        for (int i = 0; i < length; i++) {
            if (sArray[i] != '0') {
                stringBuilder.append(sArray[i]);
            }
        }

        return stringBuilder.toString();
    }
}
*/