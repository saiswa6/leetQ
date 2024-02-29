class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteCalc(new ArrayList<Integer>(), nums, 0, ans);
        return ans;
    }

    public void permuteCalc(List<Integer> processed, int nums[], int index, List<List<Integer>> ans) {
        if(index == nums.length) {
            List<List<Integer>> list = new ArrayList<>();
            ans.add(processed);
            //return list;
            return;
        }

        int number = nums[index];

        for(int i = 0; i <= processed.size(); i++) {
            List<Integer> anslist = new ArrayList<>();
            for(int ind = 0; ind < i; ind++) {
                anslist.add(processed.get(ind));
            }
            anslist.add(number);
            for(int ind = i; ind < processed.size(); ind++) {
                anslist.add(processed.get(ind));
            }
            permuteCalc(anslist, nums, index+1, ans);

        }
        


    }
}
