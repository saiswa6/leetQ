class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        producePalindrome(new ArrayList<>(), 0, s, answer);
        return answer;
    }

    private void producePalindrome(List<String> processed, int index, String s, List<List<String>> answer) {
        if(index == s.length()) {
            answer.add(new ArrayList<>(processed));
            return;
        }

        for(int i = index; i < s.length() ; i++) {
            if(isPalindrome(s, index, i)) {
                processed.add(s.substring(index, i + 1));
                producePalindrome(processed, i + 1, s, answer);
                processed.remove(processed.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;end--;
        }
        return true;
    }
}

// Direct pattern Solution
//https://leetcode.com/problems/permutations/solutions/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning/
public List<List<String>> partition(String s) {
   List<List<String>> list = new ArrayList<>();
   backtrack(list, new ArrayList<>(), s, 0);
   return list;
}

public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
   if(start == s.length())
      list.add(new ArrayList<>(tempList));
   else{
      for(int i = start; i < s.length(); i++){
         if(isPalindrome(s, start, i)){
            tempList.add(s.substring(start, i + 1));
            backtrack(list, tempList, s, i + 1);
            tempList.remove(tempList.size() - 1);
         }
      }
   }
}

public boolean isPalindrome(String s, int low, int high){
   while(low < high)
      if(s.charAt(low++) != s.charAt(high--)) return false;
   return true;
} 
