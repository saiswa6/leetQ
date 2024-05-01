/*
1st Approach : Brute force O(N2)

2nd Approach :
Using prefix and suffix array.
Time : O(N)
Time : O(N)


*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int prefix[] = new int[length];
        int suffix[] = new int[length];
        int result[] = new int[length];

        int prefixProduct = 1;
        prefix[0] = prefixProduct;
        for (int i = 1; i < length; i++) {
            prefix[i] = prefixProduct * nums[i - 1]; // Multiply current prefixProduct with previous nums element
            prefixProduct = prefix[i]; // Update prefixProduct for next iteration.
        }

        int suffixProduct = 1;
        suffix[length - 1] = suffixProduct;
        for (int i = length - 2; i >= 0; i--) {
            suffix[i] = suffixProduct * nums[i + 1]; // Multiply current suffixProduct with next nums element
            suffixProduct = suffix[i]; // Update suffixProduct for next iteration
        }

        for (int k = 0; k < length; k++) {
            result[k] = prefix[k] * suffix[k]; // Finally merge
        }

        return result;
    }
}
