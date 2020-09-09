import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String[] s = {"xx","xy","d", "b"};
        Arrays.sort(s, (a,b) -> a.compareTo(b));
        System.out.println("Complete");
    }
    static double truncateDouble(double dnum){
        dnum = dnum*100;
        int inum = (int) dnum;
        dnum = (double) inum;
        return dnum/100;
    }
}