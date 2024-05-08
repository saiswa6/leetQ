class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (symbol == '(' || symbol == '{' || symbol == '[') {
                stack.push(symbol);
            } else {
                char matchSymbol = getRelevantChar(symbol);
                if (stack.isEmpty() && matchSymbol != stack.peek()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public char getRelevantChar(char ch) {
        if (ch == ']') {
            return '[';
        } else if (ch == '}') {
            return '{';
        } else if (ch == ')') {
            return '(';
        }
        return ' ';
    }
}