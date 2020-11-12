import java.util.ArrayList;
import java.util.List;

//1431. Kids With the Greatest Number of Candies

public class MostCandy {
    public static void main(String[] args) {
        int[] candies = {2,3,5,1,3};
        int extraCandies = 3;
        kidsWithCandies(candies, extraCandies);
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        
        int max = 0;

        for(int c: candies){
            if(c >= max)
                max = c;
        }
        var res = new ArrayList<Boolean>();
        for(int c: candies){
            if(c+extraCandies >= max){
                res.add(true);
            }

            else{
                res.add(false);
            }
        }
        return res;
    }
}
