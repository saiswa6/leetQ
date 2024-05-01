/*
Approach 4: Two Pointer Approach Using Sort

In other words, we could say that, if the left pointer is pointing to the current element, we must adjust the right pointer to point to its complement and vice versa.
*/
class Solution {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < k) {
                left++;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else {
                left++;
                right--;
                count++;
            }
        }
        return count;
    }
}

/*
Complexity Analysis
Time Complexity : O(nlog⁡n), where n is the length of array nums.
The sort operation on the array takes O(nlog⁡n) time.
Each element is traversed only once, either by the left pointer or by the right pointer, depending on the fact that which pointer reaches that element first. Thus, traversing array takes O(n)\mathcal{O}(n)O(n) time.
This gives us total time complexity as O(nlog⁡n) + O(n) = O(nlog⁡n)

Space Complexity: O(1). We use constant extra space to track the count variable and maintain left,right pointers.

Space complexity : O(log⁡N)

The space complexity of the sorting algorithm depends on the implementation of each program language.
*/
