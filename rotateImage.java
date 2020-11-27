public class RotateImage {
    public void rotate(int[][] matrix) {
        //   [ 5, 1, 9,11],
        //   [ 2, 4, 8,10],
        //   [13, 3, 6, 7],
        //   [15,14,12,16]

        if(matrix[0].length == 1){
            return;
        }

        //transpose the matrix
        for(int i = 0; i < matrix.length; i++){
            //only need to consider columns that are >= i as the indexes less than i are already set
            for(int j = i; j < matrix[0].length; j++){
                //when i == j, swapping is not neccessary as it refers to a single number.
                //saves comparisions at diagonal elements
                if(i != j){
                    //swap
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
                
            }
        }

        // [5,2,13,15],
        // [1,4,3,14],
        // [9,8,6,12],
        // [11,10,7,16]

        //swap/exchange each column with its corresponding counterpart on the other side
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0, k = matrix.length - 1; j < k;j++,k--){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
            }
        }

        // [15,13,2,5],
        // [14,3,4,1],
        // [12,6,8,9],
        // [16,7,10,11]

        //System.out.println("Done");

    }

    public static void main(String[] args) {
        RotateImage ri = new RotateImage();

        int[][] matrix = new int[][]{{ 5, 1, 9,11}, {2, 4, 8,10}, {13, 3, 6, 7}, {15,14,12,16}};

        ri.rotate(matrix);
        
    }
}