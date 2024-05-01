/*
Approach #2 (Space Optimal, Operation Sub-Optimal) [Accepted]
This approach works the same way as above, i.e. , first fulfills one requirement and then another. The catch? It does it in a clever way. The above problem can also be stated in alternate way, " Bring all the non 0 elements to the front of array keeping their relative order same".

This is a 2 pointer approach. The fast pointer which is denoted by variable "cur" does the job of processing new elements. If the newly found element is not a 0, we record it just after the last found non-0 element. The position of last found non-0 element is denoted by the slow pointer "lastNonZeroFoundAt" variable. As we keep finding new non-0 elements, we just overwrite them at the "lastNonZeroFoundAt + 1" 'th index. This overwrite will not result in any loss of data because we already processed what was there(if it were non-0,it already is now written at it's corresponding index,or if it were 0 it will be handled later in time).

After the "cur" index reaches the end of array, we now know that all the non-0 elements have been moved to beginning of array in their original order. Now comes the time to fulfil other requirement, "Move all 0's to the end". We now simply need to fill all the indexes after the "lastNonZeroFoundAt" index with 0.
  */

void moveZeroes(vector<int>& nums) {
    int lastNonZeroFoundAt = 0;
    // If the current element is not 0, then we need to
    // append it just in front of last non 0 element we found.
    for (int i = 0; i < nums.size(); i++) {
        if (nums[i] != 0) {
            nums[lastNonZeroFoundAt++] = nums[i];
        }
    }
 	// After we have finished processing new elements,
 	// all the non-zero elements are already at beginning of array.
 	// We just need to fill remaining array with 0's.
    for (int i = lastNonZeroFoundAt; i < nums.size(); i++) {
        nums[i] = 0;
    }
}
/*
Complexity Analysis
Space Complexity : O(1)O(1)O(1). Only constant space is used.
Time Complexity: O(n)O(n)O(n). However, the total number of operations are still sub-optimal. The total operations (array writes) that code does is nnn (Total number of elements).
*/

/*
Solution 3 exists ->  Not Optimized
*/
