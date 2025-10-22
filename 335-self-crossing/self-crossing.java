class Solution {
    public boolean isSelfCrossing(int[] distance) {
        int len = distance.length;
        // Base Case
        if(len <= 3){
            return false;
        }

        for(int i=3; i<len; i++){
            //Fourth line crosses first line and onward
            if(distance[i]>=distance[i-2] && distance[i-1]<=distance[i-3]){
                return true;
            }
            // Fifth line meets first line and onward
            if(i>=4){
                if(distance[i-1]==distance[i-3] && distance[i]+distance[i-4]>=distance[i-2]){
                    return true;
                }
            }
            // Sixth line crosses first line and onward
            if(i>=5){
                if(distance[i-2]-distance[i-4]>=0 && distance[i]>=distance[i-2]-distance[i-4] && distance[i-1]>=distance[i-3]-distance[i-5] && distance[i-1]<=distance[i-3]){
                    return true;
                }
            }
        }
        return false;
    }
}