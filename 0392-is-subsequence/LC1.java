/*
Approach 1: Divide and Conquer with Greedy
Let us start from the first characters of each string, i.e. source[0], target[0]. By comparing them, there could be two cases as follows:
Case 1): they are identical, i.e. source[0]=target[0]
- In this case, the best strategy would be to cross out the first characters in both strings, and then continue with the matching job.
- By the above action, we reduce the input into a smaller scale.
- It boils down to determine if the rest source string (i.e. source[1:]) is a subsequence of the rest of target string (i.e. target[1:]), which we could summarize in the following recursive formula:
  isSubsequence(source, target)=isSubsequence(source[1:], target[1:])

Case 2): they are not identical, i.e. source[0]≠target[0]
In this case, the only thing we can do is to skip (cross out) the first character in the target string, and keep on searching in the target string in the hope that we would 
find a letter that could match the first character in the source string.

Now, it boils down to determine if the source string could be a subsequence for the rest of the target string, which we summarize as follows:
isSubsequence(source, target)=isSubsequence(source, target[1:])
*/

class Solution {
    String source, target;
    Integer leftBound, rightBound;

    private boolean rec_isSubsequence(int leftIndex, int rightIndex) {
        // base cases
        if (leftIndex == leftBound)
            return true;
        if (rightIndex == rightBound)
            return false;

        // consume both strings or just the target string
        if (source.charAt(leftIndex) == target.charAt(rightIndex))
            ++leftIndex;
        ++rightIndex;

        return rec_isSubsequence(leftIndex, rightIndex);
    }

    public boolean isSubsequence(String s, String t) {
        this.source = s;
        this.target = t;
        this.leftBound = s.length();
        this.rightBound = t.length();

        return rec_isSubsequence(0, 0);
    }
}

/*
Complexity Analysis
Let ∣S∣ be the length of the source string, and ∣T∣|T|∣T∣ be the length of the target string.
Time Complexity: O(∣T∣)
Space Complexity: O(∣T∣)

*/
