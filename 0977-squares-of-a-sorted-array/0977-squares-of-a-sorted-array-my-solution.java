class Solution {
    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int start = 0;
        int end = length - 1;
        int result [] = new int [length]; 
        int pointer = length - 1;

        while(start <= end) {
            if (start == end) { // take care of edge case, when single element left
                result[pointer] = nums[start] * nums[start];
                start++;
            } else if (absolute(nums[start]) < absolute(nums[end])) { //when less than
                result[pointer] = nums[end] * nums[end];
                end--;
                pointer--;
            } else if (absolute(nums[start]) > absolute(nums[end])) { // when greater than
                result[pointer] = nums[start] * nums[start];
                start++;
                pointer--;
            } else {                                                 // when teo equal elements come on both left amd right
                result[pointer] = nums[start] * nums[start];
                pointer--;
                result[pointer] = nums[start] * nums[start];
                start++;
                end--;
                pointer--;
            }
        }
        return result;
    }

    public int absolute(int num) {         // returns absolute of a number
        if (num < 0) {
            return num * -1;
        }
        return num;
    }
}
