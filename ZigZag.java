
public class ZigZag {
    static String convert(String s, int numRows){
        int slen = s.length();
        int cols = (slen);
        //int counter = 0;
        //char[][] zg = new char[numRows][cols];
        boolean rec = false;
        StringBuilder[] lstr = new StringBuilder[numRows];

        for(int i = 0; i < lstr.length;i++){
            lstr[i] = new StringBuilder("");
        }


        int j = 0;  //rows
        int k = 0;  //columns

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            lstr[j].append(String.valueOf(c));
            if(j == numRows - 1){
                //zg[j][k] = c;
                
                j--;
                k++;
                rec = true;
            }

            else if(j == 0){
                //zg[j][k] = c;
                //lstr[j].concat(String.valueOf(c));
                j++;
                rec = false;
            }

            else if(j > 0 && j < numRows - 1 && rec == false){
                //zg[j][k] = c;
                j++;
            }

            else if(j > 0 && j < numRows - 1 && rec == true){
                //zg[j][k] = c;
                j--;
                k++;
            }

        }

        StringBuilder finstr = new StringBuilder();

        for(int a = 0; a < lstr.length; a++){
            finstr = finstr.append(lstr[a]);
        }
        String fstr = finstr.toString();
        System.out.println("Final String: "+ fstr);



        return fstr;
    }

    public static void main(String args[]){
        convert("PAYPALISHIRING",
        3);
    }
}