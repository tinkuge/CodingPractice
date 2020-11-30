public class Sqrt {
    public static void main(String[] args) {
        calcSqrt(4);
    }

    /**
     * The square root of a number is at most half of it
     * (excluding 0 and 1). We binary search the space between
     * 1 and half of the number. The goal is to find the greatest
     * number possible whose square is less than or equal to
     * original number.
     * @param x
     * @return squareroot of x
     */
    static int calcSqrt(int x){


        if(x == 0 || x == 1){
            return x;
        }

        
        int min = 1;
        int max = x/2;

        //The idea is to shrink the range
        //of min and max until we find the
        //greatest number whose square is
        //less than or equal to number*number
        while(min <= max){
            //int mid = (min+max)/2;
            //to prevent int overflow
            //rewrite (min+max)/2 as
            //min + (max-min)/2
            // which would be (min + max/2 - min/2)
            int mid = min+(max-min)/2;
            //int sqr = mid*mid;
            //mid*mid might cause overflow
            //In the comparison below,
            //mid*mid > x can be rewritten as
            //mid > x/mid by taking one of the mid
            //to denominator of x

            //if mid*mid > x, it means
            // we overshot the squareroot value
            //make max smaller than mid and try again
            if(mid > x/mid){
                max = mid -1;
            }

            else{
                min = mid+1;
            }
            
        }
        //return the right most element
        //that is likely to be close to actual
        //squareroot
        return max;
        
    }
    
}
