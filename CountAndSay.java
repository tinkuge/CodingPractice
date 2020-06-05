//https://leetcode.com/problems/count-and-say/
public class CountAndSay {
    public String countAndSay(int n) {
        
        String s = "1";
        //char prev = s.charAt(0);
        if(n == 1){
            return s;
        }
        String res = "";
        for(int i = 1; i < n; i++){
            int count = 0;
            for(int j = 0; j < s.length(); j++){
                if(j+1 < s.length()){
                    if(s.charAt(j) == s.charAt(j+1)){
                        count++;
                    }

                    else{
                        count++;
                        res = res+String.valueOf(count)+Character.toString(s.charAt(j));
                        count = 0;
                    }
                }

                else{
                    count++;
                    res = res+String.valueOf(count)+Character.toString(s.charAt(j));
                }
            }

            s = res;
            res = "";
            

            
        }

        return s;
    }

    public static void main(String args[]){
        CountAndSay cs = new CountAndSay();
        cs.countAndSay(7);
    }
}