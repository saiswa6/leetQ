/*
Algorithm :

Start both indexes (insertIndex, i) from 1.
insertIndex and i represents our First and second Index respectively.

Check if the previous element is different from the current element
The previous element is the element just before our i index i.e element present at arr[i-1]

If found different then perform arr[insertIndex] = arr[i] and increment insertIndex by 1

Increment i index by 1 till we reach end of the array

Note: After reaching the end of the array, our insertIndex variable will hold the count of unique elements in our input array.
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int insertIndex = 1;
        for(int i = 1; i < nums.length; i++){
            // We skip to next index if we see a duplicate element
            if(nums[i - 1] != nums[i]) {
                /* Storing the unique element at insertIndex index and incrementing
                   the insertIndex by 1 */
                nums[insertIndex] = nums[i];     
                insertIndex++;
            }
        }
        return insertIndex;
    }
}


/*Approach 3
By iterating through the array and checking if nums at the current index i is less than nums at i + 1, we can find an index for all unique numbers in the array. We can then insert each of these numbers to the beginning of the array, at addIndex. addIndex starts at 0 as the first element in the array is always unique.

Complexity
Time complexity: O(n)

Space complexity: O(1) */

//Code
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)
            return 0;
        
        int addIndex = 1; //index that unique characters will be inserted at

        for(int i = 0; i < nums.length - 1; i++) {
            
            if(nums[i] < nums[i + 1]){ //if true, num[i + 1] is a new unique number
              nums[addIndex] = nums[i + 1];
              addIndex++;
            }
        }
        return addIndex;
    }
}
