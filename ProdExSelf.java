public class ProdExSelf {
    public static void main(String[] args) {
        productExceptSelf(new int[]{1, 2, 3, 4, 5});
    }


    //{              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
    //{ a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }
    public static int[] productExceptSelf(int[] nums) {

        int[] res = new int[nums.length];

        //Inititate the 0th position so we can
        //start from index 1
        res[0] = 1;
        //Start off from index 1 and calculate
        //product in the forward pass
        for(int i = 1; i < nums.length; i++){
            //Current index holds the product
            //of previous element and the product
            //calculated upto (but not including)
            //the previous element
            res[i] = res[i-1]*nums[i-1];
        }

        //Define the product array for reverse pass
        int[] res2 = new int[nums.length];
        //Initiate the last index to 1
        //so that we can deal with the elements
        //before it
        res2[nums.length - 1]  = 1;
        //start off from the penultimate position
        for (int i = nums.length - 2; i >= 0; i--) {
            //Product at current element is the
            //product at next element and 
            //the next element itself
            res2[i] = res2[i+1]*nums[i+1];
        }

        //Simply multiply the forward pass array
        //and the backward pass away
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i]*res2[i];
        }
        
        return res;
    }
}
