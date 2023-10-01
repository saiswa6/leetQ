class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); 
        List<List<Integer>> solution = new ArrayList<List<Integer>>();
        HashSet<List<Integer>> h = new HashSet<List<Integer>>();
        int length = nums.length;
        for(int i = 0; i< length-2;i++)
        {
            /*if(i > 0 && nums[i] == nums[i-1])
            {
                continue;
            }*/
            int value = -1 * nums[i];
            int start = i+1;
            int end = length-1;
            while(start < end)
            {
                /*if(start+1 < length && nums[start] == nums[start+1])
                {
                    start++;
                }
                if(end-1 > -1 && nums[end] == nums[end-1])
                {
                    end--;
                }*/
                int sum = nums[start] + nums[end];
                if(sum == value)
                {
                    List<Integer> sl = new ArrayList<Integer>();
                    sl.add(nums[i]);
                    sl.add(nums[start]);
                    sl.add(nums[end]);
                    if(!h.contains(sl))
                    {
                        solution.add(sl);
                    }
                    h.add(sl);
                    start++;
                    end--;
                }
                else if (sum > value)
                {
                    end--;
                }
                else
                {
                    start++;
                }
            }
        }
        return solution;
    }
}
