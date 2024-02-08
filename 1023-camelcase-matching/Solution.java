class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int qLength = queries.length;
        List<Boolean> answer = new ArrayList<>(qLength);

        for(int i = 0; i < qLength; i++) {
            boolean isMatch = match(queries[i].toCharArray(), pattern.toCharArray());
            answer.add(isMatch);
        }
        return answer;
    }

    private boolean match (char[] queryArr, char[] patternArr) {
        int j = 0;
        for(int i=0; i < queryArr.length; i++) {
            if(j < patternArr.length && queryArr[i] == patternArr[j]) {
                j++;
            } else if (Character.isUpperCase(queryArr[i])) {
                return false;
            }
        }

        return j == patternArr.length;
    }
}
