//I just popped matching words from the end of the arrays, reversed the arrays, and popped matching words again. If either array ended up empty, we have a winner. You could guarantee one array the shorter first to reduce a little logging in the loops. You could also use a deque and not have to reverse, but reversing is less work and space than converting a split list to a deque

        s1 = sentence1.split()
        s2 = sentence2.split()
        while s1 and s2 and s1[-1] == s2[-1]:
            s1.pop()
            s2.pop()
        s1.reverse()
        s2.reverse()
        while s1 and s2 and s1[-1] == s2[-1]:
            s1.pop()
            s2.pop()


//simple java 2 pointer solution

class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] arr1 = sentence1.split(" "), arr2 = sentence2.split(" ");
        int s1 =0, e1 = arr1.length-1, s2 = 0, e2 = arr2.length-1;
        for(;s1<=e1 && s2<=e2 && arr1[s1].equals(arr2[s2]);s1++,s2++);
        for(;e1>=0 && e2>=0 && arr1[e1].equals( arr2[e2]); e1--,e2--);
        return s1>e1 || s2>e2;
    }
}

class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        int i = 0, j = 0;
        while (i < s1.length && j < s2.length){
            if (!s1[i].equals(s2[j])) break;
            i++;
            j++;
        }
            if (i == s1.length || j == s2.length) return true;
        int len1 = s1.length - 1, len2 = s2.length - 1;
        while(len1 >= i && len2 >= j){
            if(!s1[len1].equals(s2[len2])) return false;
            len1--;
            len2--;
        }
        return len1 < i || len2 < j;
    }
}

class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        
        if (sentence1.length () < sentence2.length ()) {
            return areSentencesSimilar (sentence2, sentence1);
        }
        
        String[] words1 = sentence1.split (" ");
        String[] words2 = sentence2.split (" ");
        
        int i = 0, j = words1.length - 1, k = words2.length - 1;
        
        while (i < words2.length && words1[i].equals (words2[i])) {
            i++;
        }
        
        while (k >= 0 && words1[j].equals (words2[k])) {
            j--;
            k--;
        }
        
        return i > k;
    }
}
