/*

You have an array of integers nums and an array queries, where queries[i] is a pair of indices (0-based). Find the sum of the elements in nums from the indices at queries[i][0] to queries[i][1] (inclusive) for each query, then add all of the sums for all the queries together. Return that number modulo 109 + 7.

Example

For nums = [3, 0, -2, 6, -3, 2] and queries = [[0, 2], [2, 5], [0, 5]], the output should be
sumInRange(nums, queries) = 10.

The array of results for queries is [1, 3, 6], so the answer is 1 + 3 + 6 = 10.

Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer nums

An array of integers.

Guaranteed constraints:
1 ≤ nums.length ≤ 105,
-1000 ≤ nums[i] ≤ 1000.

[input] array.array.integer queries

An array containing sets of integers that represent the indices to query in the nums array.

Guaranteed constraints:
1 ≤ queries.length ≤ 3 · 105,
queries[i].length = 2,
0 ≤ queries[i][j] ≤ nums.length - 1,
queries[i][0] ≤ queries[i][1].

[output] integer

An integer that is the sum of all of the sums gotten from querying nums, taken modulo 109 + 7.


Test Cases: 
Input:
nums: [3, 0, -2, 6, -3, 2]
queries:
[[0,2], 
 [2,5], 
 [0,5]]
Expected Output:
10

Input:
nums: [-1000]
queries: [[0,0]]
Expected Output:
999999007

Input:
nums: [34, 19, 21, 5, 1, 10, 26, 46, 33, 10]
queries:
[[3,7], 
 [3,4], 
 [3,7], 
 [4,5], 
 [0,5]]
Expected Output:
283

Input:
nums: [-4, -18, -22, -14, -33, -47, -29, -35, -50, -19]
queries:
[[2,9], 
 [5,6], 
 [1,2], 
 [2,2], 
 [4,5]]
Expected Output:
999999540

Input:
nums: [-23, -8, -52, -58, 93, -16, -26, 75, -77, 25, 90, -50, -31, 70, 53, -68, 96, 100, 69, 13]
queries:
[[0,4], 
 [0,8], 
 [7,7], 
 [3,4], 
 [2,3], 
 [0,3], 
 [8,8], 
 [2,2], 
 [5,7], 
 [2,2]]
Expected Output:
999999578

Input:
nums: [-77, 54, -59, -94, -13, -78, -81, -38, -26, 17, -73, -88, 90, -42, -63, -36, 37, 25, -22, 4, 25, -86, -44, 88, 2, -47, -29, 71, 54, -42]
queries:
[[2,2], 
 [4,7], 
 [2,4], 
 [0,2], 
 [3,6], 
 [6,6], 
 [3,3], 
 [2,7], 
 [3,4], 
 [3,3], 
 [2,9], 
 [0,1], 
 [4,4], 
 [2,3], 
 [0,6], 
 [4,4], 
 [2,3], 
 [0,5], 
 [2,5], 
 [4,5]]
Expected Output:
999996808

Input:
nums: [1000]
queries: [[0,0]]
Expected Output:
1000
*/

//https://www.geeksforgeeks.org/modulo-1097-1000000007/
//

public class SumRange {
    public static void main(String[] args) {
        int[] nums = {3, 0, -2, 6, -3, 2};
        int[][] queries = {{0,2}, {2,5}, {0,5}};
        sumInRange(nums, queries);
    }

    //singMod takes a single number and applies appropriate
    //modulo operations i.e., for negative  numbers, mod first and then
    //add the mod to the result, then apply mod to the sum of result and mod
    static int singMod(int num, int mod){
        if(num < 0){
            num = ((num%mod)+mod)%mod;
            return num;
        }

        else{
            //if positive, simple mod
            return num%mod;
        }
    }


    //when an addition operation is to be applied
    //between two numbers, take their individual mods
    //and apply mod to the results. Then sum the mods
    //and apply mod to the sum again
    static int addMod(int a, int b, int mod){
        int res = (singMod(a, mod)%mod)+(singMod(b, mod)%mod);
        res = singMod(res, mod);
        return res;
    }

    static int sumInRange(int[] nums, int[][] queries) {
        //hardcoded mod value
        int harcod = ((int) Math.pow(10,9))+7;
        //maintain a separate array for cumulative sum
        //at each index, it holds the modulated sum of current
        //element and the previous sum
        int[] cumsum = new int[nums.length];
        
        //initiate the zeroth index
        cumsum[0] = singMod(nums[0], harcod);
        
        //Populate the cumsum array
        for(int i = 1; i < nums.length; i++){
            //cumsum[i] = cumsum[i-1]+nums[i]
            cumsum[i] = addMod(nums[i], cumsum[i-1], mod);
        }
        
        //maintains overall sum from all the queries
        int ovsum = 0;
        //Iterate through each query 
        for(int[] x: queries){
            //get start and end indices of the present query
            int s = x[0];
            int e = x[1];
            //if the start index is 0, just get the cumsum at end index
            //since cumsum at any index is sum from 0 until that index
            if(s == 0){
                //Add the cumsum at end index to overallSum
                ovsum = addMod(ovsum, cumsum[e], harcod);
                //ovsum = ((ovsum%harcod)+(cumsum[e]%harcod))%harcod;
            }
            //else, cumsum between two indices can be obtained by
            //subtracting the cumsum at index preceding the start index
            //from the cumsum at end index
            else{
                int diff = cumsum[e] - cumsum[s-1];
                //modulate the diff
                diff = singMod(diff, harcod);
                //add the diff to overall sum
                ovsum = addMod(ovsum, diff, harcod);
            }
        }
               
        //modulate ovsum just in case
        return ovsum%harcod;
    }
    
}
