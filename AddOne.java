//https://leetcode.com/problems/plus-one

public class AddOne {
    public static void main(String[] args) {
        plusOne(new int[]{9,0,0,9});
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;

        boolean carry = false;
        for(int i = n-1; i > -1; i--){
            if(digits[i] == 9){
                digits[i] = 0;
                carry = true;
            }
            else{
                if(digits[i] == 9 && carry){
                    digits[i] = 0;
                }

                else{
                    digits[i] = digits[i]+1;
                    carry = false;
                    return digits;
                }
            }
        }

        int[] res = new int[n+1];
        res[0] = 1;
        return res;

        
    }
}
