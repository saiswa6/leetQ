// Now that we've solved three advanced problems above, this one should be pretty easy to do. The monotonicity of this problem is very clear: if we can make m bouquets after waiting for d days, then we can definitely finish that as well if we wait for more than d days.

/*
Intuition
If m * k > n, it impossible, so return -1.
Otherwise, it's possible, we can binary search the result.
left = 1 is the smallest days,
right = 1e9 is surely big enough to get m bouquests.
So we are going to binary search in range [left, right].

Complexity
Time O(Nlog(maxA))
Space O(1)
*/

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int length = bloomDay.length; 
        if((long)m*k > length) {
            return -1;
        }

        int left = 1, right = 1;
        for(int i=0; i < length; i++) {
            right = Math.max(right, bloomDay[i]);
            left = Math.min(left, bloomDay[i]);
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
