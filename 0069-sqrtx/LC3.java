/*
Approach 3: Recursion + Bit Shifts
Intuition
Let's use recursion. Bases cases are sqrt{x} = x  for x<2. Now the idea is to decrease x recursively at each step to go down to the base cases.

How to go down?
For example, let's notice that sqrt{x} = 2 times * sqrt{{x}/{4}} hence square root could be computed recursively as
mySqrt(x)=2×mySqrt(x/4)

One could already stop here, but let's use left and right shifts, which are quite fast manipulations with bits
x<<y that means x×2 pow y 
x>>y that means x/2 pow y

That means one could rewrite the recursion above as
mySqrt(x)=mySqrt(x>>2)<<1

Complexity Analysis
Time complexity: O(log⁡N)
Space complexity: O(log⁡N) to keep the recursion stack.
*/

class Solution {
  public int mySqrt(int x) {
    if (x < 2) return x;

    int left = mySqrt(x >> 2) << 1;
    int right = left + 1;
    return (long)right * right > x ? left : right;
  }
}
