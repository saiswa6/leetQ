/*
Approach 2: Sort
You can recommend this method when the input is sorted, or when the output needs to be sorted. Here, we sort both arrays (assuming they are not sorted) and use two pointers to find common numbers in a single scan.

Algorithm
1. Sort nums1 and nums2.
2. Initialize i, j and k with zero.
3. Move indices i along nums1, and j through nums2:
   - Increment i if nums1[i] is smaller.
   - Increment j if nums2[j] is smaller.
   - If numbers are the same, copy the number into nums1[k], and increment i, j and k.
4. Return first k elements of nums1.

Complexity Analysis
Time Complexity: O(nlog⁡n+mlog⁡m), where nnn and mmm are the lengths of the arrays. We sort two arrays independently, and then do a linear scan.

Space Complexity: from O(log⁡n+log⁡m) to O(n+m), depending on the implementation of the sorting algorithm. For the complexity analysis purposes, we ignore the memory required by inputs and outputs.

  */

public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0, k = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            ++i;
        } else if (nums1[i] > nums2[j]) {
            ++j;
        } else {
            nums1[k++] = nums1[i++];
            ++j;
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}
