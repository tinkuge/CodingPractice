public class ShuffleArray {
    public static void main(String[] args) {
        
    }

    public static int[] shuffle(int[] nums, int n) {
        int hlen = nums.length/2;
        int[] shuffArr = new int[nums.length];

        int xi = 0;
        int yi = hlen;

        for(int i = 0; i < nums.length; i++){
            if(i%2 == 0){
                shuffArr[i] = nums[xi];
                xi++;
            }

            else{
                shuffArr[i] = nums[yi];
                yi++;
            }
        }
        return shuffArr;
    }
}
