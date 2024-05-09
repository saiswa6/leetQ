class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] sArray = s.split(" ");
        HashMap<Character, String> hashMap = new HashMap<>();

        for (int i = 0; i < sArray.length; i++) {
            if (hashMap.containsKey(pattern.charAt(i))) {
                if (!sArray[i].equals(hashMap.get((pattern.charAt(i))))) {
                    return false;
                } else {
                    continue;
                }

            } else {
                if(hashMap.containsValue(sArray[i])) {
                    return false;
                } else {
                    hashMap.put(pattern.charAt(i), sArray[i]);
                }
            }
        }

        return true;

    }
}