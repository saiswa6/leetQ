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