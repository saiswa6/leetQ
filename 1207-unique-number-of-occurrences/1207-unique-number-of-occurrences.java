class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1); // Insert to HashMap
        }
        Set<Integer> set = new HashSet<>(hashMap.values()); // Copy values() to set.

        return set.size() == hashMap.size(); // compare size.
    }
}