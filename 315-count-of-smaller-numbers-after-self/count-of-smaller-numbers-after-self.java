

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   
    //intuition 3: We can apply merge sort and recursively calculate the numbers smaller after the current number. Just before merge, check from left to right
    //of arr b (for elements in arr a) and stop until a larger number is found than a[i] (arr a). If the first number of b (sorted array) is larger than 
    //the a[i] element then do not add to count and simply skip. This means that all the elements after b[j] are anyway greater than a[i]. 
    // This will save time. 
    //The only thing to keep in mind is that there could be duplicates as well and how will you handle duplicates at different
    //indices -> We can somehow map each element to its original index in nums, increment count against that pair (idx, num) and at the last just take
    //index and update the count in counts arr. -> To handle this, have a hashmap in which key is a integer list of length 2 (where arr[0] = num and 
    //arr[1] = idx in nums) and values as count.
    //Have an array of arraylist and merge sort that.
    //At last, take all the keys and put the value (count) at key.get(1) idx

    //TLE without optimizing the count smaller logic (see below)
    //Beats 5% with optimizing the count smaller logic -> need to further optmize
    

    //Optimizing intuition 3 (changing  List<Integer> listAns to pair)
    //using int[][] might still work but for it to work, we need to initialize it with the maximum number which will be a lot memory intensive

    //cleaning intuition 3

    class Solution {
        static class Pair{
            long val;
            int index;

            Pair(long v, int idx){
                val = v;
                index = idx;
            }
        }

    private int[] countMap;  //each index (mapped to indices in nums) have the respective count
    
    public List<Integer> countSmaller(int[] nums) {

        int n = nums.length;
        countMap = new int[n];
        int[] ans = new int[n];

 

        Pair[] pairArr = new Pair[n];

        for(int i = 0; i < n; i ++){
            pairArr[i] = new Pair(nums[i], i); //(val, idx)
        }

        mergeSort(pairArr);
       
        List<Integer> listAns = new ArrayList<>();
            
        for (int count : countMap) {
            listAns.add(count);
        }
        return listAns;
    }

    public void mergeSort(Pair[] pairArr){

        int n = pairArr.length;
      
        if(n == 1){
            return;
        }

        Pair[] leftPairArr = new Pair[n / 2];
        Pair[] rightPairArr = new Pair[n - n / 2];

        for(int i = 0; i < leftPairArr.length; i ++){ //intializing left sub-array
            leftPairArr[i] = pairArr[i];
        }

        for(int i = 0; i < rightPairArr.length; i ++){ //intializing right sub-array
            rightPairArr[i] = pairArr[i + n / 2];
        }


        mergeSort(leftPairArr);
        mergeSort(rightPairArr);

        countSmallNum(leftPairArr, rightPairArr);

        merge(pairArr, leftPairArr, rightPairArr);

    }

    private void countSmallNum(Pair[] leftPairArr, Pair[] rightPairArr){

        // int i = 0;
        // int j = 0;

        //the current while loop gives O(n^2) as it checks for all j's for all i's
        //to avoid this we can start from ending of rightListArr to avoid comparing all the combinations. 
            //check if leftListArr[i] > rightListArr[j] (j starting from end), if yes then that simply means that all the elements in
            //rightListArr are smaller than leftListArr[i], so simply add the rightListArr.length - j to the count of leftListArr[i]

        //more optimal way of checking small numbers

        int j = 0;

        for(int i = 0; i < leftPairArr.length; i ++){

            while(j < rightPairArr.length && leftPairArr[i].val > rightPairArr[j].val){
                j ++;
            }
            // countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j); //for each leftListArr[i] the count will start from the where the prevous leftListArr left
            // //as if leftListArr[i - 1] had stopped at j = 3 (count += 3), then it is evident that leftListArr[i] will at least have 3 smaller numbers and should 
            // //start looking from j = 3. This way j never goes backwards
           

            countMap[leftPairArr[i].index] += j;
        }


        
    }

    private void merge(Pair[] pairArr, Pair[] leftPairArr, Pair[] rightPairArr){

        int k = 0; //listArr
        int i = 0; //leftListArr
        int j = 0; //rightListArr

        while(i < leftPairArr.length && j < rightPairArr.length){
            if(leftPairArr[i].val > rightPairArr[j].val){
                pairArr[k] = rightPairArr[j];
                j ++;
            }
            else{
                pairArr[k] = leftPairArr[i];
                i ++;

            }
            k ++;
        }

        while(i < leftPairArr.length){
            pairArr[k ++] = leftPairArr[i ++];
        }

        while(j < rightPairArr.length){
            pairArr[k ++] = rightPairArr[j ++];
        }

    }

}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   
//     //intuition 3: We can apply merge sort and recursively calculate the numbers smaller after the current number. Just before merge, check from left to right
//     //of arr b (for elements in arr a) and stop until a larger number is found than a[i] (arr a). If the first number of b (sorted array) is larger than 
//     //the a[i] element then do not add to count and simply skip. This means that all the elements after b[j] are anyway greater than a[i]. 
//     // This will save time. 
//     //The only thing to keep in mind is that there could be duplicates as well and how will you handle duplicates at different
//     //indices -> We can somehow map each element to its original index in nums, increment count against that pair (idx, num) and at the last just take
//     //index and update the count in counts arr. -> To handle this, have a hashmap in which key is a integer list of length 2 (where arr[0] = num and 
//     //arr[1] = idx in nums) and values as count.
//     //Have an array of arraylist and merge sort that.
//     //At last, take all the keys and put the value (count) at key.get(1) idx

