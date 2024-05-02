/*
Approach 1: Counting Sort
Intuition
- We are given an integer array, and we need to check if the frequency of each element in the array is unique or not.
- We can make use of the fact that the integers in the array will always be in the range [-1000, 1000]. This range is of length 2000, and therefore we need an array of the same size to store the frequency of each element.
- Counting sort is a sorting technique that is based on the keys between specific ranges.
  In this technique, we perform sorting by counting objects having distinct key values like hashing.
- Now we have the frequencies of all elements of array arr in an array of size 2000, and we need to check if all non-zero elements in this array are unique or not (i.e. all frequencies are unique or not). To check this, we will sort the array and then can check if any two consecutive elements are the same or not.

Note: The array arr's elements could be negative as well. Hence we will add K = 1000 to each element to make it non-negative.
*/
class Solution {
    // Constant to make elements non-negative.
    final int K = 1000;
    
    public boolean uniqueOccurrences(int[] arr) {
        int freq[] = new int[2 * K + 1];
      
        // Store the frequency of elements in the unordered map.
        for (int num : arr) {
            freq[num + K]++;
        }
        
        // Sort the frequency count.
        Arrays.sort(freq);
        
        // If the adjacent freq count is equal, then the freq count isn't unique.
        for (int i = 0; i < 2 * K; i++) {
            if (freq[i] != 0 && freq[i] == freq[i + 1]) {
                return false;
            }
        }
        
        // If all the elements are traversed, it implies frequency counts are unique.
        return true;
    }
}

/*
Complexity Analysis
Here, N is the size of array arr, and KKK is equal to 1000.
- Time complexity: O(N+Klog⁡K)
  We first iterate over the array arr to store the frequency in the array freq. This takes O(N) time. 
  Then we sort the array freq that has a size of 2K = 2000. Hence it takes O(2Klog⁡2K) time that can be simplified to O(Klog⁡K)). 
  In the end, we iterate over the array freq to check duplicate values, and this takes O(2K) time. Therefore the total time complexity is equal to O(N+Klog⁡K)

Space complexity: O(K)
The only space required is the frequency array freq of size 2K to store the frequency of all the elements. Thus, the total space complexity is equal to O(K)
*/
