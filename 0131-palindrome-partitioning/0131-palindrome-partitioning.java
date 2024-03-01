class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        producePalindrome(new ArrayList<>(), 0, s, answer);
        return answer;
    }

    void producePalindrome(List<String> processed, int index, String s, List<List<String>> answer) {
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