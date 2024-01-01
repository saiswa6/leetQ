/*
Approach 1: One Pass
Intuition
The problem is known as Dutch National Flag Problem and first was proposed by Edsger W. Dijkstra. The idea is to attribute a color to each number and then arrange them following the order of colors on the Dutch flag.
Let's use a three-pointer to track the rightmost boundary of zeros, the leftmost boundary of twos, and the current element under consideration.
The idea of a solution is to move curr pointer along the array, if nums[curr] = 0 - swap it with nums[p0], if nums[curr] = 2 - swap it with nums[p2].

Algorithm :
- Initialise the rightmost boundary of zeros: p0 = 0. During the algorithm execution nums[idx < p0] = 0.
- Initialise the leftmost boundary of twos: p2 = n - 1. During the algorithm execution nums[idx > p2] = 2.
- Initialise the index of the current element to consider: curr = 0.

For images, refer to https://leetcode.com/problems/sort-colors/editorial/

While curr <= p2 :
 - If nums[curr] = 0: swap currth and p0th elements and move both pointers to the right.
 - If nums[curr] = 2: swap currth and p2th elements. Move pointer p2 to the left.
 - If nums[curr] = 1: move pointer curr to the right.

Complexity Analysis
Time complexity : O(N) since it's one pass along the array of length N.
Space complexity : O(1) since it's a constant space solution.
*/

class Solution {
  /*
  Dutch National Flag problem solution.
  */
  public void sortColors(int[] nums) {
    // For all idx < i : nums[idx < i] = 0
    // j is an index of elements under consideration
    int p0 = 0, curr = 0;

    // For all idx > k : nums[idx > k] = 2
    int p2 = nums.length - 1;

    int tmp;
    while (curr <= p2) {
      if (nums[curr] == 0) {
        // Swap p0-th and curr-th elements
        // i++ and j++
        tmp = nums[p0];
        nums[p0++] = nums[curr];
        nums[curr++] = tmp;
      }
      else if (nums[curr] == 2) {
        // Swap k-th and curr-th elements
        // p2--
        tmp = nums[curr];
        nums[curr] = nums[p2];
        nums[p2--] = tmp;
      }
      else curr++;
    }
  }
}


/*
Please read if you are wondering why we need to move forward curr when nums[curr]==0
We know that when nums[curr]==2, after swapping with p2, we should not increase curr since the number swapped back from p2 can be 0/1, which needs to be further processed.
But how about when nums[curr]==0? Why is the number swapped from p0 not required to be handled?
In fact, if curr > p0, the number swapped back from p0 can only be 1, as curr has passed p0, therefore processed nums[p0]. In this case, leaving curr where it is, or increasing curr, does not really make a difference.
However, it is also possible that curr == p0 as initialization; in this case, both of them need to move forward.
As a unified solution, we increase curr when nums[curr] == 0.

p0 is the right boundary of 0, and p2 is the left boundary of 2 (p0 and p2 are the next place to put 0 and 2)
When curr > p0, nums[p0: curr] could only be 1, since all 2 has already be swapped into places behind p2, and all 0 are before p0. Therefore, after swapping nums[curr] and nums[p0], the new value of nums[curr] must be 1.
So curr is actually the right boundary of 1 (and 0).
*/
