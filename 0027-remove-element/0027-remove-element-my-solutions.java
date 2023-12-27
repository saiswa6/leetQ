/*valueMatchPointer shall stop when value matches with given value in method.
valueDisMatchPointer shall stop when value does not matches with given value in method.
    Then do swap.
    Scenario might fail at situation when valueMatchPointer and valueDisMatchPointer are equal because we are not sure whether value at valueMatchPointer equals given value or not. 
Ex : 3, 2, 2, 3            val 3
     0  1  2  3
     VM        VD
      0         3
      0         2
      1         1 ===> here, we have to make decision, so kept if block.

      */

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


//2ND SOLUTION
===============

class Solution {
    public int removeElement(int[] nums, int val) {
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
            }
}
