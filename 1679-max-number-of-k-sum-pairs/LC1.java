/*
Approach 1: Brute Force
The naive solution we could think of would be to pick an element from the array nums, then try to pair it with all the other elements in the array such that the sum of the pair formed is equal to k.
Once we find a matching pair, we could remove both elements from the array. 
However, removing the elements from an array could be costly, since an array stores element in a contiguous form, we could simply mark the array element to 000 value. 
Thus, in our case 000 value denotes that the element is already taken up or paired up with some other element.

We could begin by picking up the first element at the 0th index and find it's pair by iterating over all the elements beginning from the element at 1st index until we reach the end of the array. 
Thus, we could repeat the process until all the elements in the array are picked.
*/
class Solution {
    public int maxOperations(int[] nums, int k) {
        int count = 0;
        for (int first = 0; first < nums.length; first++) {
            // check if element pointed by first is already taken up
            if (nums[first] == 0) continue;
            for (int second = first + 1; second < nums.length; second++) {
                // check if element pointed by second is already taken up
                if (nums[second] == 0) continue;
                if (nums[first] + nums[second] == k) {
                    nums[first] = nums[second] = 0;
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}

/*
Complexity Analysis
Time Complexity : O(n2), where nnn is the length of array nums. We are using a nested for loop and pairing up every single element with every other element in the array. 
Thus, the time complexity of this approach would be O(n2)
This approach is exhaustive and results in Time Limit Exceeded (TLE)

Space Complexity: O(1), as we are using constant extra space to store the variable count and maintain pointers, first and second.
*/
