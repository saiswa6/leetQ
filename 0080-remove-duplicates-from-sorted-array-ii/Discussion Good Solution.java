/*Let us use two pointers approach here: slow pointer and fast pointer, where slow will always be less or equal to fast. We are asked to remove duplicates only if we have more 2 of them, so we start with slow and fast equal to 2.
Then we iterate through our data and check if nums[slow - 2] == nums[fast]: what does it mean? It means, that in fact nums[slow-2] = ... = nums[fast] and that we already have group of 3 or more equal elements: it this case we understand, that slow place should be rewritten with something else from future, so we do not move it. In other case, we have group of 2 or smaller, so we need to move slow pointer to right. In any case we move fast pointer one place to the right.

Complexity: time complexity is O(n), we move our two pointers only in one direction. Space complexity is O(1): we do it in-place as asked.

Note also, that this can be easily adjacted if you asked to have not 2 duplicates, but k: in this case we just need to change all 3 occurences of 2 to k.*/


   public int removeDuplicates(int[] nums) {
        int slow=2, fast = 2;
        while (fast < nums.length){
            if (nums[slow - 2] != nums[fast]){
                nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow;
    }
One more easy solution that comes to my mind after attempting above solution

public int removeDuplicates(int[] nums) {
    int i = 0;
    for (int n : nums)
        if (i < 2 || n > nums[i-2]) nums[i++] = n;
    return i;
}

//If question is to put k elements in order, then replace 2 with k.
