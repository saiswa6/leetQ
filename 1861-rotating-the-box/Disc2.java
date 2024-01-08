// Similar to my solution
// Thanks! I was doing the normal way, first rotate the box and move stones. It is similar to 723. Candy Crush, both use 2 pointers strategy to fill the empty spaces
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        // first rotate the box
        // 0-th row becomes the last col in the newBox => newJ = m - 1 - i
        // 0-th col becomes the same row in the newBox => newI = j
    
        // (newI, newJ)  = (j, m - 1 - i)
        int m = box.length;
        int n = box[0].length;
        char[][] newBox = new char[n][m];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // put each cell in box to newBox
                int newI = j;
                int newJ = m - 1 - i;
                newBox[newI][newJ] = box[i][j];
            }
        }
        
        // then we move the stones, the box is n * m
        // for each col, we move the stone
        for(int j = 0; j < m; j++) {
            // move stones
            int candi = n - 1; // candidate
            int cur = n - 1;
            while(cur >= 0) {
                if(newBox[cur][j] == '#') {
                    newBox[candi][j] = '#';
                    candi--;
                } else if(newBox[cur][j] == '*') {
                    while(candi > cur) {
                        newBox[candi--][j] = '.';
                    }
                    candi = cur - 1;
                }
                cur--;
            }
            
            while(candi >= 0) {
                newBox[candi--][j] = '.';
            }
        }
        
        return newBox;
    }
}
