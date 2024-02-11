/*
Take each element as Target.

T.C : O(N2)
S.C : O(1)
*/

//Brute Force Solution
        Arrays.sort(nums);
        int length = nums.length;
        int answer = 0;

        for(int i = length - 1; i >= 0 ; i--) {
            int element = nums[i];
            int eachAnswer = 0;
            int leftFreq = k;
            for(int j = i - 1; j >=0 ; j--) { // Take each element as Target.
                if(element - nums[j] <= leftFreq) { // Always check just before numbers of target. calculate difference to match target. If yes, increase answer. 
                    eachAnswer++;
                    leftFreq = leftFreq - element + nums[j]; // Use the left frewuency for next leftmost element
                } else {
                    break; // I No left Frequency, then break from looop
                }
            }
            answer = Math.max(answer, eachAnswer); // Track the maximum answer.
        }
        return answer + 1;*/
