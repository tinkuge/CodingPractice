public class FirstMissing {
    public int firstMissingPositive(int[] nums) {
        
        if(nums.length == 0){
            return 1;
        }

        if(nums.length == 1){
            if(nums[0] == 1){
                return 2;
            }

            else{
                return 1;
            }
        }
        //replace negatives and out of bounds with 1

        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0 || nums[i] > nums.length){
                nums[i] = nums.length+1;
            }
        }

        //1,2,4

        for(int i = 0; i < nums.length;){
            
            int el = nums[i];
            if(el > 0 && el < nums.length && nums[el] > 0){
                nums[i] = nums[el];
                nums[el] = -el;
            }

            else if(el == nums.length && nums[0] > 0){
                nums[i] = nums[0];
                nums[0] = -el;
            }

            else{
                
                i++;
            }
        }

        for(int i = 1; i < nums.length; i++){
            if(nums[i] > 0){
                System.out.println(i);
                return i;
            }
        }

        if(nums[0] < 0){
            System.out.println(nums.length+1);
            return nums.length+1;
        }

        System.out.println(nums.length);
        return nums.length;
    }

    public static void main(String[] args) {
        FirstMissing fm = new FirstMissing();
        fm.firstMissingPositive(new int[]{2,2});
        fm.firstMissingPositive(new int[]{1,2,0});
        fm.firstMissingPositive(new int[]{3,4,-1,1});
        fm.firstMissingPositive(new int[]{7,8,9,11,12});
    }
}