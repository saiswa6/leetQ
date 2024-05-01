class Solution {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int pointer = 0;
        int k = 0; // to track zeroes

        for (; pointer < length; pointer++) {
            if (nums[pointer] != 0) {
                // int temp = nums[pointer]; // No need to do swap
                // nums[pointer] = nums[k];
                nums[k] = nums[pointer];
                k++;
            }
        }

        for (; k < length; k++) {
            nums[k] = 0;
        }
    }
}
