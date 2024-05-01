class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true; // Important, directly retun true if n is 0.
        }

        int length = flowerbed.length;
        for (int i = 0; i < length; i++) {
            if (((i == 0) || (i - 1 < length && flowerbed[i - 1] == 0)) && flowerbed[i] == 0
                    && ((i + 1 == length) || (i + 1 < length && flowerbed[i + 1] == 0))) {
                // if n == 0 or i - 1 < length, check if it is 0.
                // if currentIndex is 0.
                // If nextIndex i + 1 < length or last Index (i + 1 == length).
                n--;
                if (n == 0) { // Important. If done, return true immediately.
                    return true;
                }
                flowerbed[i] = 1; // Update the value for index where plant is planted for next iteration.
            }
        }

        return n == 0;
    }
}