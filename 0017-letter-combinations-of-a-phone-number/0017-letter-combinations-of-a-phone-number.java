class Solution {
    Map<Character, String> keyPadMap = Map.of('2',"abc",'3',"def",'4',"ghi",'5',"jkl",'6',"mno",'7',"pqrs",'8',"tuv",'9',"wxyz");
    public List<String> letterCombinations(String digits) {
        List<String> answer = new ArrayList<>();
        if(digits.isEmpty()) {
            return answer;
        }
        
        produceCombo("", digits, answer);
        return answer;
    }

    void produceCombo(String processed, String digits, List<String> answer) {
        if(digits.isEmpty()) {
            answer.add(processed);
            return;
        }

        Character ch = digits.charAt(0);
        String keys = keyPadMap.get(ch);
        for(int i = 0; i < keys.length(); i++){
            produceCombo(processed+keys.charAt(i), digits.substring(1), answer);
        }
    }
}