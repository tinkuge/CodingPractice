import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        altSub(new int[]{1,2,3});
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        
        for(int n: nums){
            int s = res.size();
            //This loop to cycle through res
            //to add the current number
            for(int i = 0; i < s; i++){
                var curlist = new ArrayList<Integer>(res.get(i));
                curlist.add(n);
                res.add(curlist);
            }
        }
        return res;
    }

    static List<List<Integer>> altSub(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        //Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);

        return list;
    }

    static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        

        for(int i = start; i < nums.length; i++){
            var curlist = new ArrayList<>(tempList);
            curlist.add(nums[i]);
            tempList.add(nums[i]);
            list.add(curlist);
            //adds elements from i+1 until the end of nums array
            //go down the chain until end of array
            backtrack(list, tempList, nums, i+1);
            //effectively go back up the chain
            tempList.remove(tempList.size() - 1);
        }

    }
    
}
