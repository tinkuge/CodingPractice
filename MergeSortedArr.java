public class MergeSortedArr {
    public static void main(String[] args) {
        int[] nums1 = {3,5,0,0,0,0,0};
        int[] nums2 = {2,4,5,6,8};
        merge(nums1, 2, nums2, nums2.length);
    }


    /**
     * 
     * @param nums1
     * @param m
     * @param nums2
     * @param nsecond 
     * 
     * Start from end of second array and first array
     * If second array element under consideration is greater
     * than first array element, put it at end of the first array, other wise
     * copy first array element to the end. Don't worry about the
     * first array element also being in its original place.
     * It gets overwritten in the subsequent comparisons.
     * Keep going until both indexes are >=0
     * 
     * However, there is a case when there is a leftover element
     * in the second array that has not been compared to first array
     * elements at all. In this case, simply copy the remaining element
     * to the first array.
     */

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //this keeps track fo where to copy the greater element
        //set to last index of nums1 (nums1.length-1)
        int tind = m+n-1;

        //set to last set index in nums1
        int i1 = m-1;
        //set to last index of nums2
        int i2 = n-1;
        
        //keep going until one of the indices runs out
        while(i1 >= 0 && i2 >= 0){
            
            if(nums2[i2] >= nums1[i1]){
                //set element at tind to num2[i2]
                nums1[tind] = nums2[i2];
                i2--;
                tind--;
            }

            //otherwise, set the element at tind
            //to element at i1
            else if(nums1[i1] > nums2[i2]){
                nums1[tind] = nums1[i1];
                tind--;
                i1--;
            }
        }

        //copy rest of uncompared elements
        //to nums1
        while(i2 >= 0){
            nums1[i2] = nums2[i2];
            i2--;
        }

    }
}
