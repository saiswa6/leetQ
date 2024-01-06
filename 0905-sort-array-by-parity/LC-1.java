/*
Approach 1: Sort
Intuition and Algorithm
Use a custom comparator when sorting, to sort by parity.


Complexity Analysis
Time Complexity: O(Nlog⁡N), where NNN is the length of A.
Space Complexity: O(N) for the sort, depending on the built-in implementation of sort.
*/
class Solution {
    public int[] sortArrayByParity(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int t = 0; t < A.length; ++t)
            B[t] = A[t];

        Arrays.sort(B, (a, b) -> Integer.compare(a%2, b%2));

        for (int t = 0; t < A.length; ++t)
            A[t] = B[t];
        return A;

        /* Alternative:
        return Arrays.stream(A)
                     .boxed()
                     .sorted((a, b) -> Integer.compare(a%2, b%2))
                     .mapToInt(i -> i)
                     .toArray();
        */
    }
}


/*The Integer.compare(a, b) method is a static method in the Integer class in Java that is used to compare two int values numerically. It returns an int value that indicates the relationship between the two given integers. The possible return values are:

Negative value: Indicates that the first integer (a) is less than the second integer (b).
Zero: Indicates that the first integer (a) is equal to the second integer (b).
Positive value: Indicates that the first integer (a) is greater than the second integer (b).*/


/*
The code Arrays.sort(B, (a, b) -> Integer.compare(a % 2, b % 2)) sorts the array B based on the remainder of each element when divided by 2. Let's break down the components of this code:

Arrays.sort(B, comparator): This method is used to sort the elements of the array B. The sorting is done based on the provided comparator, which defines the sorting order.

(a, b) -> Integer.compare(a % 2, b % 2): This part is a lambda expression that represents a comparator. It compares two elements (a and b) based on the result of the expression Integer.compare(a % 2, b % 2). Here's what this expression does:

(a, b): This denotes the two elements being compared.
a % 2 and b % 2: These expressions calculate the remainder when a and b are divided by 2.
Integer.compare(a % 2, b % 2): This compares the remainders of a and b numerically. It returns a negative value if a % 2 is less than b % 2, zero if they are equal, and a positive value if a % 2 is greater than b % 2.
So, the lambda expression effectively sorts the elements based on whether they are even or odd. Even numbers will have a remainder of 0 when divided by 2, and odd numbers will have a remainder of 1.
*/
