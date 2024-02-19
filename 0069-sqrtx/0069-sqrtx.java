class Solution {
    public int mySqrt(int x) {
        long left = 0, right = (long)x + 1;

        while(left < right) {
            long mid = left + ((right - left)/2);

            if((long)mid*mid > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int)left - 1;
    }
}