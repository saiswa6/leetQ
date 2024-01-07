class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char result [][] = new char[n][m];

        for(int i = 0; i < m; i++) {
            Stack<Character> st = process(box[i], n);
            for (int j = 0; j < n ; j++) {
                result[j][m-i-1] = st.peek();
                st.pop();
            }
        }

        return result;
    }

    public Stack<Character> process(char box[], int n) {
        Stack<Character> st = new Stack<>();
        int count = n-1; 
        for(int j = n - 1; j >= 0 ; j--) {
            if(box[j] == '#') {
                st.push('#');
                count--;
            } else if (box[j] == '*') {
                while ( count > j) {
                    st.push('.');
                    count--;
                }
                st.push('*');
                count--;
            }
        }
        while(count >= 0) {
            st.push('.');
            count--;
        }

        return st;
    }
}