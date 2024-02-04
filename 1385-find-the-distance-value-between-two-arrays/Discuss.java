class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
        arr1.sort()
        arr2.sort()
        ans = i = j = 0
        while i < len(arr1) and j < len(arr2): 
            if arr1[i] <= arr2[j] + d: 
                if arr1[i] < arr2[j] - d: ans += 1
                i += 1
            else: j += 1
        return ans + len(arr1) - i




      ////////////
  class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int res = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        for(int n : arr2)
            ts.add(n);
        
        for(int n : arr1){
            Integer higher = ts.ceiling(n);
            Integer lower = ts.floor(n);
            int diff = 0;
            if(higher == null){
                diff = Math.abs(lower - n);
            }else if(lower == null){
                diff = Math.abs(higher - n);
            }else{
                diff = Math.min(higher - n, n - lower);
            }
            if(diff > d)
                res++;
        }
        return res;
    }
}

////////////////////////////
///Concise Java with O(mlog(n)) Time
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        TreeSet<Integer> tree = new TreeSet<>();
        for(int num: arr2){
            tree.add(num);
        }
        int distance = 0;
        for(int i = 0; i < arr1.length; i++){
           int left = arr1[i] - d;
           int right = arr1[i] + d;
           Set<Integer> set = tree.subSet(left, right+1);
           if(set.isEmpty()){
             distance++;
           }
        }
      return distance;
    }
}
