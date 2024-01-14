class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int writerIndex = 0; // maintains unique values by writing
        for(int readIndex = 0; readIndex < length; ) { // reads all the values of the array.
            int firstElement = nums[readIndex]; // Take first unique element
             while(readIndex < length && firstElement == nums[readIndex]) { // Iterate and increment till the firstElement completes.
                 readIndex++;
            }
            nums[writerIndex++] = firstElement; // write the unique element
        }
        
        return writerIndex;
    }
}
