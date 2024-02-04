class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int length1 = nums1.length;
        int length2 = nums2.length;
        int nums1Pointer = 0;
        int nums2Pointer = 0;
        int ans[] = new int [length1];
        int index = 0;

        while(nums1Pointer < length1 && nums2Pointer < length2) {
            if(nums1[nums1Pointer] < nums2[nums2Pointer] ) {
                nums1Pointer++;
            } else if(nums1[nums1Pointer] > nums2[nums2Pointer] ) {
                nums2Pointer++;
            } else {
                ans[index++] = nums1[nums1Pointer];
                int sameElem1 = nums1[nums1Pointer++];
                int sameElem2 = nums2[nums2Pointer++];
                while(nums1Pointer < length1 &&sameElem1 == nums1[nums1Pointer]) {
                    nums1Pointer++;
                }
                while(nums2Pointer < length2 && sameElem2 == nums2[nums2Pointer]) {
                    nums2Pointer++;
                }
            }
        }

        return Arrays.copyOf(ans, index);
    }
}