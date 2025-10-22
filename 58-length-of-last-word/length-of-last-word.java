class Solution {
    public int lengthOfLastWord(String s) {
        int count=0;
        String x=s.trim();
        for(int i=x.length()-1;i>=0;i--){
            char c=x.charAt(i);
            if(c==' '){
                return count;
            }
            count++;
        }
        return count;
    }
}