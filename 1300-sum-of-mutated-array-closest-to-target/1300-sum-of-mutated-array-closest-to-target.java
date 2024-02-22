class Solution {
    public int findBestValue(int[] arr, int target) {
        int diff = Integer.MAX_VALUE;
        int left = 0;
        int right = 1;
        int res = 1;
        int sum = 0;
        for(int element : arr) {
            sum += element;
            right = Math.max(right, element);
        }

        if(sum == target) {
            return right;
        }

        while(left <= right) {
            int mid = left + (right - left)/2;
            sum = getSum(arr, mid);
            if(sum > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if(Math.abs(sum-target) < diff || (Math.abs(sum - target) == diff && mid < res)) {
                res = mid;
                diff = Math.abs(sum - target);
            }
        }
        return res;
    }

    public int getSum(int[] arr, int value) {
        int sum = 0;
        for(int element : arr) {
            if(element > value) {
                sum += value;
            } else {
                sum += element;
            }
        }
        return sum;
    }
}
