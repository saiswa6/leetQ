/*
Approach #3: Hash Table
Intuition
Keep a sliding window of k elements using Hash Table.

Algorithm
From the previous approaches, we know that even logarithmic performance in search is not enough.
In this case, we need a data structure supporting constant time search, delete and insert operations.
Hash Table is the answer. The algorithm and implementation are almost identical to Approach #2.

Loop through the array, for each element do
= Search current element in the HashTable, return true if found
= Put current element in the HashTable
= If the size of the HashTable is larger than k, remove the oldest item.
Return false

Complexity Analysis
Time complexity: O(n)).
We do n operations of search, delete and insert, each with constant time complexity.

Space complexity: O(min⁡(n,k)).
The extra space required depends on the number of items stored in the hash table, which is the size of the sliding window, min⁡(n,k).
*/

public boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; ++i) {
        if (set.contains(nums[i])) return true;
        set.add(nums[i]);
        if (set.size() > k) {
            set.remove(nums[i - k]);
        }
    }
    return false;
}


// MY SOLUTION
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i <= k && i < nums.length ;i++)
        {
            if(set.contains(nums[i]))
            {
                return true;
            }
            set.add(nums[i]);
        }

        int startIndex = 0;
        for(int i = k + 1;  i < nums.length ; i++)
        {
            set.remove(nums[startIndex]);
            if(set.contains(nums[i]))
            {
                return true;
            }
            set.add(nums[i]);
            startIndex++;
        }
        return false;
    }
}
