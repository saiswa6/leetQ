class Solution {
    public int removeDuplicates(int[] nums) {

        int writeIndex = 0;
        int length = nums.length;

        for(int readIndex = 0; readIndex < length ; ) {
            int count = 0;
            int firstElement = nums[readIndex];
            while(readIndex < length && firstElement == nums[readIndex] ) {
                if(count < 2) {
                    nums[writeIndex++] = firstElement; 
                    count++;
                }
                readIndex++;
            }
        }

        return writeIndex;

        
        /*if(nums.length == 0)
        {
            return 0;
        }

        int writer = 0;
        int frequency = 0;
        int n = nums.length;
        int lastValue = nums[0]; //lastValue is needed when encounters {0,0,1,1}.At this point, reader and wrietr will become same and in first if block, frequency will be greater than 3, so elements 1,1 will be missed. so, Tracking last value is neeeded. 

        for(int reader=0;reader<n;reader++)
        {
            if(lastValue == nums[reader])// compare each time with lastValue only, not with writer because we are moving writer to next pointer
            {
                frequency++;
                if(frequency < 3) // update only when frequency is < 3.
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
        return writer;*/
    }
}