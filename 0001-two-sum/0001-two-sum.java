class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i< nums.length;i++)
        {
            int findVal = target - nums[i];
            if(map.containsKey(findVal))
            {
                return new int[]{i, map.get(findVal)};
            }
            else
            {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}