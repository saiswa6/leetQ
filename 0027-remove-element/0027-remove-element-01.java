/*
Hints :
1. Try two pointers.
2. Did you use the fact that the order of elements can be changed?
3. What happens when the elements to remove are rare?

Approach 1: Two Pointers
Intuition :
Since this question is asking us to remove all elements of the given value in-place, we have to handle it with O(1) extra space. How to solve it? We can keep two pointers reader and writer pointers, where reader pointer is to read every element in the array, and the writer pointer is to write numbers selectively. writer pointer points to the number of elements in the array by removing numbers.

Algorithm
When nums[reader] equals to the given value, skip this element by incrementing the reader. As long as nums[reader]≠val, we copy nums[reader] to nums[writer] and increment both indexes at the same time. Repeat the process until the reader reaches the end of the array and the new length is writer.

Complexity analysis
Time complexity: O(n).
Assume the array has a total of n elements, both i and j traverse at most 2n steps.

Space complexity: O(1).
*/

public int removeElement(int[] nums, int val) {
    int writer = 0;
    for (int reader = 0; reader < nums.length; reader++) {
        if (nums[reader] != val) {
            nums[writer] = nums[reader];
            writer++;
        }
    }
    return writer;
}
