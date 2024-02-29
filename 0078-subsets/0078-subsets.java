class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> outputList = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(new ArrayList<Integer>(), nums, 0, outputList);
        return outputList;
    }

    void backtrack(List<Integer> processedList, int nums[] , int index, List<List<Integer>> outputList) {
        outputList.add(new ArrayList<>(processedList));
        
        for(int k = index; k < nums.length; k++) {
            // add k to the current combination
            processedList.add(nums[k]);
            // use next integers to complete the combination
            backtrack(processedList, nums, k + 1, outputList);
            // backtrack
            processedList.remove(processedList.size() - 1);
        }
    }
}