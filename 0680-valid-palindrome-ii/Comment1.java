/*
Here is the solution I gave on my Meta phone screen and passed. Follow up question was asked to explain how I will extend this to work for n changes. My answer was to have a sub-function that compares left and right and calls recursively with a counter.
*/
class Solution:
    def validPalindrome(self, s: str) -> bool:
        
        def helper(l, r, mismatches): 
            while l <= r: 
                if s[l] != s[r]: # mismatch 
                    return mismatches > 0 and (helper(l, r-1, mismatches - 1) or helper(l+1, r, mismatches - 1))
                l += 1
                r -= 1
            return True 

        return helper(0, len(s) - 1, 1)
/*
Don't make the mistake of calling a substring function to iteratively check the newly shortened string. The substring operation is O(n). Instead, use index variables.
I kept getting time limit exceeded because I was using slicing in a helper function. This comment helped. Thank you. Two pointer keeps overall time complexity to O(N)
*/

/*
 avoid repeating code like this
*/
      def validPalindrome(self, s: str) -> bool:                
        def check(lo, hi, removed=False):
            while lo < hi:
                if s[lo] != s[hi]:
                    if removed:
                        return False
                    return check(lo+1, hi, True) or check(lo, hi-1, True)
                lo += 1
                hi -= 1
            return True
        return check(0, len(s)-1)
