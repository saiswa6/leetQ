class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int potionsLength = potions.length;
        int result[] = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int a = isSuccess(spells[i], potions, success);
            if (a != -1) {
                result[i] = potionsLength - a;
            } else {
                result[i] = 0;
            }
        }

        return result;
    }

    public int isSuccess(int number, int potions[], long success) {
        int start = 0;
        int end = potions.length - 1;
        int answer = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (number * potions[mid] >= success) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }
}