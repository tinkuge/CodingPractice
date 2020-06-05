//https://leetcode.com/problems/implement-strstr

//String.substring(int startindex, int endindex)
//endindex is exclusive while calculating substring. So, it can be equal to the length (which is the upper bound)

public class Strstr {
    public int strStr(String haystack, String needle) {
        //int counter = 0;
        int nlen = needle.length();
        int hlen = haystack.length();
        if(nlen > hlen){
            System.out.println("Larger than og");
            return -1;
        }
        
        for(int i = 0; i < hlen; i++){
            //
            if(i+nlen > hlen){
                System.out.println("Out of bounds");
                return -1;
            }

            if((haystack.substring(i, i+nlen)).equals(needle))
                return i;
        }
        return -1;
    }

    public int altstrStr(String haystack, String needle){
        int nlen = needle.length();
        int hlen = haystack.length();
        //StringBuilder sb;
        if(nlen > hlen){
            System.out.println("Larger than og");
            return -1;
        }

        for(int i = 0; i < hlen; i++){
            if(i+nlen > hlen){
                return -1;
            }
            //sb = new StringBuilder();
            int count = 0;
            for(int k = 0, j =  i; k < nlen; j++,k++){
                if(haystack.charAt(j) != needle.charAt(k)){
                    break;
                }
                else
                    count++;
            }

            if(count == nlen){
                return i;
            }
        }

        return -1;
    }

    public static void main(String args[]){
        Strstr ss = new Strstr();
        System.out.println(ss.altstrStr("mississippi", "issi"));
        //System.out.println(ss.strStr("hello", "Helloooo"));
    }
}