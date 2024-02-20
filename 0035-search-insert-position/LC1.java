/*
Approach 1: Binary Search
Intuition
Based on the description of the problem, we can see that it could be a good match with the binary search algorithm.

Integer Overflow
Let us now stress the fact that pivot = (left + right) // 2 works fine for Python3, which has arbitrary precision integers, but it could cause some issues in Java and C++.

If left + right is greater than the maximum int value 2^{31} - 1, it overflows to a negative value. In Java, it would trigger an exception of ArrayIndexOutOfBoundsException

Here is a simple way to fix it: pivot = left + (right - left) / 2;
 here is a bit more complicated but probably faster way using the bit shift operator.
 pivot = (right + left) >> 1;


Complexity Analysis
Time complexity : O(log⁡N)
Let us compute the time complexity with the help of master theorem
T(N)=aT(N/b)+Θ(N pow d)

The equation represents dividing the problem up into a subproblems of size N/b in Θ(N pow d) time.

Here at each step there is only one subproblem i.e. a = 1, its size is half of the initial problem i.e. b = 2, and all this happens in a constant time i.e. d = 0. 

O(logN) time complexity.

Refer https://leetcode.com/problems/search-insert-position/editorial/

Space complexity: O(1)
since it's a constant space solution.
*/

class Solution {
  public int searchInsert(int[] nums, int target) {
    int pivot, left = 0, right = nums.length - 1;
    while (left <= right) {
      pivot = left + (right - left) / 2;
      if (nums[pivot] == target) return pivot;
      if (target < nums[pivot]) right = pivot - 1;
      else left = pivot + 1;
    }
    return left;
  }
}

// Took me a minute to figure out why return left outside the loop. In the case where we need to insert (target not found), left and right must have narrowed down to the same element - call it Elem. Following regular binary search, if we go left, meaning toInsert is smaller than Elem, the algorithm changes right and leaves left untouched. This is desired, because left, untouched and used as insertion index, inserts the element to the left of Elem. On the other hand, if we go right, left is changed to left + 1, which indeed inserts target to the right of Elem.
