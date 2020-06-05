//https://leetcode.com/problems/search-in-rotated-sorted-array/


//Key idea is to find the index at which the index is rotated
//Once that is found, do a binary search in the sub array leading upto the pivot
//and the subarray following the pivot
public class RotArr {

    //[5,1,2,3]
    //[8,9,10,4,5]
    public int search(int[] nums, int target) {
        if(nums.length == 0)
            return -1;

        if(nums.length == 1){
            if(nums[0] == target)
                return 0;
            
            return -1;
        }



        int start = 0;
        int end = nums.length - 1;
        int mid;
        //Set pivot to -1 so that it is an invalid index
        int pivot = -1;
        //[4,5,6,7,0,1,2]
        //[3,1]
        //[1,3]
        //find the pivot index where pivot either points to the index that has the highest value out of order
        while( start <= end){

            //At a case where the value at start index is smaller than end index
            //It implies that the values in between are in sorted order, at which point, break the loop
            //Optionally you may set the pivot index to start index here itself
            if(nums[start] <= nums[end]){
                break;
            }

            //if two consecutive indices are out of order, that definitely means, the smaller index is the pivot
            if(nums[start] > nums[start+1]){
                //Set pivot to smaller index ie start
                pivot = start;
                break;
            }

            //pick a mid point
            mid = (start+end)/2;

            //do a binary search in the range of (start, mid) until you find an index whose value is greater than its next value

            //if the value at start is larger than the value at mid, it means the pivot element is between start and mid
            if(nums[start] >= nums[mid]){
                //Assign end to mid so that we can search between start and mid

                //we assign end to mid and not mid-1 because there maybe a case where mid value < mid -1 value
                end = mid;   
            }

            //else search in the subarray starting at mid upto end
            else{
                start = mid;
            }
        }

        //if pivot hasn't been set above, it possibly means the array is sorted. 
        //So, set it at the end of the list
        if(pivot == -1){
            //set the pivot to the end of the list
            pivot = nums.length-1;

            //check if the value at pivot is target
            if(nums[pivot] == target)
                return pivot;
            
            //set start at 0 and end at last index
            start = 0;
            end = pivot;
        }

        //if a pivot is already set above
        else{
            //check if pivot is the target
            if(nums[pivot] == target)
                return pivot;
            
            //if the target is greater than the 0th index
            //it may lie between 0 and pivot, so set start and end to corresponding values
            if(target >= nums[0]){
                start = 0;
                end = pivot;
            }

            //otherwise search the other part of the array
            else{
                start = pivot+1;
                end = nums.length - 1;
            }
        }

        

        
        
        //standard binary search within the subarray
        while(start <= end){
            mid = (start+end)/2;
            if(target == nums[start]){
                return start;
            }

            if(target == nums[end]){
                return end;
            }

            if(target == nums[mid]){
                return mid;
            }

            if(target < nums[mid]){
                end = mid - 1;
            }

            else{
                start = mid+1;
            }

        }
        
        

        return -1;
    }

    public static void main(String args[]){
        RotArr ra = new RotArr();

        System.out.println(ra.search(new int[]{1,3}, 1));
        //ra.search(new int[]{4,5,6,7,0,1,2}, 0);
    }
}