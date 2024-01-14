class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int writerIndex = 0;
        for(int readIndex = 0; readIndex < length; ) {
            int firstElement = nums[readIndex];
             while(readIndex < length && firstElement == nums[readIndex]) {
                 readIndex++;
            }
            nums[writerIndex++] = firstElement;
        }
        
        return writerIndex;
        
        /*if(nums.length == 0){
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
        return writer + 1;*/
    }
}
