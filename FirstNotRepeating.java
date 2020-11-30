//Codesignal

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FirstNotRepeating {
    public static void main(String[] args) {
        firstNonRepeatingCharacter("abacabad");
        firstNotRepeatingCharacter("abacabad");
    }

    static int firstNonRepeatingCharacter(String s){
        int n = s.length();
        //array to track the frequency of characters
        //We could use a boolean array where we could track
        //if a character's frequency is 1 or more than 1
        int[] freq = new int[n];
        //Map each unique character to its idx in freq array
        HashMap<Character,Integer> ctof = new HashMap<>();
        //Map each freq array index to a character in original string
        HashMap<Integer,Character> fitoc = new HashMap<>();
        //map character to its index in the orignal string
        HashMap<Character, Integer> cpos = new HashMap<>();
        //the next available index in the freq array, where the new
        //character's frequency can be tracked
        int nexav = 0;
        for(int i = 0; i < n; i++){
            char cur = s.charAt(i);
            //If the map already contains the character
            //update its frequency in freq
            if(ctof.containsKey(cur)){
                //get the idx of the character in freq
                int idx = ctof.get(cur);
                //update frequency
                freq[idx] = freq[idx]+1;

            }

            //if this is the first time the character is being encountered
            else{
                //record the position of character in the string
                cpos.put(cur, i);
                //record where in the freq array, the current character's
                //frequency is tracked
                ctof.put(cur, nexav);
                //since it's the first time the character is encountered
                //set its frequency to 1
                freq[nexav] = 1;
                //record what index in freq represents which character
                fitoc.put(nexav, cur);
                //update nextavailable index
                nexav++;
            }
        }

        //iterate through freq until we find an entry with frequency of 1
        for (int i = 0; i < n; i++) {
            if(freq[i] == 1){
                //get the corresponding character
                char unq = fitoc.get(i);
                //get the character's index in the orignal string
                return cpos.get(unq);
            }
        }

        //if no character is unique, return -1;
        return -1;
    }

    static char firstNotRepeatingCharacter(String s) {
        //
        //Maintains a map of character to frequency array index
        HashMap<Character,Integer> hm = new HashMap<>();
        //Maintains a map of freq array index to character
        HashMap<Integer,Character> fhm = new HashMap<>();
        int[] freq = new int[s.length()];
        int avidx = 0;
        for(int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if(hm.containsKey(cur)){
                int idx = hm.get(cur);
                freq[idx] = freq[idx]+1;
            }

            else{
                hm.put(cur, avidx);
                fhm.put(avidx, cur);
                freq[avidx] = 1;
                avidx++;
            }
        }

        for (int i = 0; i < freq.length; i++) {
            if(freq[i] == 1){
                char res = fhm.get(i);
                return res;
            }
        }

        return '_';
    }

    
}
