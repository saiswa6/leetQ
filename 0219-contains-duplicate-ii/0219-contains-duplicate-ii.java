class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i <= k && i < nums.length ;i++)
        {
            if(set.contains(nums[i]))
            {
                return true;
            }
            set.add(nums[i]);
        }

        int startIndex = 0;
        for(int i = k + 1;  i < nums.length ; i++)
        {
            set.remove(nums[startIndex]);
            if(set.contains(nums[i]))
            {
                return true;
            }
            set.add(nums[i]);
            startIndex++;
        }
        return false;
    }
}