//     //TLE without optimizing the count smaller logic (see below)
//     //Beats 5% with optimizing the count smaller logic -> need to further optmize
    

//     //Optimizing intuition 3 (changing  List<Integer> listAns to pair)
//     //using int[][] might still work but for it to work, we need to initialize it with the maximum number which will be a lot memory intensive

//     class Solution {
//         static class Pair{
//             long val;
//             int index;

//             Pair(long v, int idx){
//                 val = v;
//                 index = idx;
//             }
//         }

//     private int[] countMap;  //each index (mapped to indices in nums) have the respective count
    
//     public List<Integer> countSmaller(int[] nums) {

//         int n = nums.length;
//         // countMap = new HashMap<>();
//         countMap = new int[n];
//         int[] ans = new int[n];

 

//         // List<Integer>[] listArr = new List[nums.length];
//         Pair[] pairArr = new Pair[n];

//         for(int i = 0; i < n; i ++){
//             pairArr[i] = new Pair(nums[i], i); //(val, idx)
//         }


//         // for(int i = 0; i < n; i ++){ 
//         //     listArr[i] = new ArrayList<>(); //intializing each index with empty list in listArr
//         //     listArr[i] = Arrays.asList(nums[i], i); //initalizing each index with nums[i], i list

 
//         // }

//         mergeSort(pairArr);

       
//         List<Integer> listAns = new ArrayList<>();
        
    
//         for (int count : countMap) {
//             listAns.add(count);
//         }
//         return listAns;
        


//     }

//     public void mergeSort(Pair[] pairArr){

//         int n = pairArr.length;

      
//         if(n == 1){
//             return;
//         }

//         Pair[] leftPairArr = new Pair[n / 2];
//         Pair[] rightPairArr = new Pair[n - n / 2];

//         for(int i = 0; i < leftPairArr.length; i ++){ //intializing left sub-array
//             leftPairArr[i] = pairArr[i];
//         }

//         for(int i = 0; i < rightPairArr.length; i ++){ //intializing right sub-array
//             rightPairArr[i] = pairArr[i + n / 2];
//         }


//         mergeSort(leftPairArr);
//         mergeSort(rightPairArr);

//         countSmallNum(leftPairArr, rightPairArr);

//         merge(pairArr, leftPairArr, rightPairArr);

//     }

//     private void countSmallNum(Pair[] leftPairArr, Pair[] rightPairArr){

//         // int i = 0;
//         // int j = 0;

