class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int num : nums)
        {
            List<List<Integer>> middlecalc = new ArrayList<>();
            for(List<Integer> a : result)
            {
                //a.add(num);
                middlecalc.add(new ArrayList<Integer>(a) {{add(num);}});
            }
            for(List<Integer> b : middlecalc)
            {
                //a.add(num);
                result.add(b);
            }
        }
        return result;
    }
}