//https://leetcode.com/problems/climbing-stairs/

public class ClimbingStairs {
    public static void main(String[] args) {
        ways(6);
    }

    static int ways(int n){
        //if only one step to top,only way to reach the top
        if(n == 1){
            return 1;
        }
        //if only two steps to top, two ways to reach the top
        //2 steps at a time. 1 step to next step and then another
        //step to the top
        if(n == 2){
            return 2;
        }

        //create an array with n+1 elements
        //stores the number of ways to the top
        //at any particular index
        int[] steps = new int[n+1];
        //nth element would be the top, so 0 ways
        steps[n] = 0;
        //one step before top would only have one way
        steps[n-1] = 1;
        //two steps before the top would have two ways
        steps[n-2] = 2;

        //Start off from third step from top and work down to 0th step
        for(int i = n-3; i > -1; i--){
            //current step's ways to the top
            //is the sum of number of ways from next step
            //and the step after that
            steps[i] = steps[i+1]+steps[i+2];
        }

        //return the value at step 0
        return steps[0];
    }
}
