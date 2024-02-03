/*
Approach 2: Three Pointers (Start From the Beginning)
Intuition
Because each array is already sorted, we can achieve an O(n+m)\mathcal{O}(n + m)O(n+m) time complexity with the help of the two-pointer technique.

Algorithm
The simplest implementation would be to make a copy of the values in nums1, called nums1Copy, and then use two read pointers and one write pointer to read values from nums1Copy and nums2 and write them into nums1.

- Initialize nums1Copy to a new array containing the first m values of nums1.
- Initialize the read pointer p1 to the beginning of nums1Copy.
- Initialize the read pointer p2 to the beginning of nums2.
- Initialize the write pointer p to the beginning of nums1.
- While p is still within nums1:
   - If nums1Copy[p1] exists and is less than or equal to nums2[p2]:
      Write nums1Copy[p1] into nums1[p], and increment p1 by 1.
   - Else
      Write nums2[p2] into nums1[p], and increment p2 by 1.
   Increment p by 1.


Complexity Analysis
Time complexity: O(n+m).
We are performing n+2⋅m reads and n+2⋅m writes. Because constants are ignored in Big O notation, this gives us a time complexity of O(n+m).

Space complexity: O(m).
We are allocating an additional array of length m.


*/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of the first m elements of nums1.
        int[] nums1Copy = new int[m];
        for (int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }

        // Read pointers for nums1Copy and nums2 respectively.
        int p1 = 0;
        int p2 = 0;
                
        // Compare elements from nums1Copy and nums2 and write the smallest to nums1.
        for (int p = 0; p < m + n; p++) {
            // We also need to ensure that p1 and p2 aren't over the boundaries
            // of their respective arrays.
            if (p2 >= n || (p1 < m && nums1Copy[p1] < nums2[p2])) {
                nums1[p] = nums1Copy[p1++];
            } else {
                nums1[p] = nums2[p2++];
            }
        }
    }
}
