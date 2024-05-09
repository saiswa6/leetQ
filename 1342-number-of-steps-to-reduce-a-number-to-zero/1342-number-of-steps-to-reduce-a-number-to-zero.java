class Solution {
    public int numberOfSteps(int num) {
        if(num == 0) {
            return 0;
        }
        int stepsFor2 = 0;
        int stepsFor1 = 0;
        if(num % 2 == 0) {
            stepsFor2 = 1 + numberOfSteps(num/2);
        } else {
            stepsFor1 = 1 + numberOfSteps(num - 1);
        }

        return Math.max(stepsFor1, stepsFor2);
    }
}