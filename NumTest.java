import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
class NumTest {
    public static void main(String args[]){
        //System.out.println(0.05 == 0.05);
        calculateChange(8.75, 50);


    }

    public static void calculateChange(double purchasePrice, double cash) {
        // Access your code here. Feel free to create other classes as required
        if(cash < purchasePrice){
          System.out.println("ERROR");
          return;
        }
        
        if(cash == purchasePrice){
          System.out.println("Zero");
          return;
        }
        StringChangeConversion scc = new StringChangeConversion();
        //HashMap<Float, String> numToText = new HashMap<>();
        double[] denom = scc.denom;
        
        double diff = cash - purchasePrice;
        BigDecimal bd = new BigDecimal(diff).setScale(2, RoundingMode.HALF_UP);
        diff = bd.doubleValue();
        for(int i = 0; i < denom.length;){
          double currdenom = denom[i];
          //System.out.println(currdenom == diff);
          if(denom[i] == diff){
            System.out.print(scc.numToText.get(denom[i]));
            return;
          }
          
          if(denom[i] < diff){
            System.out.print(scc.numToText.get(denom[i])+ ", ");
            diff = diff - denom[i];
            BigDecimal bdec = new BigDecimal(diff).setScale(2, RoundingMode.HALF_UP);
            diff = bdec.doubleValue();
            if(denom[i] > diff){
              i++;
            }
          }

          else{
              i++;
          }
        }
        
    }

    static double truncateDouble(double dnum){
        dnum = dnum*100;
        int inum = (int) dnum;
        dnum = (double) inum;
        return dnum/100;
    }


}

class StringChangeConversion{
    HashMap<Double, String> numToText;
    double[] denom;
    StringChangeConversion(){
      denom = new double[]{50,20,10,5,2,1,.50,.20,.10,.05,.02,.01};
      numToText = new HashMap<>();
      numToText.put(50.0, "Fifty Pounds");
      numToText.put(20.0, "Twenty Pounds");
      numToText.put(10.0, "Ten Pounds");
      numToText.put(5.0, "Five Pounds");
      numToText.put(2.0, "Two Pounds");
      numToText.put(1.0, "One Pound");
      numToText.put(0.5, "Fifty Pence");
      numToText.put(0.2, "Twenty Pence");
      numToText.put(0.1, "Ten Pence");
      numToText.put(0.05, "Five Pence");
      numToText.put(0.02, "Two Pence");
      numToText.put(0.01, "One Pence");
    }
  }