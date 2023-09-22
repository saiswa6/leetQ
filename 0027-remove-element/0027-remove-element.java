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