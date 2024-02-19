//Easy one. First we need to search for minimal k satisfying condition k^2 > x, then k - 1 is the answer to the question. We can easily come up with the solution. Notice that I set right = x + 1 instead of right = x to deal with special input cases like x = 0 and x = 1.

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
