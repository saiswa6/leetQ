/*
BINARY SEARCH
T.C : O(nlogn)

Different approach, consider 2 pointers for better complexity
We iterate through the entire array and initialise the high and low value
We initialise the mid value if low index is lesser than the higher one or vice-versa
Then we check whether array[i]+arr[mid] == target or not
In case of duplicated answer, we search only on the right of the left element.

Complexity
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
         for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1;
	    int high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] +numbers[i] == target)
                    return new int[]{i + 1, mid + 1};
                else if (numbers[mid] + numbers[i] > target) 
                    high = mid - 1;
                else 
                    low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }
}
