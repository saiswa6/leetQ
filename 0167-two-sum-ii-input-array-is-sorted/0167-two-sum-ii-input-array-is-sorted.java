class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int startPointer = 0;
        int endPointer = numbers.length - 1;
        while(startPointer < endPointer)
        {
            if( numbers[startPointer] + numbers[endPointer] == target)
            {
                return new int[] {startPointer + 1, endPointer + 1};
            }
            else if (numbers[startPointer] + numbers[endPointer] > target)
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