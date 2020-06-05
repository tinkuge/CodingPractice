
public class PrintBinary {
    static void printAllBinary(int nd){
        String s = "";
        helper(s, nd);
    }

    static void helper(String s, int nd){
        if(s.length() == nd){
            System.out.println(s);
            return;
        }

        String a = s+"1";
        String b = s+"0";
        helper(a, nd);
        helper(b, nd);
    }

    public static void main(String args[]){
        printAllBinary(3);
    }


}