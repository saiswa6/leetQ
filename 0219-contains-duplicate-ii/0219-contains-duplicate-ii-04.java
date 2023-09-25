/*
Approach #4: Hash Table
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
== Hash Set add() return false if the same element already exists within HashSet. returns true if it's a new element insertion.
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

//DIFFRENT APPROACH - 1
Explanation: It iterates over the array using a sliding window. The front of the window is at i, the rear of the window is k steps back. The elements within that window are maintained using a Set. While adding new element to the set, if add() returns false, it means the element already exists in the set. At that point, we return true. If the control reaches out of for loop, it means that inner return true never executed, meaning no such duplicate element was found.
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]); //remove element if its distance to nums[i] is not lesser than k
            if(!set.add(nums[i])) return true; //because all still existed elements is closer than k distance to the num[i], therefore if the add() return false, it means there's a same value element already existed within the distance k, therefore return true.
        }
        return false;
 }
