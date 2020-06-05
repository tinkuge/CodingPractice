//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

public class RangeofTarget {
    public int[] searchRange(int[] nums, int target) {

        int start = 0;
        int end = nums.length-1;

        //target number index: initially set to -1
        int tind = -1;

        while(start <=  end){
            if(nums[start] == target){
                tind = start;
                break;
            }

            if(nums[end] == target){
                tind = end;
                break;
            }

            int mid = (start+end)/2;
            if(target == nums[mid]){
                tind = mid;
                break;
            }

            if(target < nums[mid]){
                start = start+1;
                end = mid - 1;
            }

            else{
                start = mid+1;
                end = end - 1;
            }
        }

        if(tind == -1){
            //System.out.println("li: "+ -1);
            //System.out.println("ri: "+ -1);
            return new int[]{-1,-1};
        }

        //search left of mid for any occurence of mid

        int li = tind;

        int lefst = 0;
        int lefend = tind - 1;

        while(lefst <= lefend){

            //absolute case where leftmost index is the left range
            if(nums[lefst] == target){
                if(lefst < li){
                    li = lefst;
                    break;
                }
            }
            int lmid = (lefst+lefend)/2;
            if(target == nums[lmid]){
                if(lmid < li){
                    li = lmid;
                }
                lefst = lefst+1;
                lefend = lmid - 1;

            }

            else if(target < nums[lmid]){
                lefst = lefst+1;
                lefend = lmid;
            }

            else{
                lefst = lmid+1;
            }

        }

        //search right of mid for any occurence of mid

        int rst = tind+1;
        int rend = nums.length-1;
        int ri = tind;

        while(rst <= rend){
            if(target == nums[rend]){
                if(rend > ri){
                    ri = rend;
                    break;
                }
            }

            int rmid = (rst+rend)/2;
            if(target == nums[rmid]){
                if(rmid > ri){
                    ri = rmid;
                    
                }

                rst = rmid+1;
                rend = rend - 1;
            }

            else if(target > nums[rmid]){
                rst = rmid+1;
                rend = rend - 1;
            }

            else{
                rend = rmid - 1;
            }

        }

        //.out.println("li: "+li);
        //System.out.println("ri: "+ri);

        return new int[]{li, ri};
        
    }

    public static void main(String[] args) {
        RangeofTarget rt = new RangeofTarget();

        rt.searchRange(new int[]{5,5,5,5,6}, 5);
        //cases:
        //unique values: straight up binary search
        //repeating values: repeated binary search even after finding the target value
    }
}