//https://leetcode.com/discuss/interview-experience/691128/postmates-interview-process-and-questions
//Q1

public class Diff {
    public static void main(String[] args) {
        calcDiff(325);
    }

    static void calcDiff(int x){
        if(x >= 0 && x < 10){
            System.out.println("Sum: "+x);
            System.out.println("Mul: "+x);
        }
        int mul = 1;
        int sum = 0;
        while(x > 0){
            int rem = x%10;
            sum = sum+rem;
            mul = mul*rem;
            x = x/10;
        }
        System.out.println("Sum: "+sum);
        System.out.println("Mul: "+ mul);
        System.out.println("Diff: "+(sum - mul));
    }
}