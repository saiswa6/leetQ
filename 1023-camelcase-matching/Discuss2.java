/*
Solution 2: Use Regax
- Join "[a-z]*" into pattern characters.
- According to the problem description, insert [a-z]* before & after each char in pattern, then use regular expression to match the new pattern.


Slow compared with solution 1.
Regex solutions are very slow compared to a tailor-made solution in these kind of questions.
*/

// 1st direct solution
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        String newPattern = "[a-z]*" + String.join("[a-z]*", pattern.split("")) + "[a-z]*";
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (String q : queries) { ans.add(q.matches(newPattern)); }
        return ans;
    }
//Using java 8 stream, the above can be rewritten in 2 lines:
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        String newPattern = "[a-z]*" + String.join("[a-z]*", pattern.split("")) + "[a-z]*";
        return Arrays.stream(queries).map(q -> q.matches(newPattern)).collect(Collectors.toList());
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {;
        return Arrays.stream(queries).map(q -> q.matches("[a-z]*" + String.join("[a-z]*", pattern.split("")) + "[a-z]*")).collect(Collectors.toList());
    }
