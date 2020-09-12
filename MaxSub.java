//https://medium.com/@schwiftmaster/intuition-for-kadanes-algorithm-maximum-sub-array-sum-f42bec0251d2
//https://leetcode.com/problems/maximum-subarray/
public class MaxSub {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, -1, -3, -4, -1, -2, -1, -5, 0};
        //maxSubArray(nums);
        altmax(nums);
    }

    static void altmax(int[] nums){
        //Overall sum holds the max of contiguous sum
        int overallSum = 0;
        //sum of elements up to current element
        int sumUpto = 0;

        //max to account for all negative array
        //Start off with first element
        int max = nums[0];

        for(int i = 0; i < nums.length; i++){
            //check if adding sumUpto to current element 
            //would make the resultant sum less than the current element
            //This would mean sumUpto is negative, since it is taking away
            //from the value of current element
            //At such a point, reset the sumUpto to current element.
            int res = nums[i]+sumUpto;
            if(res < nums[i]){
                sumUpto = nums[i];
            }

            //if the resultant sum value does not dip below the current 
            //assign it to sumUpto. This does not mean that sumUpto will increase
            //in value from its previous value since the current element could be
            //negative, at which point sumUpto will decrease. The above condition
            //just makes sure that sumUpto won't dip below the current element's value
            else{
                sumUpto = sumUpto+nums[i];
            }

            //At every step, check if the current sumUpto exceeds
            //overallSum, in which case, sumUpto becomes the overallSum max
            overallSum = Math.max(overallSum, sumUpto);

            //Keeps checking for max of the elements in the array
            //Useful in case when all the elements are negative
            max = Math.max(nums[i], max);
        }

        //if the overallSum is zero, it means either all are negative numbers
        //which does not budge overallSum from its initial value of zero
        //In this case, return the max of all elements
        //Another case is when the sum any subarray results in zero
        //At that point too, max of elements is the valid answer
        if(overallSum == 0){
            System.out.println(max);
        }
        else
            System.out.println(overallSum);
    }

    static int maxSubArray(int[] nums) {
        int bsum = 0;
        int csum = 0;

        //Tracks the max number in case array is full of -ve numbers
        int max = nums[0];


        for(int i: nums){
            int res = csum+i;
            //Basically reset csum to 0 if csum+i goes negative
            csum = Math.max(0, res);
            bsum = Math.max(bsum, csum);

            max = Math.max(i, max);
        }

        //bsum stays zero in case the array is full of negatives
        if(bsum == 0){
            //In this case, simply return the max of -ves
            System.out.println(max);
            return max;
        }

        System.out.println(bsum);
        //if bsum is positive, return bsum instead
        return bsum;
    }

    
}
