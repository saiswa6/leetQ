/*
Approach 1: Pocket Calculator Algorithm
Before going to the serious stuff, let's first have some fun and implement the algorithm used by the pocket calculators.

Usually, a pocket calculator computes well exponential functions and natural logarithms by having logarithm tables hardcoded or by other means. Hence the idea is to reduce the square root computation to these two algorithms as well

root of x=e pow 1/2log⁡x
That's some sort of cheat because of non-elementary function usage but it's how that actually works in real life.

Complexity Analysis
Time complexity: O(1)
Space complexity: O(1)
*/
class Solution {
  public int mySqrt(int x) {
    if (x < 2) return x;

    int left = (int)Math.pow(Math.E, 0.5 * Math.log(x));
    int right = left + 1;
    return (long)right * right > x ? left : right;
  }
}
