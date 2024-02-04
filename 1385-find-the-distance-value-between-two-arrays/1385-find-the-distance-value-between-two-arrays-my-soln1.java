class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int answer = 0;
        for(int i = 0; i< arr1.length; i++) { // Iterate theough each element of arr1 and find whether any arr1 element satisfies |arr1[i] - arr2[j]| <= d. If no, increment answer variable.
            int lowerBound = arr1[i] - d; // |arr1[i] - arr2[j]| <= d means arr2[j] = arr1[i]-d and  arr2[j] = arr1[i]+d will satisfy the condition.
            int upperBound = arr1[i] + d;
            boolean conditionSatisfy = false;;
            for(int j = 0; j < arr2.length; j++) {
                if(lowerBound <= arr2[j] && arr2[j] <= upperBound) { // Use this boundaries to increment answer or not.
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
