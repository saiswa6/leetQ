/*
Approach #1 Cumulative Sum [Accepted]
Algorithm
- We know that in order to obtain the averages of subarrays with length k, we need to obtain the sum of these k length subarrays. One of the methods of obtaining this sum is to make use of a cumulative sum array, sum, which is populated only once. Here, sum[i] is used to store the sum of the elements of the given numsnumsnums array from the first element upto the element at the ith index.

- Once the sum array has been filled up, in order to find the sum of elements from the index i to i+k, all we need to do is to use: sum[i]−sum[i−k]. Thus, now, by doing one more iteration over the sum array, we can determine the maximum average possible from the subarrays of length k.
*/
public class Solution {
	public double findMaxAverage(int[] nums, int k) {
		int[] sum = new int[nums.length];
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++)
		sum[i] = sum[i - 1] + nums[i];
		double res = sum[k - 1] * 1.0 / k;
		for (int i = k; i < nums.length; i++) {
			res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
		}
		return res;
	}
}
/*
Complexity Analysis
Time complexity : O(n). We iterate over the numsnumsnums array of length nnn once to fill the sumsumsum array. Then, we iterate over n−k elements of sumsumsum to determine the required result.
Space complexity : O(n). We make use of a sum array of length nnn to store the cumulative sum.
*/
