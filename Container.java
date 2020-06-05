//https://www.geeksforgeeks.org/container-with-most-water/
public class Container {

    //brute force solution
    public static int maxArea(int[] height) {
        
        int maxw = 0;

        for(int i  = 0; i < height.length; i++){
            for(int j = height.length - 1; j >= 0; j--){
                if(i != j){
                    int minel = Math.min(height[i], height[j]);
                    int area = (Math.abs(i-j))*minel;
                    if(area > maxw)
                        maxw = area;
                }
            }
        }
        System.out.println(maxw);

        return maxw;
    }

    //Start from both ends of the array.
    //Since the area at the both ends of the array has the most possibility of being the max area, calculate it and store it
    //The next possibility could either be at (i+1, j) or (i, j-1)
    //if i < j, that means i limits the possibility of holding more water. So, we increment i and find the area at (i+1, j)
    //Optimization: Store the i,j index where the last maxarea was discovered. Only calculate area for other i's and j's of their corresponding array values are
    //greater than the ones for the previous max area.
    //if j < i, that means j is limiting the possibility of holding more water. So you decrement it in search of a j
    //whose corresponding array value is greater than current j's corresponding value
    //Rinse and repeat until i == j where you can stop.

    public static int altMax(int[] height){
        int maxw = 0;
        
        int i = 0;
        int j = height.length - 1;

        while(i!=j){
            int curea = (Math.min(height[i], height[j]))*(j-i);
            if(curea > maxw)
                    maxw = curea;
            if(height[i] < height[j]){
                
                

                i++;
            }
            else{
                //int curea = (Math.min(height[i], height[j]))*(j-i);

                j--;
            }
        }

        System.out.print(maxw);

        return maxw;
    }

    public static void main(String args[]){
        int[] height = {1,8,6,2,5,4,8,3,7};
        //maxArea(height);
        altMax(height);

    }
}