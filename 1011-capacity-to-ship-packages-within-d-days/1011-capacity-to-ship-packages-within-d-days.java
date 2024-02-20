class Solution {
    int maxWeight = 0;
    int sumWeight = 0;
    public int shipWithinDays(int[] weights, int days) {
        calculateBoundaries(weights);

        int left = maxWeight;
        int right = sumWeight;

        while(left < right) {
            int mid = left + (right - left)/2;
            if(feasible(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean feasible(int[] weights, int capacity, int days) {
        int totalDays = 1;
        int total = 0;

        for(int j = 0; j < weights.length; j++) {
            total += weights[j];
            if(total > capacity) {
                total = weights[j];
                totalDays++;
                if(totalDays > days) {
                    return false;
                }
            }
        }
        return true;
    }

    public void calculateBoundaries(int [] weights) {
        for(int i = 0; i < weights.length; i++) {
            sumWeight += weights[i];
            if(maxWeight < weights[i]) {
                maxWeight = weights[i];
            }
        }
    }
}