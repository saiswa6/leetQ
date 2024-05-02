// Sets

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> answer = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>(); // hashset for nums1 
        Set<Integer> set2 = new HashSet<>(); // hashset for nums2

        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);  // Add elements to hashSet
        }

        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        List<Integer> list1 = new ArrayList<>();
        for (int value : set1) {  // Iterate each set and check in otherset.
            if (!set2.contains(value)) {
                list1.add(value);
            }
        }

        List<Integer> list2 = new ArrayList<>();
        for (int value2 : set2) {
            if (!set1.contains(value2)) {
                list2.add(value2);
            }
        }

        answer.add(list1);
        answer.add(list2);
        return answer;
    }
}
