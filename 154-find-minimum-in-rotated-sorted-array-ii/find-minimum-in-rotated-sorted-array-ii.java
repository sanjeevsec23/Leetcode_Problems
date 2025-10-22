class Solution {
    public int findMin(int[] nums) {
        int start =0;
        int end = nums.length-1;
        int ans=0;
        while(start<end){
            int mid = start + (end-start)/2;
           if(nums[start]==nums[mid] && nums[mid]==nums[end]){
                start++;
                end--;
            }
            else if(nums[mid]>nums[end]){
                start=mid+1;
            }
            else{
                end=mid;
            }
        }
    return nums[start];}
}