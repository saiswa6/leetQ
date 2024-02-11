/*
Solution 2: Sliding Window with Deque  -- Not efficient but learn as solution

1. left = 0
2. dq = deque(), deque to store up to k indices of zeros so far.
3. For each right in range [0..n-1]:
   - If nums[right] == 0 then dq.append(right)
   - If len(dp) > k then left = dq.popleft() + 1: This is the case when nums[left, right] contains more than k zeros -> move left util the subarray contains no more than k zeros.
   - ans = max(ans, right - left + 1)
4. The result is ans.

Complexity:

Time: O(N)
Space: O(K)
*/

class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int left = 0;
        int ans = 0;
        deque<int> dq;
        for (int right = 0; right < nums.size(); ++right) {
            if (nums[right] == 0) dq.push_back(right);
            if (dq.size() > k) { // Case nums[left, right] contains more than k zeros, move `left` util the subarray has no more than k zeros
                left = dq.front() + 1;
                dq.pop_front();
            }
            ans = max(ans, right - left + 1);
        }
        return ans;
    }
};

