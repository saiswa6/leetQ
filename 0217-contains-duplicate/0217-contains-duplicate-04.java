/*
Approach 4: Hash Map
Intuition:
The hash map approach is similar to the hash set approach but also keeps track of the count of occurrences for each element. It uses a hash map to store the elements as keys and their counts as values. If a duplicate element is encountered (count greater than or equal to 1), it returns true. This approach provides more information than just the presence of duplicates and has a time complexity of O(n).
*/

//MY SOLUTION
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            if(map.containsKey(nums[i]))
            {
                return true;
                /*int occurence = map.get(nums[i]) + 1; 
                map.put(nums[i], occurence);
                if(occurence>1)
                {
                    return true;
                }*/
            }
            else
            {
                map.put(nums[i], 1);
            }
            
        }
        return false;
    }
}
