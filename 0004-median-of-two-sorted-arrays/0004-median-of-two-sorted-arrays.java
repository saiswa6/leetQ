class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;
        int result [] = new int[totalLength];
        double ans = 0.0;

        int i = 0, j = 0, k = 0;

        while(i < length1 || j < length2) {
            if(i < length1 && j < length2 && nums1[i] < nums2[j]) {
                result[k++] = nums1[i++];
            } else if(i < length1 && j < length2 && nums1[i] > nums2[j]) {
                result[k++] = nums2[j++];
            } else if(i < length1) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }

        if(totalLength % 2 == 0) {
            ans = (double)(result[(totalLength-1)/2] + result[(totalLength)/2] )/2;
        } else {
            ans = (double)(result[(totalLength)/2]);
        }

        return ans;
    }
}