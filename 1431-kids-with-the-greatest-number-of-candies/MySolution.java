/*
1st SOlution :
For every index , check the highest number of candies by traversing all the elements and add extraCandies to current ith child. Check equal to greater than or equal to highest number of candies.
So, this takes O(N2) time.

2nd Solution :
Create a prefix(left to right) and postfix array(right to left). Compare the hghest from both arrays(combined highes for evry index). The one iteration is enough to find
whether true/false.
Time : O(N)
Space : O(N)

3rd Solution :
Felt actually highest will be same for all indexes of the array. SO, calc highest first. Compare each index by adding extraCandies with highestNumber
Time : O(N)
Space : O(1)
*/


class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int highestNumber = 0;
        int length = candies.length;
        List<Boolean> result = new ArrayList<>(length);

        for (int i : candies) {
            highestNumber = Math.max(highestNumber, i);
        }

        for (int i = 0; i < length; i++) {
            if (candies[i] + extraCandies >= highestNumber) {
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
    }
}