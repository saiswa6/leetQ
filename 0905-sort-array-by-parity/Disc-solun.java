public int[] sortArrayByParity(int[] A) {  - Do swaps whenever needed.
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            if(A[i] % 2 == 0) {
                // Even first
                i++;
            }
            else {
                if(A[j] % 2 != 0) {
                    // Both odd
                    j--;
                }
                if (A[j] % 2 == 0) {
                    // Odd, Even
                    swap(A, i, j);
                    i++;
                    j--;
                }
            }
        }


        return A;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
//////////////////////////////////////////////////////////////////////////////////////

var sortArrayByParity = function(nums) {  // Variation of my solution
    let left = 0, right = nums.length - 1;    
    while (left < right) {
        if (nums[left] % 2 === 0) {
            ++left;
        } else if (nums[right] % 2 === 1) {
            --right;
        } else {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            ++left;
            --right;
        }
    }
    
    return nums;
};
//////////////////////////////////////////////////////////////////////////////////////
//In Place - but not efficirnt when all elements are even
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int lastEvenIndex = 0;
        for (int i=0; i< A.length; i++) {
            if (A[i] % 2 == 0) {
                int tmp = A[lastEvenIndex];
                A[lastEvenIndex] = A[i];
                A[i] = tmp;
                lastEvenIndex++ ;
            }
        }  
        return A;
    }
}


    public int[] sortArrayByParity(int[] A) {
        for (int i = 0, j = 0; j < A.length; j++)
            if (A[j] % 2 == 0) {
                int tmp = A[i];
                A[i++] = A[j];
                A[j] = tmp;;
            }
        return A;
    }

