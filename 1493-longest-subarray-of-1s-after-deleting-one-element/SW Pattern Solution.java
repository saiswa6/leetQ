// Windows Shrinkable Pattern Version

/*Sliding Window (Shrinkable)
What's state? cnt as the number of 0s in the window.
What's invalid? cnt > 1 is invalid.*/
// OJ: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
// Author: github.com/lzl124631x
// Time: O(N)
// Space: O(1)
class Solution {
public:
    int longestSubarray(vector<int>& A) {
        int i = 0, j = 0, N = A.size(), cnt = 0, ans = 0;
        for (; j < N; ++j) {
            cnt += A[j] == 0;
            while (cnt > 1) cnt -= A[i++] == 0;
            ans = max(ans, j - i); // note that the window is of size `j - i + 1`. We use `j - i` here because we need to delete a number.
        }
        return ans;
    }
};

// My Soln
class Solution {
    public int longestSubarray(int[] nums) { 
        int answer = 0;
        int zeroCount = 0;
        int left = 0;
        
        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0) {
                zeroCount++;
            }

            while(zeroCount > 1) {  //-------------------------> Replace wth if to make it non-shrinkable
                if(nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            answer = Math.max(answer, right - left);
        }
        return answer;
    }
}

        

// Windows Non-Shrinkable Pattern Version
===================================================
    //Sliding Window (Non-shrinkable)
// OJ: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
// Author: github.com/lzl124631x
// Time: O(N)
// Space: O(1)
class Solution {
public:
    int longestSubarray(vector<int>& A) {
        int i = 0, j = 0, N = A.size(), cnt = 0;
        for (; j < N; ++j) {
            cnt += A[j] == 0;
            if (cnt > 1) cnt -= A[i++] == 0;
        }
        return j - i - 1;
    }
};

//My Solution
class Solution {
    public int longestSubarray(int[] nums) {
        int answer = 0;
        int zeroCount = 0;
        int left = 0;
        
        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0) {
                zeroCount++;
            }

            if(zeroCount > 1) {   //-------------------------> Replace wth if to make it non-shrinkable
                if(nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            answer = Math.max(answer, right - left);
        }
        return answer;
    }
}
