/*
Approach 2: HashMap & HashSet
Intuition
If we have the frequencies of all elements, we can put them in a hash set. If the size of the hash set is equal to the number of elements, it implies that the frequencies are unique. 
Hence, we will find the frequencies of all elements in a hash map and then put them in a hash set.
*/

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        // Store the frequency of elements in the unordered map.
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // Store the frequency count of elements in the unordered set.
        Set<Integer> freqSet = new HashSet<>(freq.values());
        
        // If the set size is equal to the map size, 
        // It implies frequency counts are unique.
        return freq.size() == freqSet.size();
    }
}

/*
Complexity Analysis
Here, Nis the size of array arr.
Time complexity: O(N)
- We iterate over the array arr to find the frequency and store them in the hash map freq. Then, we insert these frequencies in the hash set freqSet, which has the insertion complexity of O(1).
Hence, the total time complexity is equal to O(N)

Space complexity: O(N)

We are storing the N frequencies in the hash map freq that takes O(1) space for each element. 
We also store the frequency count in the hash set. Therefore, the total space complexity is equal to O(N)
*/
