/**
 * Optimal one-pass in-place solution
 *
 * If the values are not same, swap and flip will not change anything.
 * If the values are same, we will flip both.
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Matrix Size
 */
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }

        for (int[] row : image) {
            int start = 0;
            int end = row.length - 1;
            while (start <= end) {
                if (row[start] == row[end]) {
                    row[start] ^= 1;
                    row[end] = row[start];
                }
                start++;
                end--;
            }
        }

        return image;
    }
}


//2nd Solution

//The logic is simular to swaping two position within an array but just done over many position. Idea is that you will perform the swap over every row of the matrix and only to the half way point of the row. When performing the swap, invert the value to achieve the invert. Inversion is done using 1 - cell value since it's only 1 and 0. (1 - 1 = 0, 1 - 0 = 1).
//Here is the code.

class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for(int i = 0; i < A.length ; i++){
            for(int j = 0; j < (A[0].length+1)/2; j++){
                int temp = 1-A[i][j];
                A[i][j] = 1-A[i][A[i].length-j-1];
                A[i][A[i].length-j-1] = temp;
            }
        }
        return A;
    }
