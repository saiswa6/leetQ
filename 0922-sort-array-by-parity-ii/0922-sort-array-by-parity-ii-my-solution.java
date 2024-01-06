class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int evenPointer = 0;
        int oddPointer = 1;
        int length = nums.length;
        while (evenPointer < length || oddPointer < length) {   /// careful with this condition
            if (evenPointer < length && nums[evenPointer] % 2 == 0) {  // evenPointer will continue till even nums at even index exists and stops at odd place.
                evenPointer+=2;
            } else if (oddPointer < length && nums[oddPointer] % 2 != 0) { // oddPointer will continue till odd nums at even index exists and stops at even place.
                oddPointer+=2;
            } else {                                                  //If both above if satisfied, then swap will happen.
                int temp = nums[evenPointer];
                nums[evenPointer] = nums[oddPointer];
                nums[oddPointer] = temp;
                evenPointer+=2;
                oddPointer+=2;
            }
        }
        return nums;
    }
}
