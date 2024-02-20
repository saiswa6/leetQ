class Solution {
    public int searchInsert(int[] nums, int target) {
        // Pattern Solution
        // Very classic application of binary search. We are looking for the minimal k value satisfying nums[k] >= target, and we can just copy-paste our template. Notice that our solution is correct regardless of whether the input array nums has duplicates. Also notice that the input target might be larger than all elements in nums and therefore needs to placed at the end of the array. That's why we should initialize right = len(nums) instead of right = len(nums) - 1.
        int left = 0 , right = nums.length;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;


        // Own Soltion

        /*int len = nums.length;
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

        return num;*/
    }
}
