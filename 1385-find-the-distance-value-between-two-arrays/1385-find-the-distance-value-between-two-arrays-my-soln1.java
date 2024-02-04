/*
Understand Question

I spent a lot of time testing different implementations and trying to deal with errors, before I realized that the question itself isn't properly phrased. The example too is opaque unless you already know the answer.
You're supposed to count the elements in arr1 that do NOT yield a value <= d when any element from arr2 is subtracted from it. To explain the example;

arr1[0] = 4 doesn't violate the condition. Hence, we count it.
arr1[1] = 5 also doesn't violate the condition. We also count it, bringing our count to two.
arr1[8] = 8 violates the condition, so it isn't counted.

Since that's the end of arr1, the total number of GOOD numbers we have is 2. That's why the output is 2

- We are returning the number of elements in arr1 where all of its distances are MORE THAN than d.
- Calculate the distance between arr1[i] and arr2[j] and find the number of elements in arr1 where it's distances are NOT LESS THAN or EQUAL TO d.
*/



class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int answer = 0;
        for(int i = 0; i< arr1.length; i++) { // Iterate theough each element of arr1 and find whether any arr1 element satisfies |arr1[i] - arr2[j]| <= d. If no, increment answer variable.
            int lowerBound = arr1[i] - d; // |arr1[i] - arr2[j]| <= d means arr2[j] = arr1[i]-d and  arr2[j] = arr1[i]+d will satisfy the condition.
            int upperBound = arr1[i] + d;
            boolean conditionSatisfy = false;;
            for(int j = 0; j < arr2.length; j++) {
                if(lowerBound <= arr2[j] && arr2[j] <= upperBound) { // Use this boundaries to increment answer or not.
                    conditionSatisfy = true;
                }
            }
            if(!conditionSatisfy) {
                answer++;
            }
            
        }

        return answer;
    }
}

// Better version of above solution

class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        int size1= arr1.size(), size2= arr2.size();
        int res = size1;
        for(int i = 0; i < size1; i ++) {
            for(int j = 0; j < size2; j ++) {
                if(abs(arr1[i] - arr2[j]) <= d) {
                    res --;
                    break;     // break when one conditon satisfy, reduce no of iterations
                }
            }
        }
        return res;
    }
};

// 3 rd similar solution
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        for(int num: arr1) {
            if(check(arr2, num, d)) {
                count ++;
            }
        }
        return count;
    }

    public boolean check(int[] arr2, int num, int d) {
        for(int n: arr2) {
            if(Math.abs(n - num) <= d) {
                return false;
            }
        }
        return true;
    }
}
