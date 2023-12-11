/*
Approach 2: Two Pointers
Intuition
If you take any ordinary string, and concatenate its reverse to it, you'll get a palindrome. This leads to an interesting insight about the converse: every palindrome half is reverse of the other half.
Simply speaking, if one were to start in the middle of a palindrome, and traverse outwards, they'd encounter the same characters, in the exact same order, in both halves!

Algorithm
Since the input string contains characters that we need to ignore in our palindromic check, it becomes tedious to figure out the real middle point of our palindromic input.
Instead of going outwards from the middle, we could just go inwards towards the middle!
So, if we start traversing inwards, from both ends of the input string, we can expect to see the same characters, in the same order.

The resulting algorithm is simple:
Set two pointers, one at each end of the input string
If the input is palindromic, both the pointers should point to equivalent characters, at all times. 
- If this condition is not met at any point of time, we break and return early. 2
We can simply ignore non-alphanumeric characters by continuing to traverse further.
Continue traversing inwards until the pointers meet in the middle.

Complexity Analysis
Time complexity : O(n), in length nnn of the string. We traverse over each character at-most once, until the two pointers meet in the middle, or when we break and return early.
Space complexity : O(1). No extra space required, at all.

Footnotes
Such a property is formally known as a loop invariant. ↩
Such a property is often called a loop termination condition. It is one of several used in this solution. Can you identify the others? ↩
*/
// i -- left and j -- right

class Solution {
  public boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }

      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
        return false;
    }

    return true;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
   public boolean isPalindrome(String s) {
    int p = 0;
    int q = s.length() - 1;
    
    while(p <= q) {
        char c = s.charAt(p);
        char cb = s.charAt(q);
        
        if(!Character.isLetterOrDigit(c)) {
            p++;
            continue;
        }
        
        if(!Character.isLetterOrDigit(cb)) {
            q--;
            continue;
        }
        
        if(Character.toLowerCase(cb) != Character.toLowerCase(c)) return false;
        p++;
        q--;
    }
    return true;
    
}
