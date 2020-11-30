//https://leetcode.com/problems/min-cost-climbing-stairs/
public class MinCostStairs {
    public static void main(String[] args) {
        int[] cost = new int[]{10, 15, 20};
        minCostClimbingStairs(cost);
    }

    public static int minCostClimbingStairs(int[] cost) {
        if(cost.length == 1){
            return cost[0];
        }

        if(cost.length == 2){
            return Math.min(cost[0], cost[1]);
        }

        int n = cost.length;
        //Create a total cost array
        //Takes the minimum of previous two costs
        //add it to current element
        int tcost[] = new int[n];
        //initial costs would be same as first two elements
        //of cost
        tcost[0] = cost[0];
        tcost[1] = cost[1];
        //Starting from third element, decide its costs based on previous
        //two costs
        for(int i = 2; i < n; i++){
            tcost[i] = Math.min(tcost[i-1]+cost[i], tcost[i-2]+cost[i]);
        }

        //return the min of last two costs
        return Math.min(tcost[n-1], tcost[n-2]);
    }
}
