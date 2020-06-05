import java.util.*;
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {

        if(nums.length == 0){

            return new ArrayList<List<Integer>>();
        }

        if(nums.length == 1){
            ArrayList<Integer> ar = new ArrayList<>();
            ar.add(nums[0]);
            ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
            res.add(ar);
            return res;
        }
        
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i = 0; i < nums.length; i++){
            HashSet<Integer> hs = new HashSet<>();
            hs.add(nums[i]);
            ArrayList<Integer> ar = new ArrayList<>();
            ar.add(nums[i]);
            Helper(nums, hs, ar, res);
        }

        return res;
    }

    void Helper(int[] nums, HashSet<Integer> hs, ArrayList<Integer> perm, ArrayList<List<Integer>> res){
        if(hs.size() == nums.length){
            res.add(perm);
            return;
        }        

        for(int i = 0; i < nums.length; i++){
            HashSet<Integer> nset = new HashSet<>(hs);
            if(nset.add(nums[i])){
                ArrayList<Integer> nar = new ArrayList<>(perm);
                nar.add(nums[i]);
                Helper(nums, nset, nar, res);
            }
        }
    }

    public List<List<Integer>> altpermute(int[] nums){
        if(nums.length == 0){

            return new ArrayList<List<Integer>>();
        }

        if(nums.length == 1){
            ArrayList<Integer> ar = new ArrayList<>();
            ar.add(nums[0]);
            ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
            res.add(ar);
            return res;
        }

        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();

        for(int i = 0; i < nums.length; i++){
            LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
            lhs.add(nums[i]);
            altHelper(nums, lhs, res);
        }
        return res;
    }

    void altHelper(int[] nums, LinkedHashSet<Integer> lhs, ArrayList<List<Integer>> res){
        if(lhs.size() == nums.length){
            ArrayList<Integer> ar = new ArrayList<>(lhs);
            res.add(ar);
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(!lhs.contains(nums[i])){
                LinkedHashSet<Integer> nhs = new LinkedHashSet<>(lhs);
                nhs.add(nums[i]);
                altHelper(nums, nhs, res);
            }
        }
    }
}