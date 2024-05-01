/*
Approach #2 Optimized [Accepted]
Algorithm
Instead of finding the maximum value of countcountcount that can be obtained, as done in the last approach, we can stop the process of checking the positions for planting the flowers as soon as count becomes equal to n. 
Doing this leads to an optimization of the first approach. If countcountcount never becomes equal to nnn, nnn flowers can't be planted at the empty positions.
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
                    if (count >= n) {
                        return true;
                    }
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

/*
Optimization :
for further optimization we could increment the iterator "i" by 2(can be done for while loop not for loop) every time we plant a flower as we know for sure that the subsequent place cannot be planted
*/
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) {
            return flowerbed[0] == 0 || n == 0;
        }

        int planted = 0, i = 0;

        while (i < flowerbed.length - 1) {

            int curr = flowerbed[i], next = flowerbed[i + 1];

            if (curr == 0) {
                if (next == 1) {
                    i += 3;
                } else {
                    planted++;
                    flowerbed[i] = 1;
                    i += 2;
                }
            } else {
                if (next == 1) {
                    i += 3;
                } else {
                    i += 2;
                }
            }
        }

        if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
            planted++;
        }

        return planted >= n;

    }
}
