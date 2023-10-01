    //TLE
    //brute force
    //Time: O(N * N * N * log3); Space: O(N)
    public List<List<Integer>> threeSum_brute(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet();
        for (int i = 0; i < nums.length - 2; i++)
            for (int j= i + 1; j < nums.length - 1; j++)
                for (int k = j + 1 ; k < nums.length; k++)
                    if (0 == nums[i] + nums[j] + nums[k]) {
                        List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                        Collections.sort(list);
                        resultSet.add(list);
                    }
        return new ArrayList<>(resultSet);
    }
