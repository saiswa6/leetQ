/*
Approach :
Iterate from last to first and compare the value of previous to current to predict whether to subtract or add the current number.
Using switch case is an efficient approach rather than using HashMap
*/

public int romanToInt(String s) {
        private int result = 0, previous = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            final int currentNumber = switch (s.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                default -> 1000;
            };
            result += (currentNumber < previous) ? -currentNumber : currentNumber;
            previous = currentNumber;
        }
        return result;
    }

/*
Approach 2 Most Efficient Solution:
I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.

For cases in which the integer representations of I, X or C can be multiplied by 4 and still be less than the integer representation of the number that follows,** we know that we have to subtract the integer representation of I, X or C from the total. Otherwise, the number of the current iteration is added to the total. (**The number that follows is known because this approach iterates over the string from back to front.)
- Using switch case is an efficient approach rather than using HashMap
*/

 public int romanToInt(String s) {
         int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }
