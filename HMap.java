import java.util.*;
public class HMap {
    public static void main(String[] args) {
        String[] queryType = {"insert", "insert", "addToValue", "addToKey", "get"};
        int[][] qry = new int[][]{{1,2}, {2,3}, {2},{1},{3}};
        hashMap(queryType, qry);
    }
    static long hashMap(String[] queryType, int[][] query) {
        long res = 0;
        
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < queryType.length; i++){
            String curQry = queryType[i];
            if(curQry.equals("insert")){
                int[] args = query[i];
                hm.put(args[0], args[1]);
            }
            
            else if(curQry.equals("get")){
                int key = query[i][0];
                if(hm.containsKey(key)){
                    res = res+hm.get(key);
                }
            }
            
            else if(curQry.equals("addToKey")){
                int kinc = query[i][0];
                Set<Integer> kset = hm.keySet();
                Integer[] setint = new Integer[kset.size()];
                kset.toArray(setint);
                //int[] varr = hm.values();
                //kset.len == varr.len
    
                HashMap<Integer, Integer> upmap = new HashMap<>();
                for(int j = 0; j < setint.length; j++){
                    //check if map contains?
                    upmap.put(setint[j]+kinc, hm.get(setint[j]));
                }
                
                hm = upmap;
            }
            
            else{
                //add to value
                int incval = query[i][0];
                Set<Integer> kset = hm.keySet();
                Integer[] setint = new Integer[kset.size()];
                HashMap<Integer, Integer> upmap = new HashMap<>();
                kset.toArray(setint);
                for(Integer k: setint){
                    int oval = hm.get(k);
                    upmap.put(k, oval+incval);
                }
                
                hm = upmap;
                
            }
        }
        
        return res;
    }
}
