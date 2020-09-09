import java.util.ArrayList;
import java.util.List;

class Numbers {
    static int compute_number_score(int number) {
        //split each number into individual digits and then evaluate each individual digit
        List<Integer> indDigits = new ArrayList<>();
    
        //num%10 = 1
        //num/10
        //while num > 0
        int score = 0;
        if(number % 7 == 0){
            score = score+1;
        }
        int prevDig = -1;
        int consecCount = 1;
        while(number > 0){
            int dig = number%10;

            //+2 for even digits
            if(dig%2 == 0){
                score = score+2;
            }

            //+4 for each 9
            if(dig == 9){
                score = score+4;
            }
            
            //check for conescutive 1s
            if(dig == 1){
                if(prevDig == 1){
                    score = score+5;
                }
            }
            
            //if a prev digit has already been encountered
            if(prevDig != -1){
                //if the prevdigit is 1 greater than the current one
                //(since we're coming from right to left)
                if(prevDig == dig+1){
                    //if we are evaluating the last number
                    if(number/10 == 0){
                        consecCount++;
                        score = score+ (consecCount*consecCount);
                        break;
                    }
                    else
                        consecCount++;
                }
    
                //if the digits aren't in sequence
                else{
                    //if its the last number to be evaluated
                    if(number/10 == 0){
                        //for the previous sequence
                        score = score+(consecCount*consecCount);
                        //for the current digit
                        score++;
                        break;
                    }

                    else{
                        score = score+(consecCount*consecCount);
                        consecCount = 1;
                    }
                }
            }
            prevDig = dig;
            indDigits.add(dig);
            number = number/10;
        }
        System.out.println(score);
        return score;
    }
    public static void main(String[] args) {
        compute_number_score(789);
    }
}