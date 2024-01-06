class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int length = nums.length;
        int oddPointer = 0;
        int evenPointer = length - 1;
        while(oddPointer < evenPointer) { 
            while (oddPointer < evenPointer && nums[oddPointer] % 2 == 0) {  //OddPointer moves smoothly for even elements and stops at odd element.
                oddPointer++;
            }

            while (oddPointer < evenPointer && nums[evenPointer] % 2 != 0) {  //EvenPointer moves smoothly for odd elements and stops at even element.
                evenPointer--;
            }

            int temp = nums[oddPointer];                                    // Once found, swap and move forward.
            nums[oddPointer] = nums[evenPointer];
            nums[evenPointer] = temp;
            oddPointer++;
            evenPointer--;
        }
        return nums;
    }
}
