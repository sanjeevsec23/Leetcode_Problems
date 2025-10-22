class Solution {
    public int thirdMax(int[] nums) {
        Integer firstMax = null;
        Integer secMax = null;
        Integer thirdMax = null;

        for(int i=0; i<nums.length; i++){
            if((firstMax != null && nums[i] == firstMax) || (secMax != null && nums[i] == secMax) || (thirdMax != null && nums[i] == thirdMax)){
                continue;
            }
            if(firstMax == null || nums[i] > firstMax){
                thirdMax = secMax;
                secMax = firstMax;
                firstMax = nums[i];
            }
            else if(secMax == null || (nums[i] > secMax)){
                thirdMax = secMax;
                secMax = nums[i];
            }
            else if( thirdMax == null || (nums[i] > thirdMax) ){
                thirdMax = nums[i];
            }
        }

        return thirdMax == null ? firstMax : thirdMax;
    }
}