class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        combine(1, k, new ArrayList<>(),ans, n );

        return ans;
    }

    private void combine(int index, int k, List<Integer> subset,List<List<Integer>> ans, int n )
    {
        if (subset.size() ==k)
        {
            ans.add(new ArrayList<>(subset));
            return;
        }


        for (int i= index; i<=n; i++)
        {
            subset.add(i);
            combine(i+1,  k, subset, ans,  n );
            subset.remove(subset.size()-1);
        }
    }
}