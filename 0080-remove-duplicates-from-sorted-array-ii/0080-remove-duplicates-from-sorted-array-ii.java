class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 1, count = 1, length = nums.length;

        while(i < length) {

            if(nums[i] == nums[i-1]) {
                count++;

                if(count > 2) {
                    removeElement(nums, i);
                    i--;
                    length--;
                } 
            } else {
                count = 1;
            }
            i++;
        }

        return length;
    }

    public int[] removeElement(int nums[], int index) {
        for(int i = index+1 ;i < nums.length ; i++) {
            nums[i-1] = nums[i];
        }

        return nums;
    }

}