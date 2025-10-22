class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // for(int i = 0; i<matrix.length;i++){
        //     for(int j = 0; j < matrix[i].length;j++){
        //         if(matrix[i][j] == target) return true;
        //     }
        // }
        // return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int low = 0, high = n*m-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            int row = mid/m, col =  mid%m;
            if(matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}