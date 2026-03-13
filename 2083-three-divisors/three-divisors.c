bool isThree(int a){
    int count=0;
    for(int i=1;i<=a;i++){
        if(a%i==0){
            count++;
        }
    }
    if(count==3){
        return true;
    }
    else{
        return false;
    }
}