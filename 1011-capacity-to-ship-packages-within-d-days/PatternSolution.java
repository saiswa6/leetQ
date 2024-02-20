//Binary search probably would not come to our mind when we first meet this problem. We might automatically treat weights as search space and then realize we've entered a dead end after wasting lots of time. In fact, we are looking for the minimal one among all feasible capacities. We dig out the monotonicity of this problem: if we can successfully ship all packages within D days with capacity m, then we can definitely ship them all with any capacity larger than m. Now we can design a condition function, let's call it feasible, given an input capacity, it returns whether it's possible to ship all packages within D days. This can run in a greedy way: if there's still room for the current package, we put this package onto the conveyor belt, otherwise we wait for the next day to place this package. If the total days needed exceeds D, we return False, otherwise we return True.
//Next, we need to initialize our boundary correctly. Obviously capacity should be at least max(weights), otherwise the conveyor belt couldn't ship the heaviest package. On the other hand, capacity need not be more thansum(weights), because then we can ship all packages in just one day.


class Solution {
    int maxWeight = 0;
    int sumWeight = 0;
    public int shipWithinDays(int[] weights, int days) {
        calculateBoundaries(weights);

        if(days == 1){ // If no of days is 1, return all sum of weights
            return sumWeight;
        }
        if(days == weights.length){ // If days equal to weight length, return max of weights
            return maxWeight;
        }

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
