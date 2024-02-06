/*
The idea evert letter in typed should be the same as name, but "shifted" (or spreaded to be more exact) to some number of positions.
That's why, we go through typed and trying to identify if we have a corrponding letter in name. At the same time, we calculate the difference in positions of these corresponding letters in typed and name. In other words, difference identicates how many "additional" letters contains typed. For example:
name: AABCD
typed: AAAABBCDDDDDD

At the beginning difference is 0.
We go through AAAABBCDDDDDD:

// Refer : https://leetcode.com/problems/long-pressed-name/solutions/184488/java-one-pass-solution-4ms-with-o-1-extra-space/
*/

class Solution {
 public boolean isLongPressedName(String name, String typed) {
  int difference = 0;
  for (int i = 0; i < typed.length();) {
	//letters are equal -> go next
   if (difference <= i && i - difference < name.length() && typed.charAt(i) == name.charAt(i - difference)) {
    i++;
   } 
	 // letters are not equal,  but we can link typed letter to name letter from the previous iteration
	 else if (difference < i && i - difference - 1 < name.length() && typed.charAt(i) == name.charAt(i - difference - 1)) {
    difference++;
   } else return false;
  }
	// check that at the end of name there's no odd symbols
    return typed.length() - difference == name.length();
 }
}
