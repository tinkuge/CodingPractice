
public class Convert {
    public static int myAtoi(String str) {
        str = str.trim();
        boolean flag = false;
        long fin = 0;
        boolean neg = false;

        char fchar = str.charAt(0);
        int fval = (int) fchar;
        if(!(fval >= 48 && fval <= 57)){
            if(fval != 43 && fval != 45)
                return 0;
        }
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int aval = (int) c;

            if((aval >= 48 && aval <=57)){
                if(!flag){
                    fin = (Character.getNumericValue(c));
                    flag = true;
                }

                else{
                    fin = fin*10+ (Character.getNumericValue(c));
                    if(fin > Integer.MAX_VALUE){
                        if(neg)
                            return Integer.MIN_VALUE;
                        else
                            return Integer.MAX_VALUE;
                    }
                    
                }
            }

            else if(aval == 43){
                if(!flag){
                    flag = true;
                }
                else
                    break;
            }

            else if(aval == 45){
                if(!flag){
                    flag = true;
                    neg = true;
                }

                else
                    break;
            }

            else{
                if(flag){
                    break;
                }

            }
        }

        if(neg){
            System.out.print("-"+fin);

            long fmin = -1*fin;
            if(fmin < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            
            return (int) fmin;
        };

        if(fin > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        System.out.print(fin);

        return (int) fin;
    }

    public static void main(String args[]){
        myAtoi("9223372036854775808");
    }
}