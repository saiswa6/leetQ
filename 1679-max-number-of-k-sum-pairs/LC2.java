/*
Approach 2: Using Hashmap - Two Pass
Hint: We can try to find if the Complement of current element x with respect to k is present in the array or not in constant time.
*/

class Solution {
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        // build the hashmap with count of occurence of every element in array
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int complement = k - nums[i];
            if (map.getOrDefault(current, 0) > 0
                    && map.getOrDefault(complement, 0) > 0) {
                if ((current == complement) && map.get(current) < 2)
                    continue;
                map.put(current, map.get(current) - 1);
                map.put(complement, map.get(complement) - 1);
                count++;
            }
        }
        return count;
    }
}

/*
Complexity Analysis
Time Complexity : O(n), where n is the length of array nums. We iterate over an element in the array twice which takes O(n) time. 
First, to build a map from the array. Second, to find a pair for every element in the array. Also, to add or update an element in a hashmap takes constant time. This gives us total time complexity as O(n)\mathcal{O}(n)O(n).

Space Complexity: O(n), where nnn is the length of array nums. We use an unordered map to store the values of the array with their count of occurrence.
In the worst case, if every element in the array is unique, the maximum size of the map would grow up to nnn.
*/
