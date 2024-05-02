class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> answer = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        List<Integer> list1 = new ArrayList<>();
        for (int value : set1) {
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