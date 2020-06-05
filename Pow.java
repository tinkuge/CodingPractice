public class Pow {
    public double myPow(double x, int n) {

        
        if(n == 0){
            return 1;
        }

        if(n == 1)
            return x;

        if(n == -1)
            return 1/x;

        //if n is min value, it means n is negative => 1/x^n but 
        if(n == Integer.MIN_VALUE) return 0;

        if(n < 0){
            n = -n;
            x = 1/x;
        }

        //when power is even
        if(n%2 == 0){
            //Same as mypow(x, n/2)*mypow(x, n/2);
            return myPow(x*x, n/2);
        }

        //when power is odd
        return x*myPow(x*x, n/2);
    }

    public static void main(String[] args) {
        Pow p = new Pow();
        p.myPow(-3.2,5);
    }
}