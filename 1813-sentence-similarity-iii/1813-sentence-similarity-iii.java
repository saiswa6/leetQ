class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String [] sen1 = sentence1.split(" ");
        String [] sen2 = sentence2.split(" ");

        int sen1Length = sen1.length;
        int sen2Length = sen2.length;

        if(sen1Length > sen2Length) { // second parameter is big sentence
            return areSentencesSimilar(sentence2, sentence1);
        }

        int i = 0;
        while(i < sen1Length && sen1[i].equals(sen2[i])) {
            i++;
        }
        while(i < sen1Length && sen1[i].equals(sen2[sen2Length - sen1Length + i])) {
            i++;
        }

        return i == sen1Length;
    }
}