import java.util.ArrayList;

public class Reverse {
    public static int reverse(int x) {
        

        int res = 0;
        //int pow = 0;
        int fin = 0;
        ArrayList<Integer> ar = new ArrayList<>();
        while(x != 0){
            res = x%10;
            if((fin*10)+res > Integer.MAX_VALUE || (fin*10)+res < Integer.MIN_VALUE){
                return 0;
            }
            fin = fin*10+res;
            ar.add(res);
            //pow++;
            //ar.add(res);
            x = x/10;
        }

        int counter = ar.size() - 1;
        int i = 0;
        Integer[] barr = new Integer[ar.size()];
        ar.toArray(barr);
        

        System.out.println(fin);
        return fin;
    }

    public static void main(String args[]){
        reverse(431);
    }

}