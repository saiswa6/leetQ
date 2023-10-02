Solution Pattern


There are generally three strategies to do it:
1. Recursion
2. Backtracking
3. Lexicographic generation based on the mapping between binary bitmasks and the corresponding permutations / combinations / subsets.

==> The third method could be a good candidate for the interview because it simplifies the problem to the generation of binary numbers, therefore it is easy to implement and verify that no solution is missing.
    Besides, as a bonus, it generates lexicographically sorted output for the sorted inputs.


APPROACH 1: Cascading
=====================
Intuition  -- seems DP Approach
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


SIMPLER SOLUTION
================
Perhaps it could be made more simple by adding each new decision to the previously made choices.
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums == null || nums.length == 0){
            return result;
        }
        int s = 0;
        for(int n:nums){
            s = result.size();
            for(int i = 0;i<s;i++){
                List<Integer> set = new ArrayList<>(result.get(i));
                set.add(n);
                result.add(set);
            }
        }
        return result;
    }
}

Explanation :
ArrayList(Collection c):

This constructor is used to build an array list initialized with the elements from the collection c. Suppose, we wish to create an arraylist arr which contains the elements present in the collection c, then, it can be created as:

ArrayList arr = new ArrayList(c);

//So here we are getting result list elements at index i.
List set = new ArrayList<>(result.get(i));

//Then we are appending the 'n' to above list.
set.add(n);

n = 1
s = 1
result.get(i) = []
result = [[], [1]]

n = 2
s = 2
result.get(i) = []
result = [[], [1], [2]]
result.get(i) = [1]
result = [[], [1], [2], [1, 2]]

n = 3
s = 4
result.get(i) = []
result = [[], [1], [2], [1, 2], [3]]
result.get(i) = [1]
result = [[], [1], [2], [1, 2], [3], [1, 3]]
result.get(i) = [2]
result = [[], [1], [2], [1, 2], [3], [1, 3], [2, 3]]
result.get(i) = [1, 2]
result = [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]

