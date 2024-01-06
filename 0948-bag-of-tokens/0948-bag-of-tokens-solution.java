/*
Sort tokens.
Buy at the cheapest and sell at the most expensive.
- Use low tokens[l] for more score and high tokens[r] for more power.

-- In Question, said no need to use all tokens but use all to get more score.

Seems easier if we just use two pointers.
    Sort the tokens, l = 0 and r = length - 1
    If there is enough power to flip the token[l], then do it and get 1 point.
    if there is not enough power to flip token[l], then use 1 point to get the token[r] power.
    If can not do both, stop.
*/

class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);

        int l = 0, r = tokens.length - 1;
        int score = 0;
        int maxScore = 0;

        while(l <= r) {
            if (tokens[l] <= power) {  // when power is available, increase score. This is 1st preference.
                power = power - tokens[l];
                l++;
                score++;
                maxScore = Math.max(maxScore, score);
            } else if (score > 0) {  // when score is available, increase the power.
                power = power + tokens[r];
                r--;
                score--;
            } else {  // when both are not satisfied.
                break;
            }
        }

        return maxScore;
    }
}
