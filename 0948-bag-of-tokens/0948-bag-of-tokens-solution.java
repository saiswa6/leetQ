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

IDEA:----------
This is something related with greedy + two pointer.

        We firstly sort the array.
        
        How sorting helps? Sorting helps in such a way that we know in order to play face up either we pick any element
        that is having very much large value or we pick any element that is having very less value,
        our score in both the cases is going to increase by 1.
        
        Similarly, we know in order to play face down to earn more power, either we add small value or large
        value in both cases our score is going to decrease by 1.
        
        So, why not to play logically, When we have to spend our power in order to increase score
        we will try to spend as less as we can, so that we left with more power.
        
        And also, when we have to spend our score in order to increase our power we will try to pick as much as big value
        so that our power will increase more.
        
        Since we want to maximise our score so we try to play as much as face up we can.
        
        If at any point we are not able to play face up, that means at this point we do not have enough
        power to play face up.
        
        Now at this point our goal becomes to gain power.
        
        And how can we gain power?
        
        We can gain power only when we have atleast 1 score.
        And if we have choice to pick any element of the array and add to our power,
        it's kind of obvious choice to take that element which is having maximum value at that time.
        
        That's why we will use Sorting + two Pointer.

So, What we will do?

        We will play face up till when we have enough power.
        
        If at any point we do not have enough power, at that point we will try to play face down.
        
        We will play face down by picking up the element maximum available element at that time.

Solution - I (Greedy + Two pointer)-
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
