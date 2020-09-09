import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
////https://leetcode.com/discuss/interview-question/542850/postmates-senior-software-engg-sf-hq-mar-2020-reject
public class TimeWindow {
    public static void main(String[] args) {
        int[] twin = {1,30};
        int[][] del = {{1, 5}, {17, 21}, {30, 60},{11, 16}, {10, 25}};
        //int[][] del = {{1,2},{1,3},{2,3},{3,4}};
        altWindow(twin, del);
        maxwindow(twin, del);
    }

    static void maxwindow(int[] twin, int[][] delv){
        int tmin = twin[0];
        int tmax = twin[1];
        //Tuple root = new Tuple(tmin, tmax);
        ArrayList<Tuple> poten = new ArrayList<>();

        //sort the array. earlier starting points first. if two
        //intervals have same start, pick the one with earlier end time.

        for(int[] i : delv){
            Tuple t = new Tuple(i[0], i[1]);
            //if the delivery window falls in the timeframe
            if(t.fir >= tmin && t.sec <= tmax){
                //add it to arraylist
                poten.add(t);
            }
        }
        //CustComp cc = new CustComp();
        Collections.sort(poten, new CustComp());
        ArrayList<Tuple> def = new ArrayList<>();
        int count = 0;
        int pmax = -1;
        //int cmin = -1;
        for(Tuple i: poten){
            if(pmax > -1){
                if(i.fir >= pmax){
                    count++;
                    pmax = i.sec;
                }
            }

            else{
                count++;
                pmax = i.sec;
            }
        }
        System.out.println(count);
    }

    static void altWindow(int[] twin, int[][] delv){
        //Sort the array by the end time of each interval
        Arrays.sort(delv, (a, b) -> a[1] - b[1]);
        //Assuming the intervals are non-negative
        int count = 0;
        //Set pmax to negative
        int pmax = -1;
        //Iterate through the delivery windows
        for(int i = 0; i < delv.length; i++){
            //Current intervals' start time
            int curmin = delv[i][0];
            //Current intervals' end time
            int curmax = delv[i][1];
            //First check if the interval falls in the time window specified
            //Ignore those that don't.
            if(curmin >= twin[0] && curmax <= twin[1]){
                
                /*
                if(pmax > -1){
                    //
                    if(curmin >= pmax){
                        count++;
                        pmax = curmax;
                    }
                }

                else{
                    count++;
                    pmax = curmax;
                }
                */
                //If current min is gte last interval's end
                if(curmin >= pmax){
                    //increment count
                    count++;
                    //set pmax to current end time
                    pmax = curmax;
                }
            }
        }

        System.out.println(count);
    }
}

class CustComp implements Comparator<Tuple>{
    public int compare(Tuple a, Tuple b){        
        return a.sec-b.sec;
    }
}

//class PrimComp implements Comparator

class Tuple{
    int fir = 0;
    int sec = 0;
    
    Tuple(int f, int s){
        fir = f;
        sec = s;
    }

}
