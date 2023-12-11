class Solution {
    public boolean isPalindrome(String s) {
        int length = s.length();
        int start = 0;
        int end = length - 1;
        int startCount = 0;
        int endCount = 0;
        char startChar =' ';
        char endChar =' ';

        while(start < end)
        {
            boolean isStartAlphaNumeric = isAlphaNumeric(s.charAt(start));
            if (isStartAlphaNumeric) {
                startCount++;
                startChar = s.charAt(start);
                start++;
                
            }
            else {
                while (!isStartAlphaNumeric && start < end) {
                    start++;
                    isStartAlphaNumeric = isAlphaNumeric(s.charAt(start));
                    if(isStartAlphaNumeric) {
                        startCount++;
                        startChar = s.charAt(start);
                        start++;
                        break;
                    }

                }
            }

            boolean isEndAlphaNumeric = isAlphaNumeric(s.charAt(end));
            if (isEndAlphaNumeric) {
                endCount++;
                endChar = s.charAt(end);
                end--;
            }
            else {
                while (!isEndAlphaNumeric && start < end) {
                    end--;
                    isEndAlphaNumeric = isAlphaNumeric(s.charAt(end));
                    if(isEndAlphaNumeric) {
                        endCount++;
                        endChar = s.charAt(end);
                        end--;
                        break;
                    }

                }
            }

            if(startChar >= 'A' && startChar <= 'Z')
            {
                startChar = (char)(startChar+32);
            }
            if(endChar >= 'A' && endChar <= 'Z')
            {
                endChar = (char)(endChar + 32);
            }

            if(isStartAlphaNumeric && isEndAlphaNumeric && (startCount == endCount) && (startChar != endChar)) {
                return false;
            }

            if(start + 2 == end)
            {
                break;
            }
        }

        return true;
    }

    public boolean isAlphaNumeric(char ch)
    {
        if((ch >='0'&& ch <='9') || (ch >='a'&& ch <='z') || (ch >='A'&& ch <='Z')) {
            return true;
        }
        else {
            return false;
        }
    }

































    /* 
        public boolean isPalindrome(String s) {
        String newString = "";
        for(int i=0;i<s.length();i++)
        {
            char singleChar = s.charAt(i);
            if((singleChar >= 65 && singleChar <=90) || (singleChar >= 97 && singleChar <=122) || (singleChar >= 48 && singleChar <= 57)) {
                if((singleChar >= 65 && singleChar <=90))
                {
                    singleChar = (char)(singleChar+32);
                }
                newString = newString + singleChar;
            }
        }

        return isPali(newString);
    }

    public static boolean isPali(String newString)
    {
        if(newString == ""|| newString.length() == 0)
        {
            return true;
        }
        int len  =newString.length();
        for(int j=0;j<len/2;j++)
        {
            if(newString.charAt(j) != newString.charAt(len-j-1))
            {
                return false;
            }
        }

        return true;
    }
    */
}