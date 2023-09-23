class Solution {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            if(map.containsKey(nums[i]))
            {
                return true;
                /*int occurence = map.get(nums[i]) + 1; 
                map.put(nums[i], occurence);
                if(occurence>1)
                {
                    return true;
                }*/
            }
            else
            {
                map.put(nums[i], 1);
            }
            
        }
        return false;
    }
}