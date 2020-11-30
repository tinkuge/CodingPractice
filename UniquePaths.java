//https://leetcode.com/problems/unique-paths

public class UniquePaths {

    public static void main(String[] args) {
        uniquePaths(7, 3);
    }

    public static int uniquePaths(int m, int n) {
        if(m == 1 && n == 1){
            return 1;
        }

        //if it's a simply row matrix or a column matrix
        if(m == 1 || n == 1){
            return 1;
        }
        
        int[][] arr= new int[m][n];
        //set the positions to the left
        //and the position above the last corner
        //index to 1
        arr[m-1][n-2] = 1;
        arr[m-2][n-1] = 1;

        //start from last row and last column
        for(int i = m-1; i > -1; i--){
            for(int j = n-1; j > -1; j--){
                //if we're at 0,0 add down and
                //right to get the answer
                if(i == 0 && j == 0){

                    int down = 0;
                    //Edge case for when there is
                    //only one row

                    //if there is more than one row
                    //in the given matrix
                    //get the down value
                    if(i+1 != m){
                        down = arr[i+1][j];
                    }
                    //if there is more than one column
                    //get right value
                    int right = 0;
                    if(j+1 != m){
                        right = arr[i][j+1];
                    }
                    //return the sum
                    return down+right;
                }
                //if it is not last row, last column
                if(!(i == m-1 && j == n-1)){
                    //if either next row or next column is out of bounds
                    //simply set current value to 1
                    if(i+1 == m || j+1 == n){
                        arr[i][j] = 1;
                    }
                    //Otherwise, get the down and right values
                    else{
                        int down = arr[i+1][j];
                        int right = arr[i][j+1];
                        //last corner index case
                        //set to 1
                        if(!(down == 0 || right  == 0)){
                            arr[i][j] = down+right;
                        }
                        //otherwise, add down and right
                        else{
                            arr[i][j] = 1;
                        }

                    }
                }
            }
        }
        return -1;
    }
}
