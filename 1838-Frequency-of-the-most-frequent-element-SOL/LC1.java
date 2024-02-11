/*
Approach 1: Sliding Window
Intuition
- In this problem, we want to make as many elements as we can equal using k increments.
- Let's say that we choose a number target and want to maximize its frequency. Intuitively, the elements that we would increment would be the elements that are closest to target (and less than target, since we can only increment).
- So what number should we choose for target? The optimal target will already exist in the array. Why?
   1. Assume target is in nums, but target - 1 and target + 1 are not in nums. Let's say that we can increment x elements to be equal to target using at most k operations. We will prove that making target - 1 or target + 1 the most frequent element does not lead to better results.
   2. It would be pointless to instead try to make target + 1 the most frequent element, since this would cost us x extra operations and we would not improve on our answer. The same goes for even larger elements target + 2 and etc.
   3. What about target - 1? Compared with making target the most frequent element, we would lose the values representing these targets from our max frequency, but we would save x operations which we could potentially use to increment more than one extra element and thus improve our answer.
   4. The above statement is true, but meaningless! Consider the greatest element in nums that is less than target. That is, if we were to sort nums, consider the element that comes right before target. If we were to instead consider this element as the target, we would save more than x operations without negatively affecting the frequency relative to considering target - 1.
   5. In summary, for any given number absent that is not in nums, consider the greatest number in nums smaller than absent as smallerTarget. The number of operations to raise some number of elements to smallerTarget will always be less than the number of steps needed to raise them to absent.
   6. Thus, the optimal value of target must exist in nums. We can iterate over nums and consider each element as target.



- For a given value of target, how can we efficiently check the frequency we could achieve? As we mentioned at the start, we would want to increment elements that are closest to target. As such, we will start by sorting nums so that as we iterate over the elements, we know the elements closest to target are just to the left of target.

- Now that nums is sorted, consider the first element to the left of target as smaller. As smaller is the closest element to target, we want to increment it to equal target. This will cost us target - smaller operations. Now, consider the next element to the left as smaller2. Now this is the element closest to target, so we increment it using target - smaller2 operations. We continue this process until we run out of operations.

- We will use a sliding window over the sorted nums. For each element nums[right], we will treat target as this element and try to make every element in our window equal to target.

- The size of the window is right - left + 1. That means we would have a final sum of (right - left + 1) * target. If we track the sum of our window in a variable curr, then we can calculate the required operations as (right - left + 1) * target - curr. If it requires more than k operations, we must shrink our window. Like in all sliding window problems, we will use a while loop to shrink our window by incrementing left until k operations are sufficient.

- Once the while loop ends, we know that we can make all elements in the window equal to target. We can now update our answer with the current window size. The final answer will be the largest valid window we find after iterating right over the entire input.


Algorithm
1. Sort nums.
2. Initialize the following integers:
   - left = 0, the left pointer.
   - ans = 0, the best answer we have seen so far.
   - curr = 0, the sum of the elements currently in our window.
3. Iterate right over the indices of nums:
   - Consider target = nums[right].
   - Add target to curr.
   - While the size of the window right - left + 1 multiplied by target, minus curr is greater than k:
     - Subtract nums[left] from curr.
     - Increment left.
   - Update ans with the current window size if it is larger.
4. Return ans.

Complexity Analysis
Given n as the length of nums,
Time complexity: O(n⋅log⁡n)
- Despite the while loop, each iteration of the for loop is amortized O(1). The while loop only runs O(n) times across all iterations. This is because each iteration of the while loop increments left. As left can only increase and cannot exceed n, the while loop never performs more than n iterations total. This means the sliding window process runs in O(n).

- However, we need to sort the array, which costs O(n⋅log⁡n)

Space Complexity: O(log⁡n) or O(n)
- We only use a few integer variables, but some space is used to sort.
- The space complexity of the sorting algorithm depends on the implementation of each programming language:

In Java, Arrays.sort() for primitives is implemented using a variant of the Quick Sort algorithm, which has a space complexity of O(log⁡n)
*/

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int ans = 0;
        long curr = 0;
        
        for (int right = 0; right < nums.length; right++) {
            int target = nums[right];
            curr += target;
            
            while ((right - left + 1) * target - curr > k) {
                curr -= nums[left];
                left++;
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}

// My Pattern Solution
public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int ans = 0;
        long curr = 0;

        for(int right = 0 ; right < nums.length; right++) {
            long target = nums[right];
            curr += target;

            while((right - left + 1)*(target) - curr > k) {
                curr -= nums[left];
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
}
