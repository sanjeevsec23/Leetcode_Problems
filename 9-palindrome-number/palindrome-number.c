bool isPalindrome(int x) {
    long int sum=0;
    int b=x;
    while(x>0){
        int a=x%10;
        sum=sum*10+a;
        x=x/10;
        }
        if(sum == b){
            return true;
        }
        else{
            return false;
        }
}