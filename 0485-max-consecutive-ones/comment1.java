/*
No need to actually keep the count variable. The number of 1s is the length of the sliding window.
Solution beats 100% with memory beating > 90%:
*/
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int max = 0, left = 0, right = 0;
        while (left < n && right < n) {
            while (left < n && nums[left] == 0)
                left++;
            right = left;
            while (right < n && nums[right] == 1)
                right++;
            max = Math.max(max, right - left);
            left = right;
        }
        return max;
    }
}

// approach without multiplication
def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
i = 1
curr_max = 0
while i < len(nums):
if nums[i] == 1:
nums[i] = nums[i] + nums[i -1]
i+=1
return max(nums)

// approach with multiplication
     def findMaxConsecutiveOnes(self, nums):
        m = nums[0]
      
        for i in range(1, len(nums)): 
            nums[i] = nums[i] * (nums[i] + nums[i-1])
            m = max(nums[i],m)
        return m 
