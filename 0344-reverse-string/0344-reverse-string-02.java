2. Recursive Call

Explanation is same as Two Pointer's what only different happening is, we are solve this using our fucntion call every time. We are just doing the work to reverse only 1 time first & last element, rest one we are not worrying about!! Because recursion will do that for us :)



class Solution {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        solve(s, i, j);
    }
    public void solve(char[] s, int i, int j){
        if(i >= j) return;
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        solve(s, ++i, --j);
    }
}


3. Stack

class Solution {
    public void reverseString(char[] s) {
        Stack<Character> st = new Stack<>();
        String str = new String(s);
        for(int i = 0; i < str.length(); i++){
            st.push(s[i]);
        }
        char ans[] = new char[s.length];
        int i = 0;
        while(st.size() > 0){
            s[i++] = st.pop();
        }
        for(int j = 0; j < str.length(); j++){
            ans[j] = str.charAt(j);
        }
    }
}
