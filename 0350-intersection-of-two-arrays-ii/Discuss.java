/*
- What if nums1's size is small compared to nums2's size? Which algorithm is better?
    This one is a bit tricky. Let's say nums1 is K size. Then we should do binary search for every element in nums1. Each lookup is O(log N), and if we do K times, we have O(K log N).
    If K this is small enough, O(K log N) < O(max(N, M)). Otherwise, we have to use the previous two pointers method.
    let's say A = [1, 2, 2, 2, 2, 2, 2, 2, 1], B = [2, 2]. For each element in B, we start a binary search in A. To deal with duplicate entry, once you find an entry, all the duplicate element is around that that index, so you can do linear search scan afterward.
    
    Time complexity, O(K(logN) + N). Plus N is worst case scenario which you have to linear scan every element in A. But on average, that shouldn't be the case. so I'd say the Time complexity is O(K(logN) + c), c (constant) is number of linear scan you did.

- What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
    This one is open-ended. But Map-Reduce I believe is a good answer.

    /// Solution using Binary Search
    *********************************************************************************************
    For anyone struggling with applying binary search for duplicate solutions, this is the solution. I have created a custom binary search function so that I get the first index of the target I am searching. This is important for the duplicate case.

Lets talk about the 2 cases

1st case
eg. A = [84,84,90] and B= [78,84,92,95,98,101]
Now in the code, I use a startIndex while doing a binary search. This is important for this case.

if we are matching the first "84" from A with B,we will find the intersection at 1st index of B. Now for 2nd "84" from A, if we don't use the startIndex, It will again match at 1st index of B. So startIndex is index of B which is lastMatchedIndexofB + 1. It also helps in reducing search space.

2nd case Custom Binary Function
eg. A = [84,84,90] and B= [78,84,84,92,95,98,101]

lets say we are matching "84" from A with B. If we use Arrays.BinarySearch, there is no guarantee which index it will return. It can be either 1 or 2 in our case. Now lets say it matches 2nd index of B. Because of startIndex logic, we will end up only matching 1 "84" and hence we will get wrong result.

So in order to get the first index, the custom binary function is needed. Now we can move on to next "84" from A and match it accordingly using the startIndex with 3rd index from B.
*/

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> l = new ArrayList();
        int startIndex  = 0;
        for(int num:nums1){
            int idx = firstBinarySearch(nums2,startIndex,nums2.length,num);
            if(idx >= 0){
                l.add(num);
                startIndex = idx+1;
            }
        }
        return l.stream().mapToInt(k->k).toArray();
    }
    
    //start is included, end will be excluded
    public int firstBinarySearch(int[] arr, int start,int end,int target){
       int left = start,right = end;
        int startIndex = -1;
        while(left < right){
            int mid = left + (right-left)/2 ;
            if(arr[mid] == target){
                right = mid;
                startIndex = mid;
            }else if(arr[mid] > target){
                right = mid;
            }
            else{
                left = mid+1;
            }
        }
        
        return startIndex;
        
    }
}


//TC O(mlogn)
//SC O(min(m,n))
