/*
Overview
The problem would have been simpler if we are allowed to use extra space.
We can create a map which stores all unique array elements as the key and element frequency as the value.
After populating our map, we get all the unique elements from our array.
We then iterate our map and push all the keys in our input array
However, without using extra space it makes it a bit tricky as we have to modify the existing input array



Approach 1: Two indexes approach
Intuition :
Let k be the count of unique elements in our input array.
It doesn't matter what elements we place after the first k elements.

From the condition, we can have a few observations here,

- Since the array we have is sorted, all duplicate values will be one after the other.
- We need to update the first k elements in an array with unique values and return the value of k.

Using the following intuition, let's understand how to address this problem.

The problem states that we need to fill the first k elements of an array with unique values.
For doing so, we modify the input array in-place so that we don't use extra space.
In order to perform in-place operations, we use the Two indexes approach
The first index updates the value in our input array while reading the data from the second index
First Index is responsible for writing unique values in our input array, while Second Index will read the input array and pass all the distinct elements to First Index.
We continue the above steps until the second index reaches the end of an array
*/

//MY SOLUTION

class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
           return 0;
        }
        int n = nums.length;
        int writer = 0, reader = 0;
        for(; reader< n ; reader++)
        {
            //when a new element is encountered, update writer pointer.
            if(nums[reader] != nums[writer])
            {
                writer++;
                nums[writer] = nums[reader];      
            }
        }
        return writer + 1;
    }
}
