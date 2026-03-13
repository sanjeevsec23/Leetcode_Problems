int gcdOfOddEvenSums(int n) {
    int even=0,odd=0;
    int d=n+n;
    for(int i=1;i<=d;i++){
        if(i%2==0){
            even=even+i;
        }
        else{
            odd=odd+i;
        }
    }
    int b=even-odd;
    return b;
    
}