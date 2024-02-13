/*
Link : https://leetcode.com/problems/maximize-the-confusion-of-an-exam/solutions/1581341/detailed-explanation-multiple-approaches-explained-c-clean-code/

Approach 2: using Two Pointers
Idea is similar to Max Consecutive Ones III.

1. We take two pointers, and window is between these two.
2. Keep count of the number of T's and F's found in the current window.
3. If the count of both T and F is > k , then :
   - First reduce count of current item i.e at low index
   - And increment low pointer until the count of either T or F <= k .
4. At each iteration find the max window length.

Complexity :

Time : O(2N) , Since we traverse every item in string twice. Thus N+N = 2N
Space : O(1)
*/

class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        
        int n = answerKey.size();
        
        int count_T = 0, count_F = 0, low = 0, maxConsecutive = 1;
        
        for(int i=0; i<n; i++) {
            count_T += (answerKey[i] == 'T');
            count_F += (answerKey[i] == 'F');
            
            while(min(count_T, count_F) > k) {
                // move low ahead
                count_T -= (answerKey[low] == 'T');
                count_F -= (answerKey[low] == 'F');
                
                low++;
            }
            
            maxConsecutive = max(maxConsecutive, i - low + 1);
        }
        
        return maxConsecutive;
    }
};
