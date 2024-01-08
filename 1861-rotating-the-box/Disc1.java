/*
For each row:
1. empty will point to the last cell that is empty. Initially empty = columns - 1.
2. Start from last column in the row, for each column c:
  1. if current cell contains a stone, we will move it from current cell to the empty cell(which is represented by empty variable).
  2. if current cell contains an obstacle, we will change value of empty to c-1.
3. After customising the array box, we will create another array box2 which will be rotated version of box.

Why is value of empty changed to c-1 if current cell contains obstacle?
The stone at row = 0 col = 3 will move to row = 0 col = 4. But The stone at row = 0 col = 0 cannot move past obstacle. So it can only move to row = 0 col = 1.

 T.C : O(MN)
  */
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int r = box.length, c = box[0].length;
        char[][] box2 = new char[c][r];
        
        for(int i = 0; i<r; ++i){
            int empty = c-1;
            for(int j = c-1; j>=0; --j){
                if(box[i][j] == '*'){
                    empty = j-1;
                }
                if(box[i][j] == '#'){
                    box[i][j] = '.';
                    box[i][empty] = '#';
                    --empty;
                }
            }
        }
        
        for(int i = 0; i<r; ++i){
            for(int j = c-1; j>=0; --j)
                box2[j][r-i-1] = box[i][j];
        }
        
        return box2;
    }
}

// Similar idea, at first, move everything to the right, then rotate by 90 degs:

public char[][] rotateTheBox(char[][] arr) {
        final char WALL = '*', STONE='#', NOTHING='.';
        //move everything to the right towards the closest wall
        for (var row:arr){
            for (int i=row.length-1,j=i;i>=0;i--){
                var c = row[i];
                if (c==WALL){
                    j=i-1;
                } else if (c==STONE){
                    row[i]   = NOTHING;
                    row[j--] = STONE;
                }
            }
        }
        //rotate by 90 degrees
        var res = new char[arr[0].length][arr.length];
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                res[j][res[0].length-i-1]=arr[i][j];
            }
        }
        return res;
    }
