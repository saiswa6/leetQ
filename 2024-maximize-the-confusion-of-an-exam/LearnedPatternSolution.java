class Solution {

    // Non-Shrinkable Version
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int fLeft = 0;
        int tLeft = 0;
        int right = 0;
        int numberFalse = 0;
        int numberTrue = 0;
        int answer = 0;

        int length = answerKey.length();

        for( ; right < length ; right++ ) {
            if(answerKey.charAt(right) == 'F') {
                numberFalse++;
            } else if(answerKey.charAt(right) == 'T') {
                numberTrue++;
            }

            if(numberFalse > k) {
                if(answerKey.charAt(fLeft) == 'F') {
                    numberFalse--;
                }
                fLeft++;
            }
            if(numberTrue > k) {
                if(answerKey.charAt(tLeft) == 'T') {
                    numberTrue--;
                }
                tLeft++;
            }

        }
        return Math.max(right - fLeft , right - tLeft);

// Shrinkable Version
        /*
        int fLeft = 0;
        int tLeft = 0;
        int right = 0;
        int numberFalse = 0;
        int numberTrue = 0;
        int answer = 0;

        int length = answerKey.length();

        for( ; right < length ; right++ ) {
            if(answerKey.charAt(right) == 'F') {
                numberFalse++;
            } else if(answerKey.charAt(right) == 'T') {
                numberTrue++;
            }

            while(numberFalse > k) {
                if(answerKey.charAt(fLeft) == 'F') {
                    numberFalse--;
                }
                fLeft++;
            }
            while(numberTrue > k) {
                if(answerKey.charAt(tLeft) == 'T') {
                    numberTrue--;
                }
                tLeft++;
            }

            answer = Math.max(answer, Math.max(right - fLeft + 1, right - tLeft + 1));
        }
        return answer;
         */

    }
}
