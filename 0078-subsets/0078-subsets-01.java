Solution Pattern


There are generally three strategies to do it:
1. Recursion
2. Backtracking
3. Lexicographic generation based on the mapping between binary bitmasks and the corresponding permutations / combinations / subsets.

==> The third method could be a good candidate for the interview because it simplifies the problem to the generation of binary numbers, therefore it is easy to implement and verify that no solution is missing.
    Besides, as a bonus, it generates lexicographically sorted output for the sorted inputs.


APPROACH 1: Cascading
=====================
Intuition
Let's start from empty subset in output list.
At each step, one takes new integer into consideration and generates new subsets from the existing ones.

Complexity Analysis
Time complexity: O(N×2^N) to generate all subsets and then copy them into output list.

Space complexity: O(N×2^N). This is exactly the number of solutions for subsets multiplied by the number N of elements to keep for each subset.
 - For a given number, it could be present or absent (i.e. binary choice) in a subset solution. As as result, for N numbers, we would have in total 2^N choices (solutions).  


class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> output = new ArrayList();
    output.add(new ArrayList<Integer>());

    for (int num : nums) {
      List<List<Integer>> newSubsets = new ArrayList();
      for (List<Integer> curr : output) {
        newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
      }
      for (List<Integer> curr : newSubsets) {
        output.add(curr);
      }
    }
    return output;
  }
}
