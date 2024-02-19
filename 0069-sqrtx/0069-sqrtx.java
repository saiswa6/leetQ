class Solution {
    public int mySqrt(int x) {
        long left = 0, right = (long)x + 1; // when x is 2147483647, then addition of 1 to INT_MAX oveflows to -2147483648. So, convert to long.

        while(left < right) {
            long mid = left + ((right - left)/2);

            if((long)mid*mid > x) { // overflows int * int, use type cast to long.
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int)left - 1; // back type cast to retun answer in int.
    }
}