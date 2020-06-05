
public class AltPal {
    public static void main(String args[]){
        //String s = "forgeeksskeegfor";
        String s = "banana";
        String max = findLong(s);
        System.out.println("Max sub length: "+ max.length());
        System.out.print(max);
    }

    static String findLong(String s){
        if(s.length() == 0 || s.length() == 1)
            return s;
        
        if(s.length() == 2){
            if(s.charAt(0) == s.charAt(1)){
                return s;
            }

            else{
                return s.substring(0,1);
            }
        }
        String longest = "";

        int maxs = -1;
        int maxe = -1;
        int longl = 0;
        for(int i = 0; i < s.length(); i++){
            int j = i - 1;
            int k = i + 1;
            
            while(j >= 0 && s.charAt(j) == s.charAt(i)){
                j--;
            }

            while(k < s.length() && s.charAt(k) == s.charAt(i)){
                k++;
            }

            while(j >= 0 && k < s.length()){
                if(s.charAt(j) == s.charAt(k)){
                    j--;
                    k++;
                }

                else{                    
                    break;
                }
            }

            int curs = j+1;
            int cure = k-1;
            if(cure-curs+1 > longl){
                maxe = cure;
                maxs = curs;
                longl = cure-curs+1;
            }

        }

        String sub = s.substring(maxs, maxe+1);
        System.out.println("Long sub palindrome "+ sub);
        return sub;
    }

}