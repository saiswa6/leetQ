/*
Approach 1: Two Pointers
Intuition
- An intuitive method is to use two pointers to iterate over both strings. Assume we have two pointers, i and j, with i pointing to the first letter of word1 and j pointing to the first letter of word2. We also create an empty string results to store the outcome.
- We append the letter pointed to by pointer i i.e., word1[i], and increment i by 1 to point to the next letter of word1. Because we need to add the letters in alternating order, next we append word2[j] to results. We also increase j by 1.
- We continue iterating over the given strings until both are exhausted. We stop appending letters from word1 when i reaches the end of word1, and we stop appending letters from word2' when j reaches the end of word2.

Algorithm
1. Create two variables, m and n, to store the length of word1 and word2.
2. Create an empty string variable result to store the result of merged words.
3. Create two pointers, i and j to point to indices of word1 and word2. We initialize both of them to 0.
4. While i < m || j < n:
   If i < m, it means that we have not completely traversed word1. As a result, we append word1[i] to result. We increment i to point to next index of words.
   If j < n, it means that we have not completely traversed word2. As a result, we append word2[j] to result. We increment j to point to next index of words.
5. Return results.

- java: The String class is immutable in java. So we used the mutable StringBuilder to concatenate letters to result.
*/

class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;

        while (i < m || j < n) {
            if (i < m) {
                result.append(word1.charAt(i++));
            }
            if (j < n) {
                result.append(word2.charAt(j++));
            }
        }

        return result.toString();
    }
}

/*
Complexity Analysis
Here, m is the length of word1 and nnn is the length of word2.
Time complexity: O(m+n)
We iterate over word1 and word2 once and push their letters into result. It would take O(m+n) time.

Space complexity: O(1)
Without considering the space consumed by the input strings (word1 and word2) and the output string (result), we do not use more than constant space.
*/

/*
How to Convert Char Array to String in Java
-------------------------------------------
1. Using String Class Constructor
The String class provides a constructor that parses a char[] array as a parameter and allocates a new String. It represents the sequence of the characters (string). If we do any modification in the char[] array, the newly created string remains the same.

Syntax:
public String (char[] value)  
Ex : //character array  
     char[] ch = {'w', 'e', 'l', 'c', 'o', 'm', 'e', ' ' , 't', 'o', ' ', 'J', 'a', 'v', 'a', 't', 'p', 'o', 'i', 'n', 't'};  
     //constructor of the String class that parses char array as a parameter  
     String string = new String(ch);  

2. Using valueOf() Method
The valueOf() method is a static method of the String class that is also used to convert char[] array to string. The method parses a char[] array as a parameter. 
It returns a newly allocated string that represents the same sequence of characters contained in the character array

Ex : //constructor of the String class  
    String str = new String();  
    //invoking valueOf() method of the String class  
    String string = str.valueOf(chars);  

3. Using StringBuilder Class
append() Method
- The append() method of the StringBuilder class appends the string representation of the char[] array. It parses parameter str that is to be appended. 
- It returns a reference to this object. 
toString() Method
- The toString() method of the StringBuilder class returns a string that represents the data in the sequence. It allocates a new String object and initialized to contain the character sequence. 
- If we do any change in the char[] array, it does not affect the newly created string.
Ex: StringBuilder sb = new StringBuilder();  
    //Java for-each loop  
    for(char chars: ch)   
    {  
    //appends the string representation of the char array   
    sb.append(chars);  
    }  
    //the toString() method returns a string that represents data in the sequence  
    String string = sb.toString();  
*/