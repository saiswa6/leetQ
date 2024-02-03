class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) { // When 2nd array size is zero, by default 1st array is sorted, so directly
                      // return.
            return;
        }
        int arr1Pointer = m - 1;
        int arr2Pointer = n - 1;
        int access = m + n - 1; // Pointer for moving backwards
        while (arr1Pointer >= 0 && arr2Pointer >= 0) { // compare both arrays and add elements from backwards not to
                                                       // loose numbers
            if (nums1[arr1Pointer] > nums2[arr2Pointer]) { // If 1st array number is big, copy and move.
                nums1[access] = nums1[arr1Pointer--];
            } else { // If 2nd array number is big, copy and move..
                nums1[access] = nums2[arr2Pointer--];
            }
            access--;
        }

        if (arr2Pointer >= 0) { // If elements of 1st array are only left, no need to do anything, directly
                                // return
            while (arr2Pointer >= 0) {// If elements of 2nd array are left, iterate and copy and move.
                nums1[access--] = nums2[arr2Pointer--];
            }

        }
    }
}
