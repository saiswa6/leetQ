/*
Approach 3 : USing HashMap
Hasmap put() returns null when key does not exist earlier. If exists, returns the existing key's value.

Complexity Analysis :
time O(N)
space O(N), not as efficient as maintaining a K size of a set
*/

boolean containsNearbyDuplicate(int[] nums, int k) {
  if (nums == null || nums.length < 2) 
    return false;
  Map<Integer, Integer> indices = new HashMap<>();
  for (int i = 0; i < nums.length; i++) {
    Integer lastIndex = indices.put(nums[i], i);
    if (lastIndex != null && (i - lastIndex) <= k)
      return true;
  }
  return false;
}

//2nd solution
/*
for each n
    if exists in map, check difference
    if not exists, put n and index in map */

public boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
        if (map.containsKey(nums[i])) {
            if (Math.abs(i - map.get(nums[i])) <= k) 
              return true;
        }
        map.put(nums[i], i);
    }
    return false;
}
