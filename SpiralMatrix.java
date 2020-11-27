import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/spiral-matrix
public class SpiralMatrix {
    public static void main(String[] args) {
        spiralClock(new int[][]{{1 ,2 ,3 ,4 },
                                {5 ,6 ,7 ,8 },
                                {9 ,10,11,12},
                                {13,14,15,16}});
    }

    static List<Integer> spiralClock(int[][] matrix){

        //Get rows and columns
        int rows = matrix.length;
        int cols = matrix[0].length;
        //Declare arraylist
        ArrayList<Integer> l = new ArrayList<>();
        if(rows == 1 && cols == 1){
            l.add(matrix[0][0]);
            return l;
        }
        //Maintain a 2D array to check if a particular
        //element has already been visited
        boolean[][] visited = new boolean[rows][cols];
        //total number of elements
        int totel = matrix.length*matrix[0].length;
        //Set the starting point to true
        visited[0][0] = true;
        //Set initial index as our starting point
        int j = 0;
        int i = 0;
        //add the element at initial index to arraylist
        l.add(matrix[i][j]);
        //n tracks the number of elements visited so far
        int n = 1;

        //as long as the number of elements visited
        //is less than total number, continue loop
        while(n < totel){
            /*
            We have to keep going to the right until we hit the bounds
            Then we keep going down until we hit the bounds
            then we keep going left
            then we keep going up

            Rinse and repeat: right -> down -> left -> up -> right
            */

            //keep checking elements to the right
            //as long as there is an element to the right
            //and it has not been visited
            //if it has been visited, we change direction
            //
            while(j+1 < cols && !visited[i][j+1]){
                //if j+1 element is unvisited
                //and within bounds, move there
                //by incrementing it
                j++;
                //set visited to true
                visited[i][j] = true;
                //add the element to result list
                l.add(matrix[i][j]);
                //increment the number of elements visited
                n++;
            }

            //check down
            while(i+1 < rows && !visited[i+1][j]){
                i++;
                visited[i][j] = true;
                l.add(matrix[i][j]);
                n++;
            }
            //check left
            while(j-1 > -1 && !visited[i][j-1]){
                j--;
                visited[i][j] = true;
                l.add(matrix[i][j]);
                n++;
            }
            //check up
            while(i-1 > -1 && !visited[i-1][j]){
                i--;
                visited[i][j] = true;
                l.add(matrix[i][j]);
                n++;
            }
        }

        return l;
    }
}