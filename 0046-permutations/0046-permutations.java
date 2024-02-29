class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteCalc(new ArrayList<Integer>(), nums, ans);
        return ans;
    }

    public void permuteCalc(List<Integer> processed, int nums[], List<List<Integer>> ans) {
        if(processed.size() == nums.length) {
            ans.add(new ArrayList<>(processed));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(processed.contains(nums[i])) {
                continue;
            }
            processed.add(nums[i]);
            permuteCalc(processed, nums, ans);
            processed.remove(processed.size() - 1);
        }
    }
}