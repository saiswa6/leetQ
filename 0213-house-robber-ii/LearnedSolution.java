/*
Modification to Article DP5’s Approach
- We were finding the maximum sum of non-adjacent elements in the previous questions. For a circular street, the first and last house are adjacent, therefore one thing we know for sure is that the answer will not consider the first and last element simultaneously (as they are adjacent).
- Now building on the article DP5, we can say that maybe the last element is not considered in the answer. In that case, we can consider the first element. Let’s call this answer ans1. Hence we have reduced our array(arr- last element), say arr1, and found ans1 on it by using the article DP5 approach.
- Now, it can also happen that the final answer does consider the last element. If we consider the last element, we can’t consider the first element( again adjacent elements). We again use the same approach on our reduced array( arr - first element), say arr2. Let’s call the answer we get as ans2.
- Now, the final answer can be either ans1 or ans2. As we have to return the maximum money robbed by the robber, we will return max(ans1, ans2) as our final answer.


Approach:
The approach to solving this problem can be summarized as:
- Make two reduced arrays - arr1(arr-last element) and arr2(arr-first element).
- Find the maximum of non-adjacent elements as mentioned in article DP5 on arr1 and arr2 separately. Let’s call the answers we got ans1 and ans2 respectively.
- Return max(ans1, ans2) as our final answer.
*/
//Striver Link : https://takeuforward.org/data-structure/dynamic-programming-house-robber-dp-6/  Very clear
// My Solution
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != 0) {
                list1.add(nums[i]);
            }
            if (i != n - 1) {
                list2.add(nums[i]);
            }
        }

        return Math.max(robSolver(list1), robSolver(list2));
    }

    private int robSolver(List<Integer> list) {
        int len = list.size();

        int prev = list.get(0);
        int prev2 = 0;
        int current = 0;

        for (int i = 1; i < len; i++) {
            int take = list.get(i);

            take = take + prev2;
            int nonTake = 0 + prev;
            current = Math.max(take, nonTake);
            prev2 = prev;
            prev = current;
        }

        return prev;
    }
}


// Striver Solution
import java.util.*;
class TUF{
static long solve(ArrayList<Integer> arr){
    int n = arr.size();
    long prev = arr.get(0);
    long prev2 =0;
    
    for(int i=1; i<n; i++){
        long pick = arr.get(i);
        if(i>1)
            pick += prev2;
        long nonPick = 0 + prev;
        
        long cur_i = Math.max(pick, nonPick);
        prev2 = prev;
        prev= cur_i;
        
    }
    return prev;
}

static long robStreet(int n, ArrayList<Integer> arr){
    ArrayList<Integer> arr1=new ArrayList<>();
    ArrayList<Integer> arr2=new ArrayList<>();

    if(n==1)
       return arr.get(0);
    
    for(int i=0; i<n; i++){
        
        if(i!=0) arr1.add(arr.get(i));
        if(i!=n-1) arr2.add(arr.get(i));
    }
    
    long ans1 = solve(arr1);
    long ans2 = solve(arr2);
    
    return Math.max(ans1,ans2);
}


public static void main(String args[]) {

  ArrayList<Integer> arr=new ArrayList<>();
  arr.add(1);
  arr.add(5);
  arr.add(1);
  arr.add(2);
  arr.add(6);
  int n=arr.size();
  System.out.println(robStreet(n,arr));

}
}
/*
Time Complexity: O(N )
Reason: We are running a simple iterative loop, two times. Therefore total time complexity will be O(N) + O(N) ≈ O(N)

Space Complexity: O(1)
Reason: We are not using extra space.
*/
