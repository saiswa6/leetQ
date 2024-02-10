/*
Approach 3: Two Pointers
Intuition
- Let us try sorting the array first. For example, nums=[3,5,2,8,1] becomes [1,2,3,5,8].
- Let us look at an example nums=[1,2,3,5,8], and target=7.

[1, 2, 3, 5, 8]
 ↑           ↑
left       right
Let us initialize two indices, leftleftleft and rightrightright pointing to the first and last element respectively.

When we look at the sum of first and last element, it is 1+8=9, which is ≥target. That tells us no index pair will ever contain the index right. So the next logical step is to move the right pointer one step to its left.

[1, 2, 3, 5, 8]
 ↑        ↑
left    right
Now the pair sum is 1+5=6, which is less than target. How many pairs with one of the index=left that satisfy the condition? You can tell by the difference between right and left which is 3, namely (1,2),(1,3), and (1,5). Therefore, we move left one step to its right.

Complexity analysis
Time complexity: O(n2).
twoSumSmaller takes O(n) at most since it touches each element in the array once. It's parent function, threeSumSmaller takes O(nlog⁡n) to sort the array, then runs a loop that touches (n−2) elements, invoking twoSumSmaller at each iteration. Therefore, the overall time complexity is O(nlog⁡n+n2), which boils down to O(n2).
Space complexity: O(1) because no additional data structures are used.
*/

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }
}
