class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int length = nums.length;
        int oddPointer = 0;
        int evenPointer = length - 1;
        while(oddPointer < evenPointer) {
            while (oddPointer < evenPointer && nums[oddPointer] % 2 == 0) {
                oddPointer++;
            }

            while (oddPointer < evenPointer && nums[evenPointer] % 2 != 0) {
                evenPointer--;
            }

            int temp = nums[oddPointer];
            nums[oddPointer] = nums[evenPointer];
            nums[evenPointer] = temp;
            oddPointer++;
            evenPointer--;
        }
        return nums;
    }
}