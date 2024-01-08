class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length; 
        k = k % length; 
        reverse(nums, 0, length - 1); // reverse entire array
        reverse(nums, 0, k-1); // reverse first k elements
        reverse(nums, k, length-1); // reverse from k to n-1.
    }

    public void reverse(int nums[], int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
