import java.util.*;
public class LongPal {

    public static void main(String args[]){
        String s = "forgeeksskeegfor";
        String max = findLong(s);
        System.out.println("Max sub length: "+ max.length());
        System.out.print(max);
    }

    static String findLong(String s){

        // int maxlen = 0;
        // //String maxs = "";
        // int subcount = 0;
        // int palcount = 0;
        // HashMap<String, Boolean> hm = new HashMap<>();
        //int[][] memo = new int[s.length()][s.length()];

        argu a = new argu(s);
        int len = a.str.length();
        
        //a = findLength(0, len - 1, a);

        for(int i = 0; i < s.length(); i++){
            for(int j = i+1; j <=s.length();j++){
                a = findLength(i, j - 1, a);
            }
        }
        //findLength(0, s.length() - 1, s, memo);



        // for(int i = 0; i < s.length();i++){
        //     for(int j = i+1; j <= s.length(); j++){

        //         String sub = s.substring(i,j);
        //         subcount++;
                
        //         if(!hm.containsKey(sub)){
        //             boolean res = isPalindrome(sub);
        //             palcount++;
        //             hm.put(sub, res);
        //             if(res){
        //                 if(sub.length() > maxlen){
        //                     maxs = sub;
        //                     maxlen = sub.length();
        //                 }
        //             }
        //         }
                
        //     }
        // }
        String palstr = a.str.substring(a.maxs, a.maxe+1);

        System.out.println("Max length palindrome: "+ (a.maxe - a.maxs +1));
        System.out.println("Palindrome String: "+ palstr);
        return palstr;

        //return maxs;
    }

    static argu findLength(int s, int e, argu a){

        //base case
        if(s == e){
            a.memo[s][e] = 1;
            return a;
        }

        //basecase
        if(s+1 == e){
            if(a.str.charAt(s) == a.str.charAt(e)){
                a.memo[s][e] = 1;
                if(e - s +1 > (a.maxe - a.maxs +1)){
                    a.maxs = s;
                    a.maxe = e;
                }
                return a;
            }
            else{
                a.memo[s][e] = -1;
                return a;
            }
            
        }
        //e cant be outside the bounds

        //if that position is uninitiated and s != e (implicit from previous if statement)
        if(a.memo[s+1][e-1] == 0){
            //check if the memo at s+1 and e-1 is filled

            a = findLength(s + 1, e - 1, a);
            if(a.str.charAt(s) == a.str.charAt(e) && a.memo[s+1][e-1] == 1){
                a.memo[s][e] = 1;
                if(e-s+1 > (a.maxe - a.maxs +1)){
                    a.maxs = s;
                    a.maxe = e;
                }
                return a;
            }

            else{
                a.memo[s][e] = -1;
                return a;
            }

           //if(memo[s - 1][e - 1] == 1 && str.charAt(s) == str)
        }

        else{
            if(a.memo[s+1][e-1] == 1 && (a.str.charAt(s) == a.str.charAt(e))){
                a.memo[s][e] = 1;
                if(e-s+1 > (a.maxe - a.maxs +1)){
                    a.maxs = s;
                    a.maxe = e;
                }
                return a;
            }

            else{
                a.memo[s][e] = -1;
                return a;
            }
        }


    }

    // static boolean isPalindrome(String s){
    //     System.out.println(s);
    //     int n = s.length();
    //     for(int i = 0; i < n; i++){
    //         if(s.charAt(i) != s.charAt(n-i-1)){
    //             return false;
    //         }
    //     }

    //     return true;
    //     //return false;
    // }

}

class argu{

    String str;
    int[][] memo;
    int maxs;
    int maxe;
    argu(String s){
        str = s;
        memo = new int[str.length()][str.length()];
        maxs = 0;
        maxe = 0;
        for(int i = 0; i < str.length(); i++){
            memo[i][i] = 1;
        }
    }
}