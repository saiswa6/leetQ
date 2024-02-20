/*
Approach: Binary Search
Intuition
An intuitive approach would be to start with checking ship capacity equal to the largest weight in weights, say w. The ship's capacity cannot be smaller than w. We check if it is possible to ship all the packages within days days, using w as the capacity of the ship. If we are able to ship all the packages within the required days, we have w as our required answer.

Otherwise, we increment the capacity and try with w + 1. If we are able to ship the packages within the required days now, w + 1 is the answer. Otherwise, we try with the ship's capacity as w + 2.

How long can we go? In the worst case, we might need to choose the capacity of the ship equal to the sum of all the weights in weights and send them all in one day. So, our range starts from the largest weight and goes until the sum of the weights in weights.

This approach will provide the right answer to all the test cases but will indicate that the time limit has been exceeded. Because, in the worst case, we might need to check the ship's capacity from the largest weight to the sum of all elements in weights. The sum of all elements can reach n * 500, since 500 is the maximum weight we can have as per the problem constraints. So, we might need to check O(n⋅500)O(n \cdot 500)O(n⋅500) different values of ship capacity. For each capacity, we need to iterate over all the elements of weights to check whether we can ship the packages in the required number of days or not, this would require O(n) time. As a result, the total time required would be O(n⋅n⋅500)=O(n2⋅500) which would lead to TLE.

Let's think of a faster way by making some observations.

If we cannot ship the packages in the required days with capacity A, we can never ship packages with a capacity less than A. Also, if we can ship the packages in the required days with capacity B, we can always ship them with a capacity greater than B. So, in such a case, the optimal capacity lies between [A + 1, B] (both inclusive).

A scenario like this where our task is to search for an element X from a given range (L, R) where all values smaller than X do not satisfy a certain condition and all values greater than or equal to X satisfy it (or vice-versa), can be solved optimally with a binary search algorithm. In binary search, we repeatedly divide the solution space where the answer could be in half until the range contains just one element.

Complexity Analysis:
Here, n is the length of weights.
Time complexity: O(n⋅log⁡(500⋅n))=O(n⋅log⁡(n))
- It takes O(n) time to iterate through weights to compute maxLoad and totalLoad.
= In the binary search algorithm, we divide our range by half every time. So for a range of length R, it performs O(log⁡(R)) operations. In our case, the range is from maxLoad to totalLoad. As mentioned in the problem constraints, maxLoad can be 500, so the total load can be n * 500. So, in the worst case, the size of the range would be (n - 1) * 500 which would require O(log⁡(500n−500))=O(log⁡(n)) operations using a binary search algorithm.
- To see if we can deliver the packages in the required number of days with a specific capacity, we iterate through the weights array to see if the current capacity allows us to carry the all the packages in days days, which needs O(n) time.
So it would take O(n⋅log⁡(n)) time in total.

Space complexity: O(1)
We are only defining a few integer variables.
*/

class Solution {
    // Check whether the packages can be shipped in less than "days" days with
    // "c" capacity.
    Boolean feasible(int[] weights, int c, int days) {
        int daysNeeded = 1, currentLoad = 0;
        for (int weight : weights) {
            currentLoad += weight;
            if (currentLoad > c) {
                daysNeeded++;
                currentLoad = weight;
            }
        }

        return daysNeeded <= days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int totalLoad = 0, maxLoad = 0;
        for (int weight : weights) {
            totalLoad += weight;
            maxLoad = Math.max(maxLoad, weight);
        }

        int l = maxLoad, r = totalLoad;

        while (l < r) {
            int mid = (l + r) / 2;
            if (feasible(weights, mid, days)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
