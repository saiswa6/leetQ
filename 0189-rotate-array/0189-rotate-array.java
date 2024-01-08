class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length; 
        if (length == 1) {
            return ;
        }
        k = k % length ; 
        rotate(nums, 0,length-k-1);
        rotate(nums, length-k, length-1);
        rotate(nums, 0, length - 1);
    }

    public void rotate(int nums[], int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}