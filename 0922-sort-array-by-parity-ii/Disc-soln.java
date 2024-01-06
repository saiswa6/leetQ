// (A[i] & 1) == 0   -- even

//Good and similar of my solution

class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1, n = A.length;
        while (i < n && j < n) {
            while (i < n && A[i] % 2 == 0) {
                i += 2;
            }
            while (j < n && A[j] % 2 == 1) {
                j += 2;
            }
            if (i < n && j < n) {
                swap(A, i, j);
            }
        }
        return A;
    }
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

////////////////////////////////////////
class Solution {
public:
    vector<int> sortArrayByParityII(vector<int>& nums) {
        int n = nums.size();
        int i = 0, j = n-1;
        //i stands for even
        //j stands for odd
        while(i < n && j >= 0) {
            if   (nums[i]%2 == 0)    i += 2;
            else if(nums[j] %2 == 1) j -= 2;
            else                     swap(nums[i], nums[j]);
        }
        return nums;
    }
};

///////////////////////////////////////////
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int o = 1, e = 0, n = nums.length;

        while(o < n && e < n) {
            if(nums[e] % 2 == 0) e += 2;
            else if(nums[o] % 2 == 1) o += 2;
            else {
                int temp = nums[o];
                nums[o] = nums[e];
                nums[e] = temp;
            }
        }

        return nums;
    }
}
