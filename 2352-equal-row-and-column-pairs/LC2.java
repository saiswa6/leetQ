/*
Approach 2: Hash Map
Intuition
- we can optimize this approach by using a hash map data structure to reduce the time complexity.
- In this approach, we can consider each row as the key and store it in a hash map. The corresponding value for each key would be the frequency of that row in the grid. 
- Then, we can traverse through each column of the grid and increment the answer by the frequency of the equivalent row in the hash map.

**
Note that arrays cannot typically be used as keys, so we need to convert them into equivalent hashable objects, such as strings in Java.
The converted object still maintains a one-to-one correspondence with the original object, allowing us to record the frequency of the original array by hash map.
*/

class Solution {
    public int equalPairs(int[][] grid) {
        int count = 0;
        int n = grid.length;

        // Keep track of the frequency of each row.
        Map<String, Integer> rowCounter = new HashMap<>();
        for (int[] row : grid) {
            String rowString = Arrays.toString(row);
            rowCounter.put(rowString, 1 + rowCounter.getOrDefault(rowString, 0));
        }

        // Add up the frequency of each column in map.
        for (int c = 0; c < n; c++) {
            int[] colArray = new int[n];
            for (int r = 0; r < n; ++r) {
                colArray[r] = grid[r][c];
            }
            count += rowCounter.getOrDefault(Arrays.toString(colArray), 0);
        }

        return count;
    }
}

/*
Complexity Analysis
Let n×n be the size of grid.

Time complexity: O(n2)
We iterate over each row and column only once, converting one array of length nnn into a hashable object takes O(n) time.
Operations like adding or checking on hash map take O(1) time.

Space complexity: O(n2)
We store each row of the grid in the hash map, in the worst-case scenario, row_counter might contains nnn distinct rows of length n.
*/
