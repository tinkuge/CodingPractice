import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {
    public static void main(String[] args) {
        subsetsWithDup(new int[]{1,2,2});
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        //sort if nt sorted
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums, 0);

        return res;
    }

    static void backtrack(List<List<Integer>> res, List<Integer> temp, int[] nums, int start){
        

        var curlist = new ArrayList<>(temp);
        res.add(curlist);

        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1])
                continue;
            //temp after add represents the node we're at
            //(thinking of a tree structure)
            temp.add(nums[i]);
            //go one level deeper
            backtrack(res, temp, nums, i+1);
            //remove the last element
            //effectively going back up the chain
            temp.remove(temp.size() - 1);

        }
    }
}
