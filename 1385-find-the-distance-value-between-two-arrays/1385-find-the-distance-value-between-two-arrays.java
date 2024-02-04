class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int answer = 0;
        for(int i = 0; i< arr1.length; i++) {
            int lowerBound = arr1[i] - d;
            int upperBound = arr1[i] + d;
            boolean conditionSatisfy = false;;
            for(int j = 0; j < arr2.length; j++) {
                if(lowerBound <= arr2[j] && arr2[j] <= upperBound) {
                    conditionSatisfy = true;
                }
            }
            if(!conditionSatisfy) {
                answer++;
            }
            
        }

        return answer;
    }
}