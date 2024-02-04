//T.C : O(mlogm + nlogn + (m or n whichever is larger))
//S.C : O(m) + O(n)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); //Sort the arrays and apply the 2 pointer technique.
        Arrays.sort(nums2);

        int length1 = nums1.length;
        int length2 = nums2.length;
        int nums1Pointer = 0;
        int nums2Pointer = 0;
        int ans[] = new int [length1];
        int index = 0;

        while(nums1Pointer < length1 && nums2Pointer < length2) {
            if(nums1[nums1Pointer] < nums2[nums2Pointer] ) { // Increment the less than number
                nums1Pointer++;
            } else if(nums1[nums1Pointer] > nums2[nums2Pointer] ) {
                nums2Pointer++; 
            } else {  // If equal, copy the value to final array and increment both the pointers.
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

        return Arrays.copyOf(ans, index); // V Imp to copy the no of elements to final array.
    }
}
