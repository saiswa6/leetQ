/*
Approach : 

*/



class Solution {
    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>(); // Here, problem is each time map is declared and stores values for each func call. So, move this to class level so that it is not repetative.
        map.put("I" , 1);
        map.put("V" , 5);
        map.put("X" , 10);
        map.put("L" , 50);
        map.put("C" , 100);
        map.put("D" , 500);
        map.put("M", 1000);
        map.put("IV" , 4);
        map.put("IX" , 9);
        map.put("XL" , 40);
        map.put("XC" , 90);
        map.put("CD" , 400);
        map.put("CM" , 900);

        int result = 0;
        for(int i = 0; i < s.length(); ++i)
        {
            String one = s.substring(i,i+1);
            String two = (i+2) <= s.length() ? s.substring(i,i+2) : null;
            if(two != null && (two.equals("IV") || two.equals("IX") || two.equals("XL")  || two.equals("XC") || two.equals("CD") || two.equals("CM")) )
            {
                result = result + map.get(two);
                i = i + 1;
            }
            else
            {
                result += map.get(one);
            }
        }

        return result;


    }
}
