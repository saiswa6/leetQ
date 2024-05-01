/*
Approach #1 Single Scan [Accepted]
-  We can find out the extra maximum number of flowers, countcountcount, that can be planted for the given flowerbedflowerbedflowerbed arrangement. 
   To do so, we can traverse over all the elements of the flowerbedflowerbedflowerbed and find out those elements which are 0(implying an empty position). 
   For every such element, we check if its both adjacent positions are also empty. If so, we can plant a flower at the current position without violating the no-adjacent-flowers-rule. 
   For the first and last elements, we need not check the previous and the next adjacent positions respectively.

- If the countcountcount obtained is greater than or equal to nnn, the required number of flowers to be planted, we can plant nnn flowers in the empty spaces, otherwise not.
*/
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if the current plot is empty.
            if (flowerbed[i] == 0) {
                // Check if the left and right plots are empty.
                boolean emptyLeftPlot = (i == 0) || (flowerbed[i - 1] == 0);
                boolean emptyRightPlot = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
                
                // If both plots are empty, we can plant a flower here.
                if (emptyLeftPlot && emptyRightPlot) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        return count >= n;
    }
}

/*
Complexity Analysis
Time complexity: O(n)O(n)O(n). A single scan of the flowerbedflowerbedflowerbed array of size nnn is done.
Space complexity: O(1)O(1)O(1). Constant extra space is used.
*/
