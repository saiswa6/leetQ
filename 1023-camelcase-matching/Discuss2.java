/*
Solution 2: Use Regax

Join "[a-z]*" into pattern characters.
Slow compared with solution 1.

Regex solutions are very slow compared to a tailor-made solution in these kind of questions.
*/

    public List<Boolean> camelMatch(String[] queries, String pattern) {;
        return Arrays.stream(queries).map(q -> q.matches("[a-z]*" + String.join("[a-z]*", pattern.split("")) + "[a-z]*")).collect(Collectors.toList());
    }
