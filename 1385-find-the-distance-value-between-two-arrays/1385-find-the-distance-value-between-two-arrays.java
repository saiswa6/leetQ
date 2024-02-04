class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int answer = 0;
        Arrays.sort(arr2);
        for(int i = 0; i< arr1.length; i++) {
            int lowerBound = arr1[i] - d;
            int upperBound = arr1[i] + d;
            if(notInRange(arr2, lowerBound, upperBound)) {
                answer++;
            }
        }

        return answer;
    }


    public static boolean notInRange(int arr[], int lowerBound, int upperBound) {
        int start = 0;
        int end = arr.length - 1;

        while(start <= end) {
            int mid = start + (end - start)/2;
            if(lowerBound <= arr[mid] && arr[mid] <= upperBound) {
                return false;
            } else if (lowerBound > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return true;
    }
}