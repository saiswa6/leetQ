/*
- We are given string sss in a particular form k[string] and we have to decode it as string repeated k times . For example,2[b] is decoded as bb.
- The problem seems straightforward at first glance. But the trick here is that there can be nested encoded strings like k[string k[string]]. 
- For example, string s =3[a2[c]]. In such cases, we must decode the innermost string and continue in an outward direction until the entire string is decoded.

Approach 1: Using Stack
Intuition
- We have to decode the result in a particular pattern. We know that the input is always valid. The pattern begins with a number k, followed by opening braces [, followed by string. Post that, 
  there could be one of the following cases :
- There is another nested pattern k[string k[string]]
- There is a closing bracket k[string]
- Since we have to start decoding the innermost pattern first, continue iterating over the string s, pushing each character to the stack until it is not a closing bracket ]. 
  Once we encounter the closing bracket ], we must start decoding the pattern.

As we know that stack follows the Last In First Out (LIFO) Principle, the top of the stack would have the data we must decode.
*/

class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        }      
        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }
}



/*
Complexity Analysis

Time Complexity: O(maxKcountK⋅n), where maxK is the maximum value of k, countK is the count of nested kkk values and nnn is the maximum length of encoded string.
Example, for s = 20[a10[bc]], maxK is 20, countK is 2 as there are 2 nested k values (20 and 10) . Also, there are 2 encoded strings a and bc with maximum length of encoded string ,n as 2
*/
