//Python 3 real in-place solution
/*
Start from the back and adjust items to correct locations. If item is zero then duplicate it.

Here are some more verbose thoughts, since you are so inquisitive :)

This algorithm goes backwards and applies correct shifting distance to every element.

1. Why backwards?
  If we would start shifting from left to right, then we would be overwriting elements before we have had the chance to shift them,
  that is why we go backwards instead.
  We make sure we have shifted out an element before we shift another one into its original position.

2. What is the correct shift distance?
  The duplication of a zero pushes all elements to the right of it by one.
  This means also that every element is shifted to the right as many times as there are zeroes to the left of it.
  E.g. in the array [1,0,2,0,3] , 1 will not move, 2 will shift one position and 3 will shift two positions.
  As we go backwards, every time we bypass a zero (and duplicate it), the shift distance decreases for the elements we haven't shifted yet, because there is one less zero in front of them.

3. Why the < n checks?
  Shifts push some of the elements out of the array. We do the < n checks to make sure we write down only elements that are shifted to a valid position inside the array and we ignore the ones falling off the end.

  We can slightly optimize this by stopping the loop when zeroes reaches 0 (either using break or while loop). That being said, I like this solution because it is easy to understand.

 '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' 
--The expression range(n-1, -1, -1) represents a range of values starting from n-1 down to 0 (inclusive), with a step size of -1.
--In Python, the range() function is used to generate a sequence of numbers, and it takes three arguments: start, stop, and step. In this case:

start: n-1 is the starting value.
stop: -1 is the stopping value. The range will include values up to, but not including, -1.
step: -1 is the step size, indicating that the sequence should decrease by 1 in each iteration.
For example, if n is 5, the expression range(4, -1, -1) will produce the sequence of values [4, 3, 2, 1, 0].
*/

class Solution:
    def duplicateZeros(self, arr: List[int]) -> None:
        zeroes = arr.count(0)
        n = len(arr)
        for i in range(n-1, -1, -1):
            if i + zeroes < n:
                arr[i + zeroes] = arr[i]
            if arr[i] == 0: 
                zeroes -= 1
                if i + zeroes < n:
                    arr[i + zeroes] = 0


// Similar Solution

  class Solution {
  public void duplicateZeros(int[] arr) {
    int zeros = 0;

    for (int a : arr)
      if (a == 0)
        ++zeros;

    for (int i = arr.length - 1, j = arr.length + zeros - 1; i < j; --i, --j) {
      if (j < arr.length)
        arr[j] = arr[i];
      if (arr[i] == 0)
        if (--j < arr.length)
          arr[j] = arr[i];
    }
  }
}


/*
First pass to count the number of 0's.
Second pass is to write in the values in appropriate locations, moving from right to left (backwards from the usual), in which the write pointer initially extends outside of the length of the array (as if it was the full sized array without erasing values in the shift).
*/
  /*
  O(n) runtime
  O(1) space
  */
  public void duplicateZeros(int[] A) {
    
    int n = A.length, count = 0;
    
    for (int num : A) if (num == 0) count++;
    int i = n - 1;
    int write = n + count - 1;
    
    while (i >= 0 && write >= 0)  {
      
      if (A[i] != 0) { // Non-zero, just write it in
        if (write < n) A[write] = A[i];
        
      } else { // Zero found, write it in twice if we can
        if (write < n) A[write] = A[i];
        write--;
        if (write < n) A[write] = A[i];
      }
      
      i--;
      write--;
    }
  }
