class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image[0].length;
        int mid = n/2; // find mid element , n=3=> mid = 1(drawback: for odd, center row is missed for which we have to do negation), n=4 => mid = 2

        for (int i=0;i<n;i++) {
            if(n%2 == 1) { // when n is odd, for center, negate, for even it will be covered. 
                image[i][mid] = image[i][mid] == 0 ? 1 : 0;
            }
            for (int j=0;j<mid;j++) {
                int temp = image[i][j];
                image[i][j] = (image[i][n-j-1] == 0) ? 1 : 0 ;
                image[i][n-j-1] = (temp == 0) ? 1 : 0;
            }
        }

        return image;
    }
    
}
