// Backtrack

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), nums, 0, output);
        return output;
    }

    void backtrack(List<Integer> processed, int nums[] , int index, List<List<Integer>> output) {
        if (index == nums.length) {
            output.add(new ArrayList<Integer>(processed));
            return;
        }
        int number = nums[index];

        backtrack(processed, nums, index + 1, output);
        processed.add(number);
        backtrack(processed, nums, index + 1, output);
        processed.remove(processed.size() - 1); // backtrack
        return;
    }
}