//         //the current while loop gives O(n^2) as it checks for all j's for all i's
//         //to avoid this we can start from ending of rightListArr to avoid comparing all the combinations. 
//             //check if leftListArr[i] > rightListArr[j] (j starting from end), if yes then that simply means that all the elements in
//             //rightListArr are smaller than leftListArr[i], so simply add the rightListArr.length - j to the count of leftListArr[i]

//         //more optimal way of checking small numbers

//         int j = 0;

//         for(int i = 0; i < leftPairArr.length; i ++){

//             while(j < rightPairArr.length && leftPairArr[i].val > rightPairArr[j].val){
//                 j ++;
//             }
//             // countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j); //for each leftListArr[i] the count will start from the where the prevous leftListArr left
//             // //as if leftListArr[i - 1] had stopped at j = 3 (count += 3), then it is evident that leftListArr[i] will at least have 3 smaller numbers and should 
//             // //start looking from j = 3. This way j never goes backwards
            
//             // countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j); //for each leftListArr[i] the count will start from the where the prevous leftListArr left
//             //as if leftListArr[i - 1] had stopped at j = 3 (count += 3), then it is evident that leftListArr[i] will at least have 3 smaller numbers and should 
//             //start looking from j = 3. This way j never goes backwards

//             // countMap[leftListArr[i].get(1)] += j;
//             countMap[leftPairArr[i].index] += j;
//         }


        
//     }

//     private void merge(Pair[] pairArr, Pair[] leftPairArr, Pair[] rightPairArr){

//         int k = 0; //listArr
//         int i = 0; //leftListArr
//         int j = 0; //rightListArr

//         while(i < leftPairArr.length && j < rightPairArr.length){
//             if(leftPairArr[i].val > rightPairArr[j].val){
//                 pairArr[k] = rightPairArr[j];
//                 j ++;
//             }
//             else{
//                 pairArr[k] = leftPairArr[i];
//                 i ++;

//             }
//             k ++;
//         }

//         while(i < leftPairArr.length){
//             pairArr[k ++] = leftPairArr[i ++];
//         }

//         while(j < rightPairArr.length){
//             pairArr[k ++] = rightPairArr[j ++];
//         }

//     }

// }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//     //intuition 3: We can apply merge sort and recursively calculate the numbers smaller after the current number. Just before merge, check from left to right
//     //of arr b (for elements in arr a) and stop until a larger number is found than a[i] (arr a). If the first number of b (sorted array) is larger than 
//     //the a[i] element then do not add to count and simply skip. This means that all the elements after b[j] are anyway greater than a[i]. 
//     // This will save time. 
//     //The only thing to keep in mind is that there could be duplicates as well and how will you handle duplicates at different
//     //indices -> We can somehow map each element to its original index in nums, increment count against that pair (idx, num) and at the last just take
//     //index and update the count in counts arr. -> To handle this, have a hashmap in which key is a integer list of length 2 (where arr[0] = num and 
//     //arr[1] = idx in nums) and values as count.
//     //Have an array of arraylist and merge sort that.
//     //At last, take all the keys and put the value (count) at key.get(1) idx

//     //TLE without optimizing the count smaller logic (see below)
//     //Beats 5% with optimizing the count smaller logic -> need to further optmize
    

//     //Optimizing intuition 3 (hashmap replaced with int[] and counting small logic changed)

//     class Solution {
//     // private HashMap<List<Integer>, Integer> countMap; //key.get(0) = num and key.get(1) = index
//     private int[] countMap;  //each index (mapped to indices in nums) have the respective count
    
//     public List<Integer> countSmaller(int[] nums) {

//         int n = nums.length;
//         // countMap = new HashMap<>();
//         countMap = new int[n];
//         int[] ans = new int[n];

//         // //storing original indices against each num with 0 count
//         // for(int i = 0; i < n; i ++){
//         //     countMap.put(Arrays.asList(nums[i], i), 0);
//         // }    

//         List<Integer>[] listArr = new List[nums.length];

