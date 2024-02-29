/*
Approach 2: Cascading (Iterative)
Intuition
- Assume the given array has no duplicate elements. In this case, there will be a total of 2^n  distinct subsets. To find all the subsets we start with an empty subset. This will be the first subset. Next, we consider one element at a time and add it to each of the existing subsets.
- However, in this problem, the given array can have duplicate elements which will produce duplicate subsets if we follow the previously mentioned approach. Thus, we need to omit the duplicate subsets. For this, we need to sort the given array first. To avoid adding duplicate subsets we follow this rule:
- Whenever the element under consideration has duplicates, we add one of the duplicate elements to all the existing subsets to create new subsets. For the rest of the duplicates, we only add them to the subsets created in the previous step. By convention, whenever a value is encountered for the first time, we add it to all the existing subsets. Then onwards we add its duplicates only to the subsets created in the previous step.

Algorithm
1. First, sort the array in ascending order.
2. Initialize a variable subsetSize to 0. subsetSize holds the index of the subset in the subsets list from where we should start adding the current element if the current element is a duplicate. In other words, it holds the index of the first subset generated in the previous step.
3. Iterate over the nums array considering one element at a time.
4. If we haven't seen the value of the current element before, we need to add this element to all the previously generated subsets. So set startingIndex to 0.
5. If the current element is a duplicate element, add it only to subsets that were created in the previous iteration. This means we will skip every subset that was created earlier than the previous iteration. So instead of setting startingIndex to 0, set it equal to subsetSize.
6. Set subsetSize to the current subsets size. This will be the starting index of the subsets generated in this iteration.
7. Add the current element to all the subsets in the subsets list created before the current iteration starting from startingIndex.
8. Return subsets list.
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<Integer>());

        int subsetSize = 0;

        for (int i = 0; i < nums.length; i++) {
            int startingIndex = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0;
            // subsetSize refers to the size of the subset in the previous step. This value also indicates the starting index of the subsets generated in this step.
            subsetSize = subsets.size();
            for (int j = startingIndex; j < subsetSize; j++) {
                List<Integer> currentSubset = new ArrayList<>(subsets.get(j));
                currentSubset.add(nums[i]);
                subsets.add(currentSubset);
            }
        }
        return subsets;
    }
}



/*
Complexity Analysis
Here n is the number of elements present in the given array.
Time complexity: O(n⋅2n)
At first, we need to sort the given array which adds O(nlog⁡n) to the time complexity. Next, we use two for loops to create all possible subsets. In the worst case, i.e., with an array of n distinct integers, we will have a total of 2^n subsets. Thus the two for loops will add O(2 ^ n) to time complexity. Also in the inner loop, we deep copy the previously generated subset before adding the current integer (to create a new subset). This in turn requires the time of order nnn as the maximum number of elements in the currentSubset will be at most nnn. Thus, the time complexity in the subset generation step using two loops is O(n⋅2^n). Thereby, the overall time complexity is O(nlog⁡n+n⋅2^n) = O(n⋅(log⁡n+2^n)) ~ O(n⋅2^n).

Space complexity: O(log⁡n)
The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(log⁡n) In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort and Insertion Sort with the worst case space complexity of O(log⁡n)O(\log n)O(logn). Thus the use of inbuilt sort() function adds O(log⁡n) to space complexity.

The space required for the output list is not considered while analyzing space complexity. Thus the overall space complexity in Big O Notation is O(log⁡n).

  */
