/*
I was super confused why many solutions had a final check for a special case. This is in-place, and doesn't have a weird final loop to deal with any special cases.
*/

def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
	a, b, write_index = m-1, n-1, m + n - 1

	while b >= 0:
		if a >= 0 and nums1[a] > nums2[b]:
			nums1[write_index] = nums1[a]
			a -= 1
		else:
			nums1[write_index] = nums2[b]
			b -= 1

		write_index -= 1



  //2nd similar soln
  class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1 , p2 = n-1 ,i = m+n-1;
        while(p2 >=0 ){
            if(p1 >=0 && nums1[p1] > nums2[p2]){
                nums1[i--] = nums1[p1--];
            } 
            else{
                nums1[i--] = nums2[p2--];
            }
        }
       }
}

//3rd

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //variables to work as pointers
        int i=m-1; // will point at m-1 index of nums1 array
        int j=n-1; // will point at n-1 index of nums2 array
        int k=nums1.length-1; //will point at the last position of the nums1 array

        // Now traversing the nums2 array
        while(j>=0){
            // If element at i index of nums1 > element at j index of nums2
            // then it is largest among two arrays and will be stored at k position of nums1
            // using i>=0 to make sure we have elements to compare in nums1 array
            if(i>=0 && nums1[i]>nums2[j]){
                nums1[k]=nums1[i];
                k--; 
                i--; //updating pointer for further comparisons
            }else{
                // element at j index of nums2 array is greater than the element at i index of nums1 array 
                // or there is no element left to compare with the nums1 array 
                // and we just have to push the elements of nums2 array in the nums1 array.
                nums1[k] = nums2[j];
                k--; 
                j--; //updating pointer for further comparisons
            }
        }
    }
}
