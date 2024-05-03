/*
Approach 1: Greedy
Greedy algorithms
- Greedy problems usually look like "Find the minimum number of something to do something" or "Find the maximum number of something to fit in some conditions", and typically propose an unsorted input.
- The idea of the greedy algorithm is to pick the locally optimal move at each step, that will lead to the globally optimal solution.
- The standard solution has O(Nlog⁡N) time complexity and consists of two parts:
  - Figure out how to sort the input data (O(Nlog⁡N) time). That could be done directly by sorting or indirectly by heap usage. Typically sort is better than the heap usage because of gain in space.
  - Parse the sorted input to have a solution (O(N) time).

--------------------
- Let's sort the balloons by the end coordinate, and then check them one by one. The first balloon is a green number 0, it ends at coordinate 6, and there are no balloons ending before it because of sorting.
- The other balloons have two possibilities :
  - To have a start coordinate smaller than 6, like a red balloon. These ones could be burst together with the balloon 0 by one arrow.
  - To have a start coordinate larger than 6, like a yellow balloon. These ones couldn't be burst together with the balloon 0 by one arrow, and hence one needs to increase the number of arrows here.
That means that one could always track the end of the current balloon, and ignore all the balloons which end before it. Once the current balloon is ended (= the next balloon starts after the current balloon), one has to increase the number of arrows by one and start to track the end of the next balloon.
*/

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        // sort by x_end
        Arrays.sort(points, (o1, o2) -> {   // uNDERSTAND THIS
            // We can't simply use the o1[1] - o2[1] trick, as this will cause an 
            // integer overflow for very large or small values.
            if (o1[1] == o2[1]) return 0;
            if (o1[1] < o2[1]) return -1;
            return 1;
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p: points) {
            xStart = p[0];
            xEnd = p[1];

            // If the current balloon starts after the end of another one,
            // one needs one more arrow
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }

        return arrows;
    }
}

/*
Let's break down the code:

- findMinArrowShots method: This is the main method that takes a 2D array of points as input and returns an integer representing the minimum number of arrows required.
- Base case check: The first thing the code does is check if the input array points is empty. If it is, it means there are no balloons, so the method returns 0 arrows needed.
- Sorting by end points: The code then sorts the array of points based on the end points of each balloon's interval. This is crucial for the algorithm to work efficiently.
- Sorting comparator: The Arrays.sort method is used with a custom comparator. The comparator compares two intervals based on their end points. If the end points are equal, the intervals are considered equal. If the end point of the first interval is less than the end point of the second interval, the first interval comes before the second. Otherwise, the first interval comes after the second.
- Iterating through sorted points: After sorting, the code initializes arrows to 1, indicating that at least one arrow is needed. It also initializes firstEnd with the end point of the first balloon (since the array is sorted, this is the smallest end point).
- Iterating over balloons: The code then iterates through each balloon in the sorted array. For each balloon, it checks if its start point is after the firstEnd. If it is, it means this balloon cannot be burst with the same arrow as the previous one, so it increments the arrows count and updates firstEnd to the end point of the current balloon.
- Return: Finally, the method returns the arrows count, which represents the minimum number of arrows required to burst all the balloons.
*/

/*
Complexity Analysis

Time complexity : O(Nlog⁡N) because of sorting of the input data.

Space complexity : O(log⁡N)
*/
