/*

*/

// 0ms
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] temp = new int[1001];
        for (int i : nums1) {
            temp[i] = 1;
        }
        int count = 0;
        for (int i : nums2) {
            if (temp[i] == 1) {
                count++;
                temp[i] = 2;
            }
        }
        int arr[] = new int[count];
        int j = 0;
        for (int i : nums2) {
            if (temp[i] == 2) {
                temp[i] = 1;
                arr[j] = i;
                j++;
            }
        }
        return arr;
    }
}
///////////////////////////////////
// 1ms
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int temp[] = new int[1001];
        for(int i = 0; i<nums1.length; i++){
            temp[nums1[i]]=1;
        }
        int count = 0;
        for(int i = 0; i<nums2.length; i++){
            if(temp[nums2[i]] == 1){
                temp[nums2[i]]=2;
                count++;
            }
        }
        int result[] = new int[count];
        int j= 0; 
        for(int i = 0; i<temp.length; i++){
            if(temp[i]==2){
                result[j] =i; 
                j++;
            }
        }
        return result;
    }
}

//// 2ms
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) 
    {
       HashSet<Integer> s1=new HashSet<>();
       HashSet<Integer> s2=new HashSet<>();
       for(int i:nums1)
       {
           s1.add(i);
       }
       for(int i:nums2)
       {
           if(s1.contains(i))
           {
               s2.add(i);

           }
       }
       int[] res=new int[s2.size()];
       int i=0;
       for(int n:s2)
       {
           res[i]=n;
           i++;
       }
       return res;
    }
}
//// 3ms
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] ar1 = nums1, ar2 = nums2;
        List<Integer> res = new ArrayList<>();
        int ans;
        if(nums1.length>nums2.length) {
            ar1 = nums2;
            ar2 = nums1;
        }
        for(int i=0;i<ar1.length;i++) {
            if(i==0 || ar1[i]!=ar1[i-1]) {
                ans = search(ar2, ar1[i]);
                if(ans!=-1) res.add(ans);
            }
        }
        int[] intersect = new int[res.size()];
        for(int i=0;i<res.size();i++) {
            intersect[i] = res.get(i);
        }
        return intersect;
    }
    public int search(int[] arr, int element) {
        int start = 0;
        int end = arr.length-1;
        while(start<=end) {
            int mid = start+(end-start)/2;
            if(arr[mid]==element) return element;
            if(arr[mid]<element) start = mid+1;
            else end = mid-1;
        }
        return -1;
    }
}
