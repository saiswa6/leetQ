class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(new ArrayList<>(), nums, new boolean[nums.length], answer);
        return answer;
    }

    void backtrack(List<Integer> processedList, int [] nums, boolean [] used, List<List<Integer>> answer) {
        if(processedList.size() == nums.length) {
            answer.add(new ArrayList<>(processedList));
            return;
        }

        for(int i=0; i < nums.length; i++) {
            if(used[i] || i>0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }
            used[i] = true;
            processedList.add(nums[i]);
            backtrack(processedList, nums, used, answer);
            used[i] = false;
            processedList.remove(processedList.size() - 1);
        }
    }
}