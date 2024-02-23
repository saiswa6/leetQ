/*
Approach 1: Brute Force

We need to find out the maximum difference (which will be the maximum profit) between two numbers in the given array. Also, the second number (selling price) must be larger than the first one (buying price).

In formal terms, we need to find max⁡(prices[j]−prices[i]), for every i and j such that j>i.
*/

public class Solution {
    public int maxProfit(int prices[]) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }
}
/*
Complexity Analysis
Time complexity: O(n2). Loop runs n(n−1)/2 times.
Space complexity: O(1). Only two variables - maxprofit and profit are used.
*/
