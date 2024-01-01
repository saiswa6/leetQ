class Solution {
    public void sortColors(int[] nums) {
        int length = nums.length;
        int zeroIndex = 0;
        int twoIndex = length - 1;

        for ( int i=0;i<=twoIndex;i++) {
            if (nums[i] == 0) {
                swap(nums, i, zeroIndex);
                zeroIndex++;
            }
            else if (nums[i] == 2) {
                swap(nums, i, twoIndex);
                if (nums[i] != 1) {
                    i--;
                }
                twoIndex--;
            }
        }
        /*for ( int j = zeroIndex; j<= twoIndex; j++) {
            nums[j] = 1;
        }*/
    }
    public void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp; 
    }
}