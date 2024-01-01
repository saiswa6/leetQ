class Solution {
    public void sortColors(int[] nums) {
        int length = nums.length;
        int zeroIndex = 0; // track for zero's Index
        int twoIndex = length - 1;  // track for two's Index

        for ( int i=0;i<=twoIndex;i++) {
            if (nums[i] == 0) {  // If num is 0, then swap i and zeroIndex -> Increase both i and zeroIndex.
                swap(nums, i, zeroIndex);
                zeroIndex++;
            }
            else if (nums[i] == 2) {  // If num is 0, then swap i and zeroIndex -> Only Increase twoIndex because we are not sure about the element present at twoIndex. will check in next Interation.
                swap(nums, i, twoIndex);
                if (nums[i] != 1) {
                    i--;
                }
                twoIndex--;
            }
        }
        /*for ( int j = zeroIndex; j<= twoIndex; j++) { //This extra step is not needed because swapping will keep 1 in right places.
            nums[j] = 1;
        }*/
    }
    public void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp; 
    }
}
