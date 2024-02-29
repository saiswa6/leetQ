/*
Approach 3: Backtracking
Intuition
- As demonstrated in the previous approaches, the key to this problem is figuring out how to avoid duplicate subsets. In the previous approach, we discussed an iterative way to do so. Although the iterative approach is optimal in terms of time and space complexity, recursive approaches tend to be more intuitive, so let's see how we can solve this problem recursively.

- When designing our recursive function, there are two main points that we need to consider at each function call:
  - Whether the element under consideration has duplicates or not.
  - If the element has duplicates, which element among the duplicates should be considered while creating a subset.

- Start with an empty list and the starting index set to 0. At each function call, add a new subset to the output list of subsets. Scan through all the elements in the nums array from the starting index (written in blue in the above diagram) to the end. Consider one element at a time and decide whether to keep it or not. If we haven't seen the current element before, then add it to the current list and make a recursive function call with the starting index incremented by one. Otherwise, the subset is a duplicate and so we ignore it. Thus, if in a particular function call we scan through k distinct elements, there will be k different branches.

Algorithm
1. First, sort the array in ascending order.
2. Use a recursive helper function subsetsWithDupHelper to generate all possible subsets. The subsetsWithDupHelper has the following parameters:
    - Output list of subsets (subsets).
    - Current subset (currentSubset).
    - nums array.
    - the index in the nums array from where we should start scanning elements at that function call (index).
3. At each recursive function call:
    - Add the currentSubset to the subsets list.
    - Iterate over the nums array from index to the array end.
       - If the element is considered for the first time in that function call, add it to the currentSubset list. Make a function call to subsetsWithDupHelper with index = current element position + 1.
       - Otherwise, the element is a duplicate. So we skip it as it will generate duplicate subsets (refer to the figure above).
       - While backtracking, remove the last added element from the currentSubset and continue the iteration.
4. Return subsets list.
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();

        subsetsWithDupHelper(subsets, currentSubset, nums, 0);
        return subsets;
    }

    private void subsetsWithDupHelper(List<List<Integer>> subsets, List<Integer> currentSubset, int[] nums, int index) {
        // Add the subset formed so far to the subsets list.
        subsets.add(new ArrayList<>(currentSubset));

        for (int i = index; i < nums.length; i++) {
            // If the current element is a duplicate, ignore.
            if (i != index && nums[i] == nums[i - 1]) { // skip
                continue;
            }
            currentSubset.add(nums[i]);
            subsetsWithDupHelper(subsets, currentSubset, nums, i + 1);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}

/*
Complexity Analysis
Here n is the number of elements present in the given array.

Time complexity: O(n⋅2n)
As we can see in the diagram above, this approach does not generate any duplicate subsets. Thus, in the worst case (array consists of nnn distinct elements), the total number of recursive function calls will be 2 ^ n . Also, at each function call, a deep copy of the subset currentSubset generated so far is created and added to the subsets list. This will incur an additional O(n) time (as the maximum number of elements in the currentSubset will be n). So overall, the time complexity of this approach will be O(n⋅2^n).

Space complexity: O(n)
- The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(log⁡n). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort and Insertion Sort with the worst case space complexity of O(log⁡n)O. Thus the use of inbuilt sort() function adds O(log⁡n) to space complexity.
- The recursion call stack occupies at most O(n) space. The output list of subsets is not considered while analyzing space complexity. So, the space complexity of this approach is O(n).


*/
