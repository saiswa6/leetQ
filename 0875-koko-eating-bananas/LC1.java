/*
Approach 1: Brute Force
Intuition
Does the order by which Koko eats affect the overall time?

- The answer is no. The order does not matter because Koko will stop eating for the rest of the hour, even if there are no more bananas left in the current pile. Therefore, the time she spends eating a particular pile is given as currTime=⌈NumberOfBananas/speed⌉, regardless of the order of this pile in her eating plan (⌈x⌉ denotes ceil(x)). Thus, we can conclude that as long as the eating speed is the same, the order of the piles by which Koko eats does not affect the total hours, so we can keep the array as it is for convenience.
- The brute force approach is to try every possible eating speed to find the smallest workable speed. Starting from speed=1 and incrementing it by 1 each time, we will find a speed at which Koko can eat all piles within hhh hours, that is, the first minimum speed.

Algorithm
1. Start at speed=1
2. Given the current speed, calculate how many hours Koko needs to eat all of the piles.
   - If Koko cannot finish all piles within h hours, increment speed by 1, that is speed=speed+1 and start over step 2.
   - If Koko can finish all piles within hhh hours, go to step 3.
3. Return the speed as the answer.

Complexity Analysis
Let n be the length of input array piles and m be the upper bound of elements in piles

Time complexity: O(nm)
For each eating speed speed, we iterate over piles and calculate the overall time, which takes O(n) time.
Before finding the first workable eating speed, we must try every smaller eating speed. Suppose in the worst-case scenario (when the answer is m), we have to try every eating speed from 1 to m, that is a total of m iterations over the array.
To sum up, the overall time complexity is O(nm)

Space complexity: O(1)
For each eating speed speedspeedspeed, we iterate over the array and calculate the total hours Koko spends, which costs constant space.
Therefore, the overall space complexity is O(1)
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Start at an eating speed of 1.
        int speed = 1;

        while (true) {
            // hourSpent stands for the total hour Koko spends with 
            // the given eating speed.
            int hourSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / speed)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / speed);
                if (hourSpent > h) {
                    break;
                }
            }

            // Check if Koko can finish all the piles within h hours,
            // If so, return speed. Otherwise, let speed increment by
            // 1 and repeat the previous iteration.
            if (hourSpent <= h) {
                return speed;
            } else {
                speed += 1;
            }            
        }
    }
}
