class Solution {
    List<List<Integer>> output = new ArrayList<>();
    int n;

    public void backtrack(int start, ArrayList<Integer> curr, int [] nums)
    {
        output.add(new ArrayList<>(curr));

        for(int i = start; i < n; i++)
        {
            curr.add(nums[i]);
            backtrack(i + 1, curr, nums);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        backtrack(0, new ArrayList<Integer>(), nums);
        return output;
    }
}