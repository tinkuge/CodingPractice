import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        //convert each string to char array
        //sort the char array
        //check if hashmap has the char array as key value
        //if it does, append to exisiting value
        //if not, insert new key value

        if(strs.length == 0){
            return new ArrayList<>();
        }

        HashMap<String, ArrayList<String>> hm = new HashMap<>();

        for(String s: strs){
            char[] carr = s.toCharArray();
            Arrays.sort(carr);
            String sorted = String.valueOf(carr);
            if(hm.containsKey(sorted)){
                hm.get(sorted).add(s);
            }

            else{
                ArrayList<String> ar = new ArrayList<>();
                ar.add(s);
                hm.put(sorted,ar);
            }
        }

        ArrayList<List<String>> res = new ArrayList<>();
        for(List<String> clist: hm.values()){
            res.add(clist);
        }


        return res;
        
        //maintain hashset and hashmap
        //hashset would have any new word that does not have a match for existing permutations
        //take a word -> see if its in any of hashmaps
        //if not, generate its permutations
        //store the permutations in hashmap, mapping to this word

        // List<List<Integer>> res = new ArrayList<>();

        // //store all permutations of a given word. permutation -> original word
        // HashMap<String, String> hm = new HashMap<>();
        // //Hashmap that contains the resultant list
        // HashMap<String, ArrayList<String>> groupedList = new HashMap<>();
        // //store words that do not match to any existing permutations
        // HashSet<String> hs = new HashSet<>();

        // for(String s: strs){

        //     if(!hm.containsKey(s)){
        //         char[] carr = s.toCharArray();
        //         hm.put(s, s);
        //         ArrayList<String> ar = new ArrayList<>();
        //         ar.add(s);
        //         groupedList.put(s, ar);
        //         getPermutations(carr, "", hm, groupedList);
        //     }

            
        // }

        // return null;
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        ga.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }

}