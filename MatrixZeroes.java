//https://leetcode.com/problems/set-matrix-zeroes/

public class MatrixZeroes {
    public static void main(String[] args) {
        
    }
    /**
     * To do this in constant space, first row and first column are
     * used to track which row and which column should be set to 0.
     * But this would mean info about whether first row and column
     * contains any 0 would be lost. To address this, we use two variables
     * that track whether any of the first row elements or the first column elements
     * are 0s. If they are, we set the first row or first column to 0 at the end.
     * 
     * Run through all the elements/indices/positions, if a 0 is encountered,
     * set the corresponding row's first element is 0. Similarly, set the corresponding
     * column's first element to 0. If it's in first row or fiirst column, set the corresponding
     * boolean variable to true.
     * 
     * Then we do a pass over the matrix, excluding the first row and first column.
     * Starting position would be [1,1]. Check if either the corresponding row/column's
     * first element is zero. If it is, set it to 0.
     * 
     * Finally, we check the two tracking variables for first row and first column. If
     * row is 0 (which would mean one of the first row elements is 0), set all elements of 
     * first row to 0. Similarly, set first column elements to 0 if the column tracking variable
     * is set to true.
     *
     */
    
    static void setZeroes(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        //tracking variable for first row
        boolean r = false;
        //tracking variable for second row
        boolean c = false;
        //run through matrix
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //if the current element is 0
                if(matrix[i][j] == 0){
                    //set 0th column of ith row to 0
                    matrix[i][0] = 0;
                    //set jth column of 0th row to 0
                    matrix[0][j] = 0;

                    //if the element is in first row, set true
                    if(i == 0){
                        r = true;
                    }
                    //if element is in first column, set true
                    if(j == 0){
                        c = true;
                    }
                }
            }
        }

        //from 1,1 check if corresponding first row or first column element
        //is set to 0
        for(int i = 1; i < m;i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        //if one of the row elements is 0,
        //set all the first row to 0
        if(r){
            for(int j = 0; j < n; j++){
                matrix[0][j] = 0;
            }
        }

        //if one of the column elements is 0,
        //set all the first column to 0
        if(c){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }

    }
}
