
//https://leetcode.com/problems/remove-duplicates-from-sorted-array/
//edge cases
//[x,x,x,x,x] return 1;
//[1,2,3,4,5]


public class removeDup {
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 1)
            return 1;
        int count = 1;
        //nums is sorted array
        int nlen = nums.length;
        int i = 0;
        while(i < nlen){
            int j = i+1;

            if(j == nlen){
                break;
            }
            while((j < nlen) && (nums[i] == nums[j]|| nums[i] > nums[j])){
                j++;
                //if(nums[j] > nums[j+1]) j++;
            }

            //while(j+1 < nlen && nums[j] > nums[j+1]) j++;

            
            
            if(j-i > 1){
                int temp = nums[i+1];
                nums[i+1] = nums[j];
                nums[j] = temp;
                count++;
            }
            else{
                count++;
            }

            i++;
        }
        System.out.println(count);
        return count;
    }

    public static int altremoveDuplicates(int[] nums){
        int nlen = nums.length;
        int i = 0;
        int j = 1;
        while(j < nlen){
            if(nums[i]!=nums[j]){
                nums[i+1] = nums[j];
                i++;
            }
            j++;

        }

        System.out.println(i+1);
        return i+1;
    }

    public static void main(String args[]){
        altremoveDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
    }

}