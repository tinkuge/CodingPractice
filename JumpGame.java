
public class JumpGame {

    
    public static void main(String[] args) {
        
        //int[] nums = new int[]{2,5,0,0};
        int[] nums = new int[]{1,0,1,0};
        //int[] nums = new int[]{3,2,1,0,4};
        //bruteforce solution
        //canJump(nums);
        //dynamic programming
       // optCanJump(nums);
        //greedy 
        greedyJump(nums);
    }

    private static boolean greedyJump(int[] nums) {

        if(nums.length == 1){
            return true;
        }
        

        if(nums[0] == 0)
            return false;
        
        
        int nlen = nums.length;
        if(nums[0] >= nlen-1)
            return true;
        //tells the smallest index that can reach
        //last index either directly or through other
        //indices. Objective is to get minreach to 0
        //which would mean the 0th index can reach
        //final index through a series of jumps.
        int minreach = nlen-1;

        //going backwards, check if the current index
        //plus its value can reach any index (or even overshoot)
        //that can reach final index
        for(int i = nlen - 2; i >= 0; i--){
            int vali = nums[i];
            //if the current index plus its value
            //can reach or overshoot the minimum index that can
            //reach final index, set the minreach to current index
            if(vali+i >= minreach)
                minreach = i;
        }

        //see if minreach is the starting index
        return minreach == 0;
    }

    private static boolean optCanJump(int[] nums) {

        if(nums.length == 1){
            return true;
        }

        int nlen = nums.length;
        //int penpos = nlen-2;
        //To track if a certain index can reach final position
        boolean[] reach = new boolean[nlen];

        //if the starting index value is >=
        //to final index, return true
        if(nums[0] >= nlen-1)
            return true;


        //Start from penultimate index and see if 
        //we can reach the last index from the current index
        //This includes reaching to all possible indexes
        //that can be reached with the current index
        for(int i = nlen-2; i >=0; i--){
            int vali = nums[i];
            //if the resultant value either reaches
            //final index or overshoots it, set the
            //corresponding boolean array index to true
            if(vali+i >= nlen-1)
                reach[i] = true;

            else{
                //dist represents the value at current index
                //which will be added to the index to see if we can reach 
                //either the final index or another index that can reach final index

                //See if we can reach any index from current index
                //with all possible values of dist since dist represents
                //the MAXIMUM jump distance. That means, jump distance can be
                //something less than dist if it means it can reach another index
                //that can reach last index
                for(int dist = vali; dist >= 1; dist--){
                    //add current distance to current index
                    int indj = (i+dist);
                    //if the resultant value either reaches
                    //final index or overshoots it, set the
                    //corresponding boolean array index to true
                    if(indj >= nlen -1){
                        reach[i] = true;
                        //if the current index is starting index,
                        //our job is done
                        if(i == 0)
                            return true;
                    }

                    //if the sum of current index plus current distance
                    //makes it possible to reach another index which can
                    //reach final index, set boolean index to true
                    else if(reach[indj]){
                        reach[i] = true;

                        if(i == 0)
                            return true;
                    }

                }
            }
        }

        return reach[0];
    }

    public static boolean canJump(int[] nums) {
        if(nums.length == 1){
            return true;
        }

        if(nums[0] == 0)
            return false;

        
        
        int n = nums.length;
        int val = nums[0];

        if(val >= n-1){
            return true;
        }

        for(int j = val; j > 0; j--){
            int jumpdist = 0+j;
            if(jumpdist >= n-1)
                return true;
            boolean ret = follow(nums, jumpdist);
            if(ret){
                return true;
            }
        }

        return false;
    }

    static boolean follow(int[] nums, int pos){
        if(pos != nums.length-1 && nums[pos] == 0){
            return false;
        }

        int maxdist = nums[pos];
        int n = nums.length;
        for(int i = maxdist; i > 0; i--){
            int jumpdist = pos+i;
            if(jumpdist >= n-1){
                return true;
            }
            boolean ret = follow(nums, jumpdist);
            if(ret)
                return true;
        }
        return false;
    }
}
