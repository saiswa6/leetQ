/*
Intuition
- First, we make the following observation. Consider a group t of consecutive repeating characters. The length of compressed t is less than or equal to the length of t. For example, d tranforms into d, cc into c2, aaaa into a4, bbbbbbbbbbbb into b12.
- This observation allows processing groups in the array chars from left to right.
- In the slideshow above, we compress the array chars = ["c","c","b","a","a","a","a","a","a","a","a","a","a"]. First, we process the group cc, then b, and finally aaaaaaaaaa.

Algorithm
1. Declare the variables i – the first index of the current group, and res – the length of the answer (of the compressed string). Initialize i = 0, res = 0.
2. While i is less than the length of chars:
 -> Find the length of the current group of consecutive repeating characters groupLength.
 -> Add chars[i] to the answer (chars[res++] = chars[i]).
 -> If groupLength > 1, add the string representation of groupLength to the answer and increase res accordingly.
 -> Increase i by groupLength and proceed to the next group.
3. Return res.

Complexity Analysis
Let n be the length of chars.
Time complexity: O(n).
All cells are initially white. We will repaint each white cell blue, and we may repaint some blue cells green. Thus each cell will be repainted at most twice. Since there are n cells, the total number of repaintings is O(n).

Space complexity: O(1).
We store only a few integer variables and the string representation of groupLength which takes up O(1) space.
*/

class Solution {
    public int compress(char[] chars) {
        int i = 0, res = 0;
        while (i < chars.length) {
            int groupLength = 1;
            while (i + groupLength < chars.length && chars[i + groupLength] == chars[i]) {
                groupLength++;
            }
            chars[res++] = chars[i];
            if (groupLength > 1) {
                for (char c : Integer.toString(groupLength).toCharArray()) {
                    chars[res++] = c;
                }
            }
            i += groupLength;
        }
        return res;
    }
}
