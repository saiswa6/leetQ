/*
Approach: Backtracking
Intuition
- We are given that n <= 6. Typically, problems that ask you to find all of something with low bounds can be solved with backtracking.
- In backtracking, we generate all solutions one element at a time. This problem is asking us to generate all possible permutations, so we will generate permutations one element at a time.
- To generate a permutation one element at a time, we will use an array curr that represents the current permutation we are building. To start, we add the first element in nums. We have curr = [nums[0]]. We are locking in this first value and we will now find all permutations that start with nums[0].
- To find all permutations that start with nums[0], we start by adding the next element, which is nums[1]. We now have curr = [nums[0], nums[1]]. We are locking in this second element and we will now find all permutations that start with nums[0], nums[1].
- This continues until we use all elements, i.e. curr.length == nums.length. Let's say that we have finished finding all permutations that start with [nums[0], nums[1]]. Now what? We backtrack by removing the nums[1], and we have curr = [nums[0]] again. Now, we add the second element that comes after nums[0], which is nums[2]. We have curr = [nums[0], nums[2]], and now we need to find all permutations that start with [nums[0], nums[2]].
- Once we find all the permutations that start with [nums[0]], we backtrack by removing nums[0] from curr and adding the next element. We have curr = [nums[1]], and now we need to find all permutations that start with nums[1].
- This process is very recursive in nature. Each time we add an element, we solve a new version of the problem (find all permutations that start with curr). The initial version of the problem is to find all permutations that start with [], which represents all possible permutations.
- To summarize: try all numbers in the first position. For each number in the first position, try all other numbers in the second position. For each pair of numbers in the first and second positions, try all other numbers in the third position, and so on.

Trees
The best way to think about the backtracking process is by modeling it as a tree. You can imagine the solution space as a tree, with each node representing a version of curr. Label each node with a number that represents the last number in curr. Moving to a child is like adding the child's label to curr.
----For Trees comparision, check LC Solution

*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new ArrayList<>(), ans, nums);
        return ans;
    }
    
    public void backtrack(List<Integer> curr, List<List<Integer>> ans, int[] nums) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        
        for (int num: nums) {
            if (!curr.contains(num)) {
                curr.add(num);
                backtrack(curr, ans, nums);
                curr.remove(curr.size() - 1);
            }
        }
    }
}


/*
For Time complexity refer LC Solution -- Very Good Explanatory
https://leetcode.com/problems/permutations/editorial/
*/