//         for(int i = 0; i < n; i ++){ 
//             listArr[i] = new ArrayList<>(); //intializing each index with empty list in listArr
//             listArr[i] = Arrays.asList(nums[i], i); //initalizing each index with nums[i], i list

//             // countMap.put(Arrays.asList(nums[i], i), 0);  //try running this while commenting the above for loop

//         }

//         mergeSort(listArr);

//         // for(List<Integer> key : countMap.keySet()){
//         //     ans[key.get(1)] = countMap.get(key);
//         // }

//         List<Integer> listAns = new ArrayList<>();
    
//         for (int count : countMap) {
//             listAns.add(count);
//         }
//         return listAns;
        


//     }

//     public void mergeSort(List<Integer>[] listArr){

//         int n = listArr.length;

      
//         if(n == 1){
//             return;
//         }

//         List<Integer>[] leftListArr = new List[n / 2];
//         List<Integer>[] rightListArr = new List[n - n / 2];

//         for(int i = 0; i < leftListArr.length; i ++){ //intializing left sub-array
//             leftListArr[i] = listArr[i];
//         }

//         for(int i = 0; i < rightListArr.length; i ++){ //intializing right sub-array
//             rightListArr[i] = listArr[i + n / 2];
//         }


//         mergeSort(leftListArr);
//         mergeSort(rightListArr);

//         countSmallNum(leftListArr, rightListArr);

//         merge(listArr, leftListArr, rightListArr);

//     }

//     private void countSmallNum(List<Integer>[] leftListArr, List<Integer>[] rightListArr){

//         // int i = 0;
//         // int j = 0;

//         //the current while loop gives O(n^2) as it checks for all j's for all i's
//         //to avoid this we can start from ending of rightListArr to avoid comparing all the combinations. 
//             //check if leftListArr[i] > rightListArr[j] (j starting from end), if yes then that simply means that all the elements in
//             //rightListArr are smaller than leftListArr[i], so simply add the rightListArr.length - j to the count of leftListArr[i]
//         // while(i < leftListArr.length){
//         //     if(j == rightListArr.length){
//         //         j = 0; //reset j if end of rightListArr is reached
//         //         i ++;
//         //         if(i == leftListArr.length){
//         //             break;
//         //         }
//         //     }
//         //     if(leftListArr[i].get(0) > rightListArr[j].get(0)){
//         //         countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + 1); //incrementing count
//         //         j ++;
//         //     }
//         //     else{
//         //         // countMap.put(rightListArr[j], countMap.get(rightListArr[j]) + 1); //incrementing count
//         //         i ++;
//         //         j = 0;
//         //     }
//         // }

//         // int i = 0;
//         // int j = rightListArr.length - 1;
       
//         // while(i < leftListArr.length){
//         //     if(j == -1){
//         //         j = rightListArr.length - 1; //reset j if end of rightListArr is reached
//         //         i ++;
//         //         if(i == leftListArr.length){
//         //             break;
//         //         }
//         //     }
//         //     if(leftListArr[i].get(0) > rightListArr[j].get(0)){
//         //         countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j + 1); //incrementing count
//         //         i ++; //check for next left element
//         //     }
//         //     else{
//         //         // countMap.put(rightListArr[j], countMap.get(rightListArr[j]) + 1); //incrementing count
//         //         j --;
//         //     }
//         // }


//         //more optimal way of checking small numbers

//         int j = 0;

//         for(int i = 0; i < leftListArr.length; i ++){

//             while(j < rightListArr.length && leftListArr[i].get(0) > rightListArr[j].get(0)){
//                 j ++;
//             }
//             // countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j); //for each leftListArr[i] the count will start from the where the prevous leftListArr left
//             // //as if leftListArr[i - 1] had stopped at j = 3 (count += 3), then it is evident that leftListArr[i] will at least have 3 smaller numbers and should 
//             // //start looking from j = 3. This way j never goes backwards
            
//             // countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j); //for each leftListArr[i] the count will start from the where the prevous leftListArr left
//             //as if leftListArr[i - 1] had stopped at j = 3 (count += 3), then it is evident that leftListArr[i] will at least have 3 smaller numbers and should 
//             //start looking from j = 3. This way j never goes backwards
            
