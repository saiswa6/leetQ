// Link : https://leetcode.com/problems/max-consecutive-ones-ii/solutions/96920/java-clean-solution-easily-extensible-to-flipping-k-zero-and-follow-up-handled/

/*
The idea is to keep a window [l, h] that contains at most k zero

The following solution does not handle follow-up, because nums[l] will need to access previous input stream
Time: O(n) Space: O(1)
*/

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, zero = 0, k = 1; // flip at most k zero
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)                                           
                zero++;
            while (zero > k)
                if (nums[l++] == 0)
                    zero--;                                     
            max = Math.max(max, h - l + 1);
        }                                                               
        return max;             
    }

/*
Now let's deal with follow-up, we need to store up to k indexes of zero within the window [l, h] so that we know where to move l next when the window contains more than k zero. If the input stream is infinite, then the output could be extremely large because there could be super long consecutive ones. In that case we can use BigInteger for all indexes. For simplicity, here we will use int
Time: O(n) Space: O(k)
*/

    public int findMaxConsecutiveOnes(int[] nums) {                 
        int max = 0, k = 1; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>(); 
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)                                   
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;                     
    }


/*
Note that setting k = 0 will give a solution to the earlier version Max Consecutive Ones

For k = 1 we can apply the same idea to simplify the solution. Here q stores the index of zero within the window [l, h] so its role is similar to Queue in the above solution
*/

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, q = -1;
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0) {
                l = q + 1;
                q = h;
            }
            max = Math.max(max, h - l + 1);
        }                                                               
        return max;             
    }
