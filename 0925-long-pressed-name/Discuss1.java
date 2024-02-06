/*
Don't have much time, but the keys are:
1. 2 pointers i and j are handling the same characters at the same time(let's say c)
2. The count of consecutive c in typed >= the count of consecutive c in name

time complexity: O(m + n)
space complexity: O(1)
*/

class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        
        while(i < m && j < n){
            char c1 = name.charAt(i), c2 = typed.charAt(j);
            if(c1 != c2) return false; // we are handling different chars, no!
            
			// count of consecutive c1/c2
            int count1 = 0; 
            while(i < m && name.charAt(i) == c1){
                count1++;
                i++;
            }
            
			// count of consecutive c1/c2
            int count2 = 0; 
            while(j < n && typed.charAt(j) == c2){
                count2++;
                j++;
            }
            
            if(count2 < count1) return false;
        }
        
		// they both reach the end
        return i == m && j == n;
    }
}


// similar

public boolean isLongPressedName(String nameStr, String typeStr) {
        char[] name = nameStr.toCharArray(), typed = typeStr.toCharArray();
        int n = 0, t = 0;
        while (n < name.length && t < typed.length) {
            int need = 1;
            char c = name[n++];
            while (n < name.length && c == name[n]) {
                n++;
                need++;
            }
            while (t < typed.length && typed[t] == c) {
                need--;
                t++;
            }
            if (need > 0)
                return false;
        }
        return n == name.length && t == typed.length;
    }