//             countMap[leftListArr[i].get(1)] += j;
//         }


        
//     }

//     private void merge(List<Integer>[] listArr, List<Integer>[] leftListArr, List<Integer>[] rightListArr){

//         int k = 0; //listArr
//         int i = 0; //leftListArr
//         int j = 0; //rightListArr

//         while(i < leftListArr.length && j < rightListArr.length){
//             if(leftListArr[i].get(0) > rightListArr[j].get(0)){
//                 listArr[k] = rightListArr[j];
//                 j ++;
//             }
//             else{
//                 listArr[k] = leftListArr[i];
//                 i ++;

//             }
//             k ++;
//         }

//         while(i < leftListArr.length){
//             listArr[k ++] = leftListArr[i ++];
//         }

//         while(j < rightListArr.length){
//             listArr[k ++] = rightListArr[j ++];
//         }

//     }

// }


    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//     //intuition 2: What if we sort all the elements after the current element in decreasing order. -> This will give TC of n x nlogn, so not preferred.
    
//     //intuition 3: We can apply merge sort and recursively calculate the numbers smaller after the current number. Just before merge, check from left to right
//     //of arr b (for elements in arr a) and stop until a larger number is found than a[i] (arr a). If the first number of b (sorted array) is larger than 
//     //the a[i] element then do not add to count and simply skip. This means that all the elements after b[j] are anyway greater than a[i]. 
//     // This will save time. 
//     //The only thing to keep in mind is that there could be duplicates as well and how will you handle duplicates at different
//     //indices -> We can somehow map each element to its original index in nums, increment count against that pair (idx, num) and at the last just take
//     //index and update the count in counts arr. -> To handle this, have a hashmap in which key is a integer list of length 2 (where arr[0] = num and 
//     //arr[1] = idx in nums) and values as count.
//     //Have an array of arraylist and merge sort that.
//     //At last, take all the keys and put the value (count) at key.get(1) idx

//     //TLE without optimizing the count smaller logic (see below)
//     //Beats 5% with optimizing the count smaller logic -> need to further optmize
    

//     class Solution {
//     private HashMap<List<Integer>, Integer> countMap; //key.get(0) = num and key.get(1) = index
    
//     public List<Integer> countSmaller(int[] nums) {

//         int n = nums.length;
//         countMap = new HashMap<>();
//         int[] ans = new int[n];

//         //storing original indices against each num with 0 count
//         for(int i = 0; i < n; i ++){
//             countMap.put(Arrays.asList(nums[i], i), 0);
//         }    

//         List<Integer>[] listArr = new List[nums.length];

//         for(int i = 0; i < n; i ++){ 
//             listArr[i] = new ArrayList<>(); //intializing each index with empty list in listArr
//             listArr[i] = Arrays.asList(nums[i], i); //initalizing each index with nums[i], i list

//             // countMap.put(Arrays.asList(nums[i], i), 0);  //try running this while commenting the above for loop

//         }

//         mergeSort(listArr);

//         for(List<Integer> key : countMap.keySet()){
//             ans[key.get(1)] = countMap.get(key);
//         }

//         List<Integer> listAns = new ArrayList<>();
    
//         for (int num : ans) {
//             listAns.add(num);
//         }
//         return listAns;
        


//     }

//     public void mergeSort(List<Integer>[] listArr){

//         int n = listArr.length;

      
//         if(n == 1){
//             return;
//         }

//         List<Integer>[] leftListArr = new List[n / 2];
//         List<Integer>[] rightListArr = new List[n - n / 2];

//         for(int i = 0; i < leftListArr.length; i ++){ //intializing left sub-array
//             leftListArr[i] = listArr[i];
//         }

//         for(int i = 0; i < rightListArr.length; i ++){ //intializing right sub-array
//             rightListArr[i] = listArr[i + n / 2];
//         }


//         mergeSort(leftListArr);
//         mergeSort(rightListArr);

//         countSmallNum(leftListArr, rightListArr);

