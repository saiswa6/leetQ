/*
Approach 3: Using Hashmap - Single Pass
In this approach, the hashmap would only hold those array elements for which we have not yet found a suitable pair so far with sum equal to k. As and when the elements are paired up, we remove them from the map.

*/
class Solution {
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int complement = k - current;
            if (map.getOrDefault(complement, 0) > 0) {
                // remove complement from the map
                map.put(complement, map.get(complement) - 1);
                count++;
            } else {
                 // add current element in the map
                map.put(current, map.getOrDefault(current, 0) + 1);
            }
        }
        return count;
    }
}

/*
Complexity Analysis

Time Complexity : O(n), where n is the length of array nums. We iterate over every element only once. 
Besides, checking or updating the value of a particular key element in the hashmap takes constant time. This gives us total time complexity as O(n)

Space Complexity: O(n), where n is the length of array nums. We use an unordered map to store the values of the array with their count of occurrence. 
In the worst case, if we do not find a complement pair of any current element, we would end up adding all the elements in the map and the maximum size of the map would grow up to nnn.
*/
