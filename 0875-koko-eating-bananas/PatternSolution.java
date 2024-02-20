//Very similar to LC 1011 and LC 410 mentioned above. Let's design a feasible function, given an input speed, determine whether Koko can finish all bananas within H hours with hourly eating speed speed. Obviously, the lower bound of the search space is 1, and upper bound is max(piles), because Koko can only choose one pile of bananas to eat every hour.

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;
        for(int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left)/2;

            if(feasible(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean feasible(int[] piles, int speed, int h) {
        int hours = 0;
        for(int pile : piles) {
            hours += (pile/speed) + (pile%speed != 0 ? 1 : 0); // If ceil fn is used, it will take more time
            if(hours > h) {
                return false;
            }
        }
        return hours <= h;
    }
}

/*
total += (p+m-1) / m equal to total += Math.ceil(p/m)
*/
