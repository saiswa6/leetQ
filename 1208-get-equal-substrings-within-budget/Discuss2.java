/*
Easy PrefSum + BinarySearch O(NLogN)

Intuition
The intution came to me when i was solving the question i saw i needed to find a substring sum which leads me to this size or less.

Approach
Made a prefix sum to calculate sum/change cost in O(1)
Then i searched substring of size k which satisfy the value and then if it satisfied i searched for higher values otherwise lesser size.

Complexity
Time complexity:O(NLogN)

Space complexity:O(N)
*/

class Solution {
public:
    bool check(vector<int>& pref , int cost , int mid){
        int l = 0 , r = mid;
        int flag = false;
        while(r < pref.size()){
            int sum = pref[r] - pref[l];
            if(sum <= cost){
                flag = true;
                break;
            }
            l++;
            r++;
        }
        return flag;
    }
    int equalSubstring(string s, string t, int cost) {
        int n = s.size();
        vector<int> pref(n + 1 , 0);
        for(int i = 1 ; i <= n ; i++){
            pref[i] = pref[i-1] + abs((s[i - 1] - 'a') - (t[i - 1] - 'a'));
        }
        int l = 0 , h = s.size()+1;
        int ans = 0;
        while(l <= h){
            int mid = l + (h - l)/2;
            if(check(pref , cost , mid)){
                ans = mid ;
                l = mid + 1;
            }
            else{
                h = mid - 1;
            }
        }
        return ans;
    }
};
