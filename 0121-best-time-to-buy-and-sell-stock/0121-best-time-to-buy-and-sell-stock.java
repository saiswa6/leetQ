class Solution {
    public int maxProfit(int[] prices) {
        int largestDifference = 0;
        int minimumSoFar = Integer.MAX_VALUE;

        for(int i = 0 ; i < prices.length ; i++) {
            if(prices [i] < minimumSoFar) {
                minimumSoFar = prices[i];
            } else {
                largestDifference = Math.max(largestDifference, prices[i] - minimumSoFar);
            }
        }
        return largestDifference;
    }
}