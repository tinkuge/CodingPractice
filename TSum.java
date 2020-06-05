import java.util.*;
//https://leetcode.com/problems/3sum/

public class TSum {
    static List<List<Integer>> altSum(int[] nums){
        List<List<Integer>> al = new ArrayList<>();
        int n = nums.length;

        //Sort the array for implicit sorted properties
        Arrays.sort(nums);
        
        //make sure i ends two indices behind overall length to make room for j and k
        for(int i = 0; i < n-2; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            else{
                int inum = nums[i];
                //store the target number that needs to be achieved by nums[j]+nums[k]
                int target  = -inum;
                //start off j from just after i and k from the end of the array
                int j = i+1;
                int k = n-1;

                //j and k need to be from distinct indices. As soon as j==k, stop.
                while(j < k){
                    //if the sum equal the target
                    if(nums[j]+nums[k] == target){
                        ArrayList<Integer> arl = new ArrayList<>();
                        arl.add(inum);
                        arl.add(nums[j]);
                        arl.add(nums[k]);
                        al.add(arl);
                        //After the values are added to the list, increase the j and k indices
                        j++;
                        k--;

                        //check if the previous j value and the current j value arethe same. If so, skip past it until 
                        //the previous j and current j are not equal
                        while(j < k && nums[j-1] == nums[j]){
                            j++;
                        }

                        //similarly, since k index is decreased, check if the current k value is the same as the previous k value 
                        //where previous k value is at k+1
                        while(j < k && nums[k+1] == nums[k]){
                            k--;
                        }
                    }

                    //Using the implicit sorted property of the array, check how close to the target is the sum of j and k
                    //since value at j would always be less than or equal to value at k and sum is less than target
                    //it would mean value at j is too small to reach the target. So, increment the j index
                    else if(nums[j]+nums[k] < target){
                        j++;
                    }
                    //otherwise, increment k
                    else
                        k--;
                }
            }
        }

        return al;
    }

    public static void main(String args[]){
        int nums[] = {-1, 0, 1, 2, -1, -4};
        altSum(nums);
    }
}