class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int writer = 0, reader = 0;
        for(; reader< n ; reader++)
        {
            if(nums[reader] != nums[writer])
            {
                writer++;
                nums[writer] = nums[reader];
                
            }
        }
        return writer + 1;


        /*
        int n = nums.length;
        int writer = 0, reader = 0;
        for(; reader< n-1 ; reader++)
        {
            if(nums[reader] != nums[reader+1])
            {
                nums[writer] = nums[reader];
                writer++;
            }
        }
        if(reader < n && nums[reader-1] != nums[reader])
        {
            nums[writer] = nums[reader];
            writer++;
        }
        return writer;
        
        */ 
    }
}