/*
Approach 2: Binary Search
Intuition
- In the previous approach, we tried every smaller eating speed, before finding the first workable speed. We shall look for a more efficient way to locate the minimum workable eating speed.

- Recall how we calculated the total time for Koko to finish eating all the piles in approach 1. We can observe two laws:
   1. If Koko can eat all the piles with a speed of nnn, she can also finish the task with the speed of n+1
With a larger eating speed, Koko will spend less or equal time on every pile. Thus, the overall time is guaranteed to be less than or equal to that of the speed nnn.
   2. If Koko can't finish with a speed of nnn, then she can't finish with the speed of n−1 either.
       With a smaller eating speed, Koko will spend more or equal time on every pile, thus the overall time will be greater than or equal to that of the speed n.
- If the current speed is workable, the minimum workable speed should be on its left inclusively. If the current speed is not workable, that is, too slow to finish the eating task, then the minimum workable speed should be on its right exclusively.
Therefore, we can use binary search to locate the boundary that separates workable speeds and unworkable speeds, to get the minimum workable speed.

- First, let's set a reasonable upper and lower bound for binary search (to ensure that we do not miss any workable speed). Let the lower bound be 1, the minimum possible eating speed since there is no speed slower than 1. The upper bound will be the maximum eating speed, that is the maximum number of bananas in a pile. For instance, if the piles are [3,5,7,9], then 999 is the maximum number of bananas in a single pile, we can set the upper boundary as 999. Because Koko can eat every pile within 1 hour with a speed of 999, or any other faster speed, 999 is thus guaranteed to be a workable value.

- Once we set the boundaries, we can then apply the binary search to reduce the search space. In each iteration, we will reduce the remaining search space by half until we have narrowed down the search space to just one element, which is the minimum workable eating speed!

Algorithm
1. Initialize the two boundaries of the binary search as left=1, right=max(piles)
2. Get the middle value from left and right, that is, middle=(left+right)/2, this is Koko's eating speed during this iteration.
3. Iterate over the piles and check if Koko can eat all the piles within h hours given this eating speed of middle.
4. If Koko can finish all the piles within hhh hours, set right equal to middle signifying that all speeds greater than middle are workable but less desirable by Koko. Otherwise, set leftleftleft equal to middle+1 signifying that all speeds less than or equal to middle are not workable.
5. Repeat the steps 2, 3, and 4 until the two boundaries overlap, i.e., left=right, which means that we have found the minimum speed by which Koko could finish eating all the piles within hhh hours. We can return either left or right as the answer.

Complexity Analysis
Let n be the length of the input array piles and m be the maximum number of bananas in a single pile from piles

Time complexity: O(n⋅log⁡m)
The initial search space is from 1 to m, it takes log⁡m comparisons to reduce the search space to 1.
For each eating speed middlemiddlemiddle, we traverse the array and calculate the overall time Koko spends, which takes O(n) for each traversal.
To sum up, the time complexity is O(n⋅log⁡m).

Space complexity: O(1)
For each eating speed middlemiddlemiddle, we iterate over the array and calculate the total hours Koko spends, which costs constant space.
Therefore, the overall space complexity is O(1)
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Initalize the left and right boundaries 
        int left = 1, right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            int middle = (left + right) / 2;
            int hourSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / middle);
            }

            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return right;
    }
}
