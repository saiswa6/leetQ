// Two pointers
// here , k is empty, indicates next empty place.
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        if (box == null || box.length == 0 || box[0] == null || box[0].length == 0) return box;
        int m = box.length, n = box[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = n - 1, k = n - 1; j >= 0; --j) {
                if (box[i][j] == '*') {
                    k = j - 1;
                }
                else if (box[i][j] == '#') {
                    char tmp = box[i][k];
                    box[i][k] = box[i][j];
                    box[i][j] = tmp;
                    k--;
                }
            }
            for (int j = 0; j < n; ++j) {
                res[j][m - i - 1] = box[i][j];
            }
        }
        return res;
    }
}
