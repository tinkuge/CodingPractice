import java.util.*;

public class NumCon {
    public static List<String> letterCombinations(String digits) {
        if(digits.length() == 0)
            return new ArrayList<String>();

        HashMap<String, String[]> hm = new HashMap<>();
        ArrayList<String> al  = new ArrayList<>();
        hm.put("0", new String[]{""});
        hm.put("1", new String[]{""});
        hm.put("2", new String[]{"a", "b", "c"});
        hm.put("3", new String[]{"d", "e", "f"});
        hm.put("4", new String[]{"g", "h", "i"});
        hm.put("5", new String[]{"j", "k", "l"});
        hm.put("6", new String[]{"m","n", "o"});
        hm.put("7", new String[]{"p", "q", "r", "s"});
        hm.put("8", new String[]{"t","u", "v"});
        hm.put("9", new String[]{"w", "x", "y", "z"});

        String[] nums = digits.split("");

        String res = "";
        
        combi(nums, 0, hm, al, res);

        return al;
    }

    static void combi(String[] nums, int i, HashMap<String, String[]> hm, ArrayList<String> al, String res){
        if(i == nums.length - 1){
            String[] fin = hm.get(nums[i]);
            for(int a = 0; a < fin.length; a++){
                //res = ;
                al.add(res+fin[a]);
            }

            return;
        }

        String[] s = hm.get(nums[i]);
        
        for(int a = 0; a < s.length; a++){
            //res = res+s[a];
            String ex = res+s[a];
            combi(nums, i+1, hm, al, ex);
        }
        
    }

    static void bfs(){
        
    }


    public static void main(String args[]){
        letterCombinations("23");
    }
}