class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int length = bloomDay.length; 
        if((long)m*k > length) {
            return -1;
        }

        int left = 1, right = 1;
        for(int i=0; i < length; i++) {
            right = Math.max(right, bloomDay[i]);
        }

        while(left < right) {
            int mid = left + (right - left) / 2;
            if(feasible(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean feasible(int[] bloomDay, int m, int k, int days) {
        int flowers = 0;
        int bouquets = 0;

        for(int j=0;j<bloomDay.length;j++) {
            if(bloomDay[j] > days) {
                flowers = 0;
            } else {
                bouquets += (flowers + 1)/k;
                flowers = (flowers + 1) % k;
            }
        }
        return bouquets >= m;
    }
}