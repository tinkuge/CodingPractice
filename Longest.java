
public class Longest {
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }

        StringBuilder sb = new StringBuilder("");
        boolean flag = false;

        //int j = 0;
        //Go through each character of first string and check if the rest of the strings have same corresponding character
        //if a string's length is less than current index, break the loop and return the prefix

        for(int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){

                if((strs[j].length() < i+1) || (strs[j].charAt(i) != c)){
                    flag = true;
                    break;
                }

                    
            }
            if(flag){
                System.out.println(sb.toString());
                return sb.toString();
            }
            sb.append(c);
        }

        System.out.println(sb.toString());

        return sb.toString();
    }

    public static void main(String args[]){
        String[] sarr = {"flow","fly","flower"};
        longestCommonPrefix(sarr);
    }
}