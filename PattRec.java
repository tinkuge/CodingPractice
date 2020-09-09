public class PattRec {
    public static void main(String[] args) {
        //String line = ";bcdefbcbebc|abcdebcfgsdf|cbdbesfbcy|1bcdef23423bc32";
        String line = "aa;a|aa";
        String splitInput[] = line.split(";");
        String pattern = splitInput[0];
        String blobs = splitInput[1];
        doSomething(pattern, blobs);
    }

    static void doSomething(String pattern, String blobs){
        
        String[] indBlobs = blobs.split("\\|");
        if(pattern.length() == 0){
            int numBlobs = indBlobs.length;
            int i = 0;
            while(i < numBlobs){
                System.out.print("0|");
                i++;
            }
            //Print 0 in place of final sum of patterns
            System.out.println("0");
            return;
        }

        int patlen = pattern.length();
        int sum = 0;
        for(String s: indBlobs){
            int slen = s.length();
            if(slen < patlen){
                System.out.print("0|");
            }

            else if(slen == patlen){
                int count = 0;
                if(s.equals(pattern)){
                    count++;
                    sum++;
                }
                System.out.print(count+"|");
            }
            else{
                int i = 0;
                int count = 0;
                while(i+patlen <= slen){
                    String currSubStr = s.substring(i, i+patlen);
                    if(currSubStr.equals(pattern)){
                        count++;
                        sum++;
                    }
                    i++;
                }

                System.out.print(count+"|");
            }
        }
        
        System.out.print(sum);
        return;
    }
}