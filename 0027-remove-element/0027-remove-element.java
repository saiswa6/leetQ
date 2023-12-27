class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 1) {
            return nums[0] == val ? 0 : 1 ;
        }
        int valueMatchPointer = 0;
        int valueDisMatchPointer = nums.length - 1;
        int count = 0;

        while (valueMatchPointer <= valueDisMatchPointer) {
            if (valueMatchPointer == valueDisMatchPointer) {
                return nums[valueMatchPointer] != val ? count + 1 : count;
            }
            while (valueMatchPointer < valueDisMatchPointer && nums[valueMatchPointer] != val) {
                count++;                                        
                valueMatchPointer++;
            }
            while (valueMatchPointer < valueDisMatchPointer && nums[valueDisMatchPointer] == val) {
                valueDisMatchPointer--;
            }

            int temp = nums[valueMatchPointer];
            nums[valueMatchPointer] = nums[valueDisMatchPointer];
            nums[valueDisMatchPointer] = temp;
            //count++;
        }

        return nums.length - count;
    }
}


/*
        if(nums.length == 0)
        {
            return 0;
        }
        if(nums.length == 1 && nums[0] != val)
        {
            return 1;
        }
        int firstMatchIndex = 0;
        int secondMatchIndex = nums.length - 1;
        int notEqualAns = 0;
        while(firstMatchIndex < secondMatchIndex)
        {
            while( firstMatchIndex < nums.length && nums[firstMatchIndex] != val)
            {
                firstMatchIndex++;
                notEqualAns++;
            }

            while(secondMatchIndex >= 0 && nums[secondMatchIndex] == val )
            {
                secondMatchIndex--;
            }

            if(firstMatchIndex < secondMatchIndex)
            {
                int swap = nums[firstMatchIndex];
                nums[firstMatchIndex] = nums[secondMatchIndex];
                nums[secondMatchIndex] = swap;
            }
        }
           if(firstMatchIndex < secondMatchIndex)
            {
                int swap = nums[firstMatchIndex];
                nums[firstMatchIndex] = nums[secondMatchIndex];
                nums[secondMatchIndex] = swap;
            }
        return notEqualAns;
 */