class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int num = 0;
        int low = 0, high = len - 1;
        while(low <= high)
        {
            if(low == high)
            {
                if(nums[low] < target)
                {
                    return low + 1;
                }
                else
                {
                    return low;
                }
               
            }
            int mid = (low+high)/2;

            if(nums[mid] == target)
            {
                return mid;
            }
            else if(nums[mid] < target)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
        if(low > high)
        {
            return low;
        }

        return num;
    }
}