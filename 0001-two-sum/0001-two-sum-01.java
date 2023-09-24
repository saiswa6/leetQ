// Solution
/*Approach 1: Brute Force
Algorithm: The brute force approach is simple. Loop through each element x and find if there is another value that equals to (target−x).

Complexity Analysis:
Time complexity: O(n2)
For each element, we try to find its complement by looping through the rest of the array which takes O(n) time. Therefore, the time complexity is O(n2)
Space complexity: O(1)
The space required does not depend on the size of the input array, so only constant space is used.
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };         ////LEARN THIS
                }
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}

/*


*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        int start = 0, end = nums.length - 1;
        int arr [] = new int[2];
        int nums2 [] = nums.clone();
        Arrays.sort(nums2);
        int value1 =0, value2 =0;
        while(start < end)
        {
            if(nums2[start] + nums2[end] == target)
            {
                
                value1 = nums2[start];
                value2 = nums2[end];
                break;
            }
            else if (nums2[start] + nums2[end] > target)
            {
                end--;
            }
            else {
                start++;
            }
        }
        int j = 0;
        for(int i = 0;i < nums.length;i++)
        {
            if(nums[i] == value1 || nums[i] == value2)
            {
                arr[j] = i; 
                j++;
            }
        }
        return arr;
    }
}
