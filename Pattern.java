
public class Pattern {

    boolean isMatch(String s, String p){

        if(p.length() == 0){
            return s.length() == 0;
        }

        if(s.length() == 0)
            return false;

        if(p.length() == 1){
            if((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')){
                return false;
            }

            else{
                return true;
            }
        }

        
        return false;
    }
}