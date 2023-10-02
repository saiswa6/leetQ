class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);

        int len = nums.length;
        int modulus = 1_000_000_007;
        int ans = 0;
        int [] power  = new int[len];
        power[0] = 1;
        for(int i=1;i<len;i++)
        {
            power[i] = (power[i-1] *2) % modulus;
        }

            int start = 0;
            int end = len-1;
            
            while(start <= end)
            {
                if((nums[start] + nums[end]) <= target)
                {
                    ans += power[end - start];
                    ans %= modulus;
                    start++;
                }
                else
                {
                    end--;
                }
            }

        return ans;
    }
}