//         merge(listArr, leftListArr, rightListArr);

//     }

//     private void countSmallNum(List<Integer>[] leftListArr, List<Integer>[] rightListArr){

//         // int i = 0;
//         // int j = 0;

//         //the current while loop gives O(n^2) as it checks for all j's for all i's
//         //to avoid this we can start from ending of rightListArr to avoid comparing all the combinations. 
//             //check if leftListArr[i] > rightListArr[j] (j starting from end), if yes then that simply means that all the elements in
//             //rightListArr are smaller than leftListArr[i], so simply add the rightListArr.length - j to the count of leftListArr[i]
//         // while(i < leftListArr.length){
//         //     if(j == rightListArr.length){
//         //         j = 0; //reset j if end of rightListArr is reached
//         //         i ++;
//         //         if(i == leftListArr.length){
//         //             break;
//         //         }
//         //     }
//         //     if(leftListArr[i].get(0) > rightListArr[j].get(0)){
//         //         countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + 1); //incrementing count
//         //         j ++;
//         //     }
//         //     else{
//         //         // countMap.put(rightListArr[j], countMap.get(rightListArr[j]) + 1); //incrementing count
//         //         i ++;
//         //         j = 0;
//         //     }
//         // }

//         // int i = 0;
//         // int j = rightListArr.length - 1;
       
//         // while(i < leftListArr.length){
//         //     if(j == -1){
//         //         j = rightListArr.length - 1; //reset j if end of rightListArr is reached
//         //         i ++;
//         //         if(i == leftListArr.length){
//         //             break;
//         //         }
//         //     }
//         //     if(leftListArr[i].get(0) > rightListArr[j].get(0)){
//         //         countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j + 1); //incrementing count
//         //         i ++; //check for next left element
//         //     }
//         //     else{
//         //         // countMap.put(rightListArr[j], countMap.get(rightListArr[j]) + 1); //incrementing count
//         //         j --;
//         //     }
//         // }


//         //more optimal way of checking small numbers

//         int j = 0;

//         for(int i = 0; i < leftListArr.length; i ++){

//             while(j < rightListArr.length && leftListArr[i].get(0) > rightListArr[j].get(0)){
//                 j ++;
//             }
//             countMap.put(leftListArr[i], countMap.get(leftListArr[i]) + j); //for each leftListArr[i] the count will start from the where the prevous leftListArr left
//             //as if leftListArr[i - 1] had stopped at j = 3 (count += 3), then it is evident that leftListArr[i] will at least have 3 smaller numbers and should 
//             //start looking from j = 3. This way j never goes backwards
            
//         }


        
//     }

//     private void merge(List<Integer>[] listArr, List<Integer>[] leftListArr, List<Integer>[] rightListArr){

//         int k = 0; //listArr
//         int i = 0; //leftListArr
//         int j = 0; //rightListArr

//         while(i < leftListArr.length && j < rightListArr.length){
//             if(leftListArr[i].get(0) > rightListArr[j].get(0)){
//                 listArr[k] = rightListArr[j];
//                 j ++;
//             }
//             else{
//                 listArr[k] = leftListArr[i];
//                 i ++;

//             }
//             k ++;
//         }

//         while(i < leftListArr.length){
//             listArr[k ++] = leftListArr[i ++];
//         }

//         while(j < rightListArr.length){
//             listArr[k ++] = rightListArr[j ++];
//         }

//     }

// }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //intuition 1: Brute force will be to go through each element and compare it with remaining elements
    // class Solution {

    //TC: O(n^2)
    //TLE 
    // public List<Integer> countSmaller(int[] nums) {
    //     int n = nums.length;
    //     List<Integer> ans = new ArrayList<>();

    //     for(int i = 0; i < n; i ++){
    //         int count = 0;
    //         // ans.add(count);
    //         for(int j = i + 1; j < n; j ++){
    //             if(nums[i] > nums[j]){
    //                 count ++;
    //             }
    //         }
    //         ans.add(count);
    //     }
    //     return ans;

    // }


// }





