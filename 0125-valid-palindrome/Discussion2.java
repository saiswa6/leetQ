// Using Regular Expression in Java

public class Solution {
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}

/////////////////////////////////////////////////////////////////////////////

class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        int i = 0;
        int j = s.length() - 1;
        while(i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}


///Note we can save 1 iteration with i < j rather than i <= j because s[i] == s[j] for all i, j where i=j.


