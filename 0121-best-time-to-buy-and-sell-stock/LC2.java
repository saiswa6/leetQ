/*
Approach 2: One Pass
Algorithm
Say the given array is:
[7, 1, 5, 3, 6, 4]

If we plot the numbers of the given array on a graph, we get:

The points of interest are the peaks and valleys in the given graph. We need to find the largest price following each valley, which difference could be the max profit.
We can maintain two variables - minprice and maxprofit corresponding to the smallest valley and maximum profit (maximum difference between selling price and minprice) obtained so far respectively.
*/
public class Solution {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
/*
Complexity Analysis
Time complexity: O(n). Only a single pass is needed.
Space complexity: O(1). Only two variables are used.
*/
