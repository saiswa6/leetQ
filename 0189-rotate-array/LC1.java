/*Approach 1: Brute Force
The simplest approach is to rotate all the elements of the array in kkk steps
by rotating the elements by 1 unit in each step.

Complexity Analysis
Time complexity: O(n×k)
All the numbers are shifted by one step(O(n) k times.

Space complexity: O(1). No extra space is used. */

class Solution {
  public void rotate(int[] nums, int k) {
    // speed up the rotation
    k %= nums.length;
    int temp, previous;
    for (int i = 0; i < k; i++) {
      previous = nums[nums.length - 1];
      for (int j = 0; j < nums.length; j++) {
        temp = nums[j];
        nums[j] = previous;
        previous = temp;
      }
    }
  }
}

/*
Approach 2: Using Extra Array
Algorithm
We use an extra array in which we place every element of the array at its correct position i.e. the number at index i in the original array is placed at the index (i+k)% length of array.
Then, we copy the new array to the original one.

Complexity Analysis
Time complexity: O(n).
One pass is used to put the numbers in the new array.
And another pass to copy the new array to the original one.

Space complexity: O(n). Another array of the same size is used.
*/

class Solution {
  public void rotate(int[] nums, int k) {
    int[] a = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      a[(i + k) % nums.length] = nums[i];
    }
    for (int i = 0; i < nums.length; i++) {
      nums[i] = a[i];
    }
  }
}

