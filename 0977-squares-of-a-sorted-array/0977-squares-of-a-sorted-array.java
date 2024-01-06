class Solution {
    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int start = 0;
        int end = length - 1;
        int result [] = new int [length]; 
        int pointer = length - 1;

        while(start <= end) {
            if (start == end) {
                result[pointer] = nums[start] * nums[start];
                start++;
            } else if (absolute(nums[start]) < absolute(nums[end])) {
                result[pointer] = nums[end] * nums[end];
                end--;
                pointer--;
            } else if (absolute(nums[start]) > absolute(nums[end])) {
                result[pointer] = nums[start] * nums[start];
                start++;
                pointer--;
            } else {
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

    public int absolute(int num) {
        if (num < 0) {
            return num * -1;
        }
        return num;
    }
}