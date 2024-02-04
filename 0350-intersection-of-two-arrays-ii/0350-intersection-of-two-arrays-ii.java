class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        Map<Integer, Integer> resultMap = new HashMap<>();
        int result [] = new int[length1];
        int index = 0;

        for(int i = 0; i < length1; i++) {
            if(!resultMap.containsKey(nums1[i])) {
                resultMap.put(nums1[i], 1);
            } else {
                resultMap.put(nums1[i], resultMap.get(nums1[i]) + 1);
            }
        }

        for(int j = 0; j < length2; j++) {
            if(resultMap.containsKey(nums2[j]) && resultMap.get(nums2[j]) != 0) {
                result[index++] = nums2[j];
                resultMap.put(nums2[j], resultMap.get(nums2[j]) - 1);
            }
        }
        return Arrays.copyOf(result, index);  
    }
}