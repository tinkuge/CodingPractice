import java.math.BigInteger;

/*
You have an array nums. We determine two functions to perform on nums. In both cases, n is the length of nums:

fi(nums) = nums[0] · nums[1] · ... · nums[i - 1] · nums[i + 1] · ... · nums[n - 1]. (In other words, fi(nums) is the product of all array elements except the ithf.)
g(nums) = f0(nums) + f1(nums) + ... + fn-1(nums).
Using these two functions, calculate all values of f modulo the given m. Take these new values and add them together to get g. You should return the value of g modulo the given m.

Example

For nums = [1, 2, 3, 4] and m = 12, the output should be
productExceptSelf(nums, m) = 2.

The array of the values of f is: [24, 12, 8, 6]. If we take all the elements modulo m, we get:
[0, 0, 8, 6]. The sum of those values is 8 + 6 = 14, making the answer 14 % 12 = 2.


*/

/**
 * ProdSelf
 */
public class ProdSelf {

    public static void main(String[] args) {
        int[] nums = new int[]{27, 37, 47, 30, 17, 6, 20, 17, 21, 43, 5, 49, 49, 50, 20, 42, 45, 1, 22, 44};
        productExceptSelf(nums, 12);
    }

    static int productExceptSelf(int[] nums, int m) {

        int nlen = nums.length;
        //Initialize the fprod to 1
        BigInteger fprod = new BigInteger("1");
        //calculate the product all elements in nums
        for(int x: nums){
            fprod = fprod.multiply(BigInteger.valueOf(x));
        }

        System.out.println("Fprod:");
        System.out.println(fprod);
        
        //define a farr so that it can hold
        //all the products of fi(nums)
        int[] farr = new int[nums.length];
        System.out.println("Except Product: ");

        for(int i = 0; i < nlen; i++){
            //divide the fprod by nums[i] to get
            //product except self. Cast to int, just in case

            BigInteger inter =  fprod.divide(BigInteger.valueOf((long)nums[i]));
            //individually apply modulo to prod except self
            BigInteger inmod = inter.mod(BigInteger.valueOf((long)m));
            farr[i] = inmod.intValue();
            System.out.println(farr[i]);
        }
        
        //Now we calculate g(nums)
        int gsum = 0;
        
        for(int fi: farr){
            //preemptively mod existing gsum
            int gmod = gsum%m;
            //mod the fi for good measure
            int fimod = fi%m;
            //add modulated fi to current gsum
            gsum = (gmod+fimod)%m;
        }
        System.out.println("GSum:");
        System.out.println(gsum);
        return (int) gsum%m;
    }
}