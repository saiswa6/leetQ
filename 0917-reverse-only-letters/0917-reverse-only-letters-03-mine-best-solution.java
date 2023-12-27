t.c o(n)
s.c o(1)

class Solution {
    public String reverseOnlyLetters(String s) {
        int slength = s.length();
        int start = 0;
        int end = slength - 1;
        char sarray [] = s.toCharArray();

        while (start < end) {
            while (start < end && !isAlphabet(sarray[start])) {
                start++;
            }

            while (start < end && !isAlphabet(sarray[end])) {
                end--;
            }

            char temp = sarray[start];
            sarray[start] = sarray[end];
            sarray[end] = temp;
            start++;
            end--;
        }

        return new String(sarray);
    }

    public static boolean isAlphabet(char ch) {
        if ((ch>='A' && ch <='Z') || (ch>='a' && ch <='z')) {
            return true;
        }
        else {
            return false;
        }
    }
}


//DISCUSSION
    public String reverseOnlyLetters(String s) {
        char temp[] = s.toCharArray();      
        int low = 0 , high = s.length()-1;
        while(low < high){
            if(Character.isAlphabetic(temp[low]) && Character.isAlphabetic(temp[high])){
                char i = temp[low];temp[low] = temp[high];
                temp[high] = i;//   Please UPVOTE \U0001f64b‍♂️\U0001f64b\U0001f481\U0001f647\U0001f647\U0001f647‍♂️\U0001f647‍♀️\U0001f647\U0001f647
                low++; high--;
            }else if(!Character.isAlphabetic(temp[low]))  low++;
            else if(!Character.isAlphabetic(temp[high]))  high--;   
        }
        return String.valueOf(temp);
    }
