/*
Approach 2: Overwriting unwanted duplicates
Intuition
The second approach is really inspired by the fact that the problem statement asks us to return the new length of the array from the function. If all we had to do was remove elements, the function would not really ask us to return the updated length. However, in our scenario, this is really an indication that we don't need to actually remove elements from the array. Instead, we can do something better and simply overwrite the duplicate elements that are unwanted.
We won't be able to achieve this using a single pointer. We will be using a two-pointer approach where one pointer iterates over the original set of elements and another one that keeps track of the next "empty" location in the array or the next location that can be overwritten in the array.

Algorithm
We define two pointers, i and j for our algorithm. The pointer i iterates of the array processing one element at a time and j keeps track of the next location in the array where we can overwrite an element.
We also keep a variable count which keeps track of the count of a particular element in the array. Note that the minimum count would always be 1.
We start with index 1 and process one element at a time in the array.
If we find that the current element is the same as the previous element i.e. nums[i] == nums[i - 1], then we increment the count. If the value of count > 2, then we have encountered an unwanted duplicate element. In this case, we simply move forward i.e. we increment i but not j.
However, if the count is <= 2, then we can move the element from index i to index j.

If we encounter that the current element is not the same as the previous element i.e. nums[i] != nums[i - 1], then it means we have a new element at hand and so accordingly, we update count = 1 and also move this element to index j.

It goes without saying that whenever we copy a new element to nums[j], we have to update the value of j as well since j always points to the location where the next element can be copied to in the array.

Complexity Analysis

Time Complexity: O(N) since we process each element exactly once.
Space Complexity: O(1).
*/

class Solution {
    
    public int removeDuplicates(int[] nums) {
        
        //
        // Initialize the counter and the second pointer.
        //
        int j = 1, count = 1;
        
        //
        // Start from the second element of the array and process
        // elements one by one.
        //
        for (int i = 1; i < nums.length; i++) {
            
            //
            // If the current element is a duplicate, increment the count.
            //
            if (nums[i] == nums[i - 1]) {
                
                count++;
                
            } else {
                
                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }
            
            //
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            //
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
//////////////////////////////////////////////////////////

//MY SOLUTION
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)
        {
            return 0;
        }

        int writer = 0;
        int frequency = 0;
        int n = nums.length;
        int lastValue = nums[0]; //lastValue is needed when encounters {0,0,1,1}.At this point, reader and wrietr will become same and in first if block, frequency will be greater than 3, so elements 1,1 will be missed. so, Tracking last value is neeeded. 

        for(int reader=0;reader<n;reader++)
        {
            if(lastValue == nums[reader])// compare each time with lastValue only, not with writer because we are moving writer to next pointer
            {
                frequency++;
                if(frequency < 3) // update only when frequency is < 3.
                {
                    nums[writer] = nums[reader];
                    lastValue = nums[writer];
                    writer++;
                }
            }
            else{
                frequency = 1;
                nums[writer] = nums[reader];
                lastValue = nums[writer];
                writer++;
            }
        }
        return writer;
    }
}
