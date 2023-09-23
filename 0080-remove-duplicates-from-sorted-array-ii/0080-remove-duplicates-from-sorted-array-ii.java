class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)
        {
            return 0;
        }

        int writer = 0;
        int frequency = 0;
        int n = nums.length;
        int lastValue = nums[0];

        for(int reader=0;reader<n;reader++)
        {
            if(lastValue == nums[reader])
            {
                frequency++;
                if(frequency < 3)
                {
                    nums[writer] = nums[reader];
                    lastValue = nums[writer];
                    writer++;
                }
            }
            else{
                frequency = 1;
                nums[writer] = nums[reader];
                lastValue = nums[writer];
                writer++;
            }
        }
        return writer;
    }
}