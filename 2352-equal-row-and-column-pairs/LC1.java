/*
Approach 1: Brute Force
Intuition
Since we need to find the number of matching rows and columns, we traverse through every possible combination of rows and columns (row R, col C) and check if all elements at the same position in R and C are equal to 
each other.

*/
class Solution {
    public int equalPairs(int[][] grid) {
        int count = 0, n = grid.length;
        
        // Check each row r against each column c.
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                boolean match = true;

                // Iterate over row r and column c.
                for (int i = 0; i < n; ++i) {
                    if (grid[r][i] != grid[i][c]) {
                        match = false;
                        break;
                    }
                }

                // If row r equals column c, increment count by 1.
                count += match ? 1 : 0;
            }
        }
        
        return count;
    }
}

/*
Complexity Analysis
Let n×n be the size of grid.
Time complexity: O(n3)
There are a total of O(n2) pairs when iterating over each row R and column C. Traversing each element in R and C takes O(n) time.
Space complexity: O(1)

we are use constant amount of extra space to store the answer count.
*/
