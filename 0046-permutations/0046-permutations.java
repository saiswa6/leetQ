class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteCalc(new ArrayList<Integer>(), new HashSet<>(),nums, ans);
        return ans;
    }

    public void permuteCalc(List<Integer> processed, Set<Integer> hs,int nums[], List<List<Integer>> ans) {
        if(processed.size() == nums.length) {
            ans.add(new ArrayList<>(processed));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(hs.contains(nums[i])) {
                continue;
            }
            processed.add(nums[i]);
            hs.add(nums[i]);
            permuteCalc(processed, hs, nums, ans);
            int lastElement = processed.get(processed.size() - 1);
            hs.remove(lastElement);
            processed.remove(processed.size() - 1);
        }
    }
}