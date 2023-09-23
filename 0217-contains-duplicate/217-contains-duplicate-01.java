/*Approach 1: Brute Force
Intuition:
The brute force approach compares each element with every other element in the array to check for duplicates. If any duplicates are found, it returns true. This approach is straightforward but has a time complexity of O(n^2), making it less efficient for large arrays.

Explanation:
The brute force approach involves comparing each element in the array with every other element to check for duplicates. If any duplicates are found, return true, otherwise return false.

The time complexity of this approach is O(n^2), where n is the length of the array.
so, this approach is not efficient for large arrays -> TLE  

Note
This approach will get Time Limit Exceeded on LeetCode. Usually, if an algorithm is O(n2) it can handle n up to around 10^4. It gets Time Limit Exceeded when n≥10^5.
*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }
}
