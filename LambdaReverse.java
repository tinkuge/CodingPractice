interface FuncInter{
    String strRev(String s);
}

class LambdaReverse{
    static String stringReverser(FuncInter fi, String s){
        return fi.strRev(s);
    }

    public static void main(String[] args) {
        String exStr = args[0];

        FuncInter fi = (String x) -> 
        {
            StringBuilder sb = new StringBuilder(x);
            sb = sb.reverse();
            x = sb.toString();
            return x;
        };

        String rev = stringReverser(fi, exStr);
        System.out.println(rev);

        String rev2 = stringReverser(fi, rev);
        System.out.println(rev2);
    }
}