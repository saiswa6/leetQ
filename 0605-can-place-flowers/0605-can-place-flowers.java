class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) {
            return true;
        }
       //boolean isPlant = false;
        int length = flowerbed.length;
        for(int i = 0; i < length; i++) {
            if(((i == 0) || (i - 1 < length && flowerbed[i - 1] == 0)) && flowerbed[i] == 0 && ( (i + 1 == length) || (i + 1 < length && flowerbed[i + 1] == 0))) {
                n--;
                if(n == 0) {
                    return true;
                }
                flowerbed[i] = 1;
            }

            //isPlant = (flowerbed[i] == 1) ;
        }

        return n == 0;
    }
}