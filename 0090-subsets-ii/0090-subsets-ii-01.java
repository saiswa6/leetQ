/*
Since we may need to generate 2^n subsets, no solution can achieve better than exponential time complexity. However, when the value of n is small (between 1 and 10 in this case), exponential time complexity is acceptable. As such, in each of the following solutions, we'll focus our attention on generating all unique subsets while efficiently omitting all duplicate subsets.

Approach 1: Bitmasking
Intuition
- As discussed earlier, each element could be present in or absent from the subset, so there will be a total of 2^n distinct subsets for an array of length n with no duplicates. The maximum possible value of n is 10. Thus, no more than 2^10=1024 subsets will be generated. Since this number is small, we can represent all the subsets using bitmasking.
- The idea is that we map each subset to a bitmask of length n, where 1 in the ith position of the bitmask implies nums[i] is in the subset, and 0 implies nums[i] is not in the subset.
- Bitmask 000...0 signifies an empty subset, while bitmask 111...1 signifies that all numbers from the given array nums are present in the set. Thus, bitmask values from 0 to 2^n - 1 represent all possible subsets. We need to iterate over the binary representation of each number and depending on the position of set bits (bit value = 1) we can determine which numbers are present in the current subset. An unsigned integer can represent at most 32 (64 if we use long datatype for mask) positions via bitmasking. Since the given array size (ranging from 1 to 10) is less than 32, we can use bitmasking to represent all subsets. 
- However, in this approach, some of the generated subsets will be duplicates. So we need to use an additional set, seen, to detect duplicate subsets. In order to make use of seen, we must first sort the given array. Otherwise, seen won't be able to distinguish between duplicate subsets. For example, without sorting nums = [2,1,2] our algorithm will generate subsets [], [2], [1], [2], [2, 1], [2,2], [1, 2], [2, 1, 2]. Here subset [1, 2] should be considered as a duplicate of subset [2, 1]. To detect such duplicate subsets, prior sorting of the array is needed. This ensures all the included subsets are unique.

Algorithm
=========
1. Sort nums array. Sorting is required to ensure all the generated subsets will also be sorted. This helps to identify duplicates.
2. Initialize maxNumberOfSubsets to the maximum number of subsets that can be generated, i.e., 2^n.
3. Initialize a set, seen, of type string to store all the generated subsets. Adding all the sorted subsets to the set gives us the opportunity to catch any duplicate subsets.
4. Iterate from 0 to maxNumberOfSubsets - 1. The set bits in the binary representation of the subsetIndex indicate the position of the elements in the nums array that are present in the current subset.
5. Intialize a string hashcode which will store a comma separated list of numbers in the currentSubset as a string. hashcode is necessary to uniquely identify each subset via the use of a set.
6. We run an inner for loop from j = 0 to n - 1 to check the position of set bits in subsetIndex. If at the jth position bit is set, add nums[j] to the current subset currentSubset and append nums[j] to hashcode string.
7. Add the current subset currentSubset to seen and to the subsets list if the generated hashcode is not in seen.
8. Return subsets.

Complexity Analysis :
Here n is the size of the nums array.

Time complexity: O(n⋅2n)
Sorting the nums array requires nlog⁡n time. The outer for loop runs 2^n times. The inner loop runs n times. We know that average case time complexity for set operations is O(1). Although, to generate a hash value for each subset O(n) time will be required. However, we are generating the hashcode while iterating the inner for loop. So the overall time complexity is O(nlog⁡n+2^n(n (for inner loop) + n (for stringbuilder to string conversion in Java) )) = O(2^n*n)

Space complexity: O(n⋅2n)
We need to store at most 2^n number of subsets in the set, seen. The maximum length of any subset can be n.

The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(log⁡n). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort and Insertion Sort with the worst case space complexity of O(log⁡n). Thus the use of inbuilt sort() function adds O(log⁡n) to space complexity.

The space required for the output list is not considered while analyzing space complexity. Thus the overall space complexity in Big O Notation is O(n⋅2n)
 */

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> subsets = new ArrayList();
        int n = nums.length;

        // Sort the generated subset. This will help to identify duplicates.
        Arrays.sort(nums);

        int maxNumberOfSubsets = (int) Math.pow(2, n);
        // To store the previously seen sets.
        Set<String> seen = new HashSet<>();

        for (int subsetIndex = 0; subsetIndex < maxNumberOfSubsets; subsetIndex++) {
            // Append subset corresponding to that bitmask.
            List<Integer> currentSubset = new ArrayList();
            StringBuilder hashcode = new StringBuilder();
            for (int j = 0; j < n; j++) {
                // Generate the bitmask
                int mask = 1 << j;
                int isSet = mask & subsetIndex;
                if (isSet != 0) {
                    currentSubset.add(nums[j]);
                    // Generate the hashcode by creating a comma separated string of numbers in the currentSubset.
                    hashcode.append(nums[j]).append(",");
                }
            }
            if (!seen.contains(hashcode.toString())) {
                seen.add(hashcode.toString());
                subsets.add(currentSubset);
            }

        }

        return subsets;
    }
}
