/*
Approach 3: Binary Search
Intuition
- Note: the previous two approaches are the optimal solutions and are sufficient to solve the problem. Here, we will look at another unique way to approach the problem for the sake of completeness.
- Given an index i, if we treat nums[i] as target, we are concerned with how many elements on the left we can take. In the earlier approaches, we used a sliding window. In this approach, we will directly find the left-most index of these elements using binary search.
- Let's say that best is the index of the furthest element to the left that we could increment to target = nums[i]. Note that here, best is analogous to what left was after the while loop finished in the first approach. How do we find best?

- The value of best must be in the range [0, i]. We will perform a binary search on this range. For a given index mid:
  - The number of elements in the window would be count = i - mid + 1.
  - Thus, the final sum after making every element in the window equal to target would be finalSum = count * target.
  - The original sum of the elements is the sum of the elements from index mid to index i. We can use a prefix sum to find this originalSum.
  - Thus, the number of operations we need is operationsRequired = finalSum - originalSum.
  - If operationsRequired > k, it's impossible to include the index mid. We update left = mid + 1.
  - Otherwise, the task is possible and we should look for a better index. We update best = mid and right = mid - 1.
  
Essentially, we are binary searching the left bound from the first approach for a given right bound i. If we pre-process a prefix sum, then for each mid, we have all the necessary information to find operationsRequired.

Algorithm
1. Define a function check(i):
   - Initialize the following integers:
     - target = nums[i], the current target.  
     - left = 0, the left bound of the binary search.
     - right = i, the right bound of the binary search.
     - best = i, the best (furthest left) index that we can increment to target.
  - While left <= right
     - Calculate mid = (left + right) / 2.
     - Calculate count = i - mid + 1.
     - Calculate finalSum = count * target.
     - Calculate originalSum = prefix[i] - prefix[mid] + nums[mid].
     - Calculate operationsRequired = finalSum - originalSum.
     - If operationsRequired > k, move left = mid + 1.
     - Otherwise, update best = mid and right = mid - 1.
  - Return i - best + 1.
2. Sort nums.
3. Create a prefix sum of nums.
4. Initialize ans = 0.
5. Iterate i over the indices of nums:
   - Update ans with check(i) if it is larger.
6. Return ans.


Complexity Analysis
Given n as the length of nums,
Time complexity: O(n⋅log⁡n)
First, we sort nums which costs O(n⋅log⁡n)
Next, we iterate over the indices of nums. For each of the O(n) indices, we call check, which costs up to O(log⁡n)) as its a binary search over the array's elements. The total cost is O(n⋅log⁡n)

Space complexity: O(n)
The prefix array uses O(n) space.
*/

class Solution {
    public int check(int i, int k, int[] nums, long[] prefix) {
        int target = nums[i];
        int left = 0;
        int right = i;
        int best = i;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            long count = i - mid + 1;
            long finalSum = count * target;
            long originalSum = prefix[i] - prefix[mid] + nums[mid];
            long operationsRequired = finalSum - originalSum;
            
            if (operationsRequired > k) {
                left = mid + 1;
            } else {
                best = mid;
                right = mid - 1;
            }
        }
        
        return i - best + 1;
    }
    
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long[] prefix = new long[nums.length];
        prefix[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }
        
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, check(i, k, nums, prefix));
        }
        
        return ans;
        
    }
}
