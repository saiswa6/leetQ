public int removeDuplicates(int[] nums) {
    int i = nums.length > 0 ? 1 : 0; // when no elements,return ans as zero , otherwise start from i from 1.
    for (int n : nums)
        if (n > nums[i-1]) // compare current element with previous element
            nums[i++] = n;
    return i;
}
