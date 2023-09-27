class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int startPointer = 0;
        int endPointer = numbers.length - 1;
        while(startPointer < endPointer)
        {
            int sum = numbers[startPointer] + numbers[endPointer];
            if( sum == target)
            {
                return new int[] {startPointer + 1, endPointer + 1};
            }
            else if (sum > target)
            {
                endPointer--;
            }
            else
            {
                startPointer++;
            }
        }
        return new int[] {};
    }
}