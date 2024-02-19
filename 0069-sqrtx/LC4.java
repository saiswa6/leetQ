/*
Approach 4: Newton's Method
Intuition
One of the best and most widely used methods to compute sqrt is Newton's Method. Here we'll implement the version without the seed trimming to keep things simple. However, seed trimming is a bit of math and a lot of fun, so here is a link if you'd like to dive in.

Let's keep the mathematical proofs outside of the article and just use the textbook fact that the set
x(k+1)=1/2[x(k)+x/x(k)]
converges to sqrt{x} if x0=x. Then things are straightforward: define that the error should be less than 1 and proceed iteratively.

Complexity Analysis
Time complexity: O(log⁡N) since the set converges quadratically.
Space complexity: O(1)
*/

class Solution {
  public int mySqrt(int x) {
    if (x < 2) return x;

    double x0 = x;
    double x1 = (x0 + x / x0) / 2.0;
    while (Math.abs(x0 - x1) >= 1) {
      x0 = x1;
      x1 = (x0 + x / x0) / 2.0;
    }

    return (int)x1;
  }
}
