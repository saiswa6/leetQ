/*
Approach 2: Binary Search
Intuition
Let's go back to the interview context. For x≥2 the square root is always smaller than x/2 and larger than 0 : 0<a<=x/2. Since a is an integer, the problem goes down to the iteration over the sorted set of integer numbers. Here the binary search enters the scene.

Algorithm
- If x < 2, return x.
- Set the left boundary to left = 2, and the right boundary to right = x / 2.
- While left <= right:
  - Take num = (left + right) / 2 as a guess. Compute num * num and compare it with x:
     - If num * num > x, move the right boundary ``right = pivot - 1`
     - Else, if num * num < x, move the left boundary left = pivot + 1
     - Otherwise num * num == x, the integer square root is here, let's return it.
- Return right

Complexity Analysis
Time complexity : O(log⁡N)
Space complexity : O(1)
*/
class Solution {
  public int mySqrt(int x) {
    if (x < 2) return x;

    long num;
    int pivot, left = 2, right = x / 2;
    while (left <= right) {
      pivot = left + (right - left) / 2;
      num = (long)pivot * pivot;
      if (num > x) right = pivot - 1;
      else if (num < x) left = pivot + 1;
      else return pivot;
    }

    return right;
  }
}
