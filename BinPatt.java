import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
//https://leetcode.com/discuss/interview-experience/691128/postmates-interview-process-and-questions
//question 2
public class BinPatt {
    public static void main(String[] args) {
        String s = "Thebeachwascrowdedwithsnowleopards";
        
        String pat = "1100";
        numMatches(pat,s);
    }

    static void numMatches(String pat, String s){
        Character[] vowels = {'a', 'e', 'i', 'o', 'u'};
        s = s.toLowerCase();
        //0 is vowel, 1 is consonant
        HashSet<Character> hstr = new HashSet<>();
        hstr.addAll(Arrays.asList(vowels));
        int patlen = pat.length();
        
        //generate all possible substrings from string s
        ArrayList<String> arl = new ArrayList<>();

        int i = 0;
        int end = i+patlen;

        while(end <= s.length()){
            arl.add(s.substring(i,end));
            i++;
            end++;
        }

        int numatch = 0;
        for(String sub: arl){
            boolean flag = true;
            for(int j = 0; j < patlen;j++){
                if(pat.charAt(j) == '0'){//if it's a vowel
                    if(!hstr.contains(sub.charAt(j))){
                        //means the current character at substring is consonant
                        flag = false;
                        break;
                    }
                }

                else{//if current pat char is 1
                    //but current char at sub is vowel
                    if(hstr.contains(sub.charAt(j))){
                        flag = false;
                        break;
                    }
                }
            }

            if(flag){
                numatch++;
            }
            else{
                flag = true;
            }
        }

        System.out.print(numatch);
    }
}
