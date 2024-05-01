class Solution {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int pointer = 0;
        int k = 0;

        for( ; pointer < length ; pointer++) {
            if(nums[pointer] != 0) {
                int temp = nums[pointer];
                nums[pointer] = nums[k];
                nums[k] = temp;
                k++;
            }
        }

        for( ; k < length ; k++) {
            nums[k] = 0;
        }
    }
}