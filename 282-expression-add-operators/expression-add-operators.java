class Solution {
    void solve(int idx,long calcVal,long prevNum,String num,int target,List<String> ans,StringBuilder str){
        if(idx==num.length()){
            if(calcVal==target){
                ans.add(str.toString());
            }
            return;
        }
        for(int i=idx;i<num.length();i++){
            if(i!=idx && num.charAt(idx)=='0') break;
        

            Long curr = Long.parseLong(num.substring(idx,i+1));
            int len = str.length();

            if(idx==0){
                str.append(curr);
                solve(i+1,curr,curr,num,target,ans,str);
                str.setLength(len);
            }
            else{
                // add
                str.append("+");
                str.append(curr);
                solve(i+1,calcVal+curr,curr,num,target,ans,str);
                str.setLength(len);
                //sub
                str.append("-");
                str.append(curr);
                solve(i+1,calcVal-curr,-curr,num,target,ans,str);
                str.setLength(len);
                // mul
                str.append("*");
                str.append(curr);
                solve(i+1,calcVal-prevNum + prevNum*curr,prevNum*curr,num,target,ans,str);
                str.setLength(len);
            }
        }  
    }

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if(num==null || num.length()==0) return ans;
        solve(0,0,0,num,target,ans,new StringBuilder());

        return ans;
    }
}