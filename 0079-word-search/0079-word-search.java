class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean pathMatrix[][] = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(search(board, pathMatrix, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean search(char[][] board, boolean pathMatrix[][], int i, int j, String word, int index) {
        if(index == word.length()) {
            return true;
        }

        if(!pathMatrix[i][j] && board[i][j] == word.charAt(index)) {
            pathMatrix[i][j] = true;

            if(index + 1 == word.length()) {
                return true;
            }
            if(j-1 >= 0 && search(board, pathMatrix, i, j-1, word, index + 1)) {
                return true;
            }

            if(j+1 < board[0].length && search(board, pathMatrix, i, j+1, word, index + 1)) {
                return true;
            }

            if(i-1 >= 0 && search(board, pathMatrix, i-1, j, word, index + 1)) {
                return true;
            }

            if(i+1 < board.length && search(board, pathMatrix, i+1, j, word, index + 1)) {
                return true;
            }
            pathMatrix[i][j] = false;
            return false;
        } /*else {
            if(j+1 < board[0].length && search(board, pathMatrix, i, j+1, word, index + 1)) {
                return true;
            }

            if(i+1 < board.length && search(board, pathMatrix, i+1, 0, word, index + 1)) {
                return true;
            }
        }*/
          
        return false;

    }
}