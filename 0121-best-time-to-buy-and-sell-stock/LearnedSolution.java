// 
class Solution {
    public int maxProfit(int[] prices) {
        int largestDifference = 0;
        int minimumSoFar = Integer.MAX_VALUE;

        for(int i = 0 ; i < prices.length ; i++) {
            if(prices [i] < minimumSoFar) {  // record the minimumSo Far values below the current price[i]
                minimumSoFar = prices[i]; 
            } else { // If largest price[i] than minimumSoFar, update the largest profit.
                largestDifference = Math.max(largestDifference, prices[i] - minimumSoFar); 
            }
        }
        return largestDifference;
    }
}
