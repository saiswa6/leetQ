Algorithm
We define a backtrack function named backtrack(first, curr) which takes the index of first element to add and a current combination, nums as arguments.

1. Current combination is done, we add the combination to the final output.
2. We iterate over the indexes i from first to the length of the entire sequence n.
   - Add integer nums[i] into the current combination curr.
   - Proceed to add more integers into the combination : backtrack(i + 1, curr).
   - Backtrack by removing nums[i] from curr.

Complexity Analysis:
Time complexity: O(N×2^N) to generate all subsets and then copy them into output list.
Space complexity: O(N). We are using O(N) space to maintain curr, and are modifying curr in-place with backtracking. Note that for space complexity analysis, we do not count space that is only used for the purpose of returning output, so the output array is ignored.


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
            curr.remove(curr.size() - 1);   // Don't do by curr.remove(nums[i]) because remove method accepts indices only.
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        backtrack(0, new ArrayList<Integer>(), nums);
        return output;
    }
}
