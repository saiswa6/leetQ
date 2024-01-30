class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0) {
            return;
        }
        int arr1Pointer = m - 1;
        int arr2Pointer = n - 1;
        int access = m + n - 1;
        while(arr1Pointer >= 0 && arr2Pointer >= 0) {
            if(nums1[arr1Pointer] > nums2[arr2Pointer] ) {
                nums1[access] = nums1[arr1Pointer--];
            } else {
                nums1[access] = nums2[arr2Pointer--];
            }
            access--;
        }

        if(arr2Pointer >= 0) {
            while(arr2Pointer >= 0) {
                nums1[access--] = nums2[arr2Pointer--];
            }
            
        }
    }
}