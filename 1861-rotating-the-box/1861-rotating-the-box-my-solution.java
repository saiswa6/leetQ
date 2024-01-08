// USED STACK TO PROCESS ELEMENTS. A STACK OF COLUMN LENGTH IS NEEDED. 

class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;  // row length
        int n = box[0].length; //column length
        char result [][] = new char[n][m];

        for(int i = 0; i < m; i++) {
            Stack<Character> st = process(box[i], n);
            for (int j = 0; j < n ; j++) {
                result[j][m-i-1] = st.peek(); // TAKE EACH ELEMENT FROM STACK AND INSERT IN MATRIX.
                st.pop();
            }
        }

        return result;
    }
// IF EMPTY DON'T INSERT.
    public Stack<Character> process(char box[], int n) { // Process each row from last to first.
        Stack<Character> st = new Stack<>();
        int count = n-1; 
        for(int j = n - 1; j >= 0 ; j--) {
            if(box[j] == '#') {  // IF IS STONE , PUSH IT
                st.push('#');
                count--;
            } else if (box[j] == '*') { // IF OBSTACLE, BEFORE PUSHING WAIT AND PUSH EMPTY BY COUNT AND THEN PUSH OBSTACLE 
                while ( count > j) {
                    st.push('.');
                    count--;
                }
                st.push('*');
                count--;
            }
        }
        while(count >= 0) {  // AT THE END WHATEVER REMAINING, PUSH EMPTY.
            st.push('.');
            count--;
        }

        return st;
    }
}
