/*
Approach: Prefix Sum
Intuition
- We start from the altitude 0 and we have a list of N integers, where each integer represents the gain in altitude at each step (it could be negative as well, which implies a fall in altitude) a biker takes. 
We need to return the highest altitude of the biker in the complete journey, including the starting point at 0.

- This can be solved by taking the maximum altitudes at each step in the journey. The altitude at a step can be determined as the altitude at the previous step plus the gain at the current step. 
Hence, we will start from 0 and keep adding the gain in altitude to it at each step, and after each addition, we will update the maximum altitude we have seen so far.

- If we observe closely, the altitude at a point is the sum of gains on the left of it, which is nothing but the prefix sum at this index. 
Therefore, we can find the prefix sum and return the maximum as the highest reached altitude.
*/
class Solution {
  public int largestAltitude(int[] gain) {
    int currentAltitude = 0;
    // Highest altitude currently is 0.
    int highestPoint = currentAltitude;

    for (int altitudeGain : gain) {
      // Adding the gain in altitude to the current altitude.
      currentAltitude += altitudeGain;
      // Update the highest altitude.
      highestPoint = Math.max(highestPoint, currentAltitude);
    }

    return highestPoint;
  }
}

/*
Complexity Analysis
Here, N is the number of integers in the list gain.
Time complexity: O(N)
We iterate over every integer in the list gain only once, and hence the total time complexity is equal to O(N).

Space complexity: O(1)
We only need two variables, currentAltitude and highestPoint; hence the space complexity is constant.
*/
