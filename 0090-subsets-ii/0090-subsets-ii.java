class Solution {
        HashSet<List<Integer>> output = new  HashSet<>();
        int n;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        n = nums.length;
        Arrays.sort(nums);
        backtrack(0, new ArrayList<Integer>(), nums);
        return new ArrayList<>(output);
    }

    public void backtrack(int start, ArrayList<Integer> curr, int nums[])
    {
        output.add(new ArrayList<Integer>(curr));

        for(int i=start;i<n;i++)
        {
            /*if(i>0 && nums[i-1] == nums[i] && i+1 < n)
            {
                i++;
                //continue;
            }*/
            curr.add(nums[i]);
            backtrack(i+1, curr, nums);
            curr.remove(curr.size() - 1);
        }
    }
}