class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
           return 0;
        }
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
    }
}