class Solution {
    public int calculateMinimumHP(int[][] arr) {
        
        int rows = arr.length;
        int cols = arr[0].length;

        int [][] dp = new int[rows][cols];
        for(int [] ar : dp){
            Arrays.fill(ar,-1);
        }

        return helper(0,0,arr,dp);
    }


    private static int helper(int row,int col,int[][] arr,int[][] dp){
        if(row == arr.length || col == arr[0].length)return Integer.MAX_VALUE;

        if(dp[row][col] != -1)return dp[row][col];
        
        if(row == arr.length-1 && col == arr[0].length-1){
            return dp[row][col] = Math.max(1,1-arr[row][col]);
        }

        int right = helper(row,col+1,arr,dp);
        int down = helper(row+1,col,arr,dp);

        int total = Math.min(right,down) - arr[row][col];

        return dp[row][col] = Math.max(1,total);


    }
}