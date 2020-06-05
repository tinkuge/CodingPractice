//https://leetcode.com/problems/valid-sudoku/

import java.util.*;

public class validSudoku {

    //Main idea is to keep track of elements in a particular row/column/ square
    //through a hashmap
    public boolean altValidSudoku(char[][] board){
        //this hashmap tracks the rows and the elements contained within each row
        //Integer represents the row/column/square and hashset carries unique elements of the corresponding row/col/square
        HashMap<Integer, HashSet<Character>> rw = new HashMap<>();
        //hashmap for columns
        HashMap<Integer, HashSet<Character>> col = new HashMap<>();
        //hashmap for sub-square
        HashMap<Integer, HashSet<Character>> sqr = new HashMap<>();

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                //Assign the current character
                char ch = board[i][j];
                //characters that are '.' are not interest and can be skipped
                //when the character is not '.'
                if(ch != '.'){
                    //check row hashmap to see if the hashmap has a hashset entry for the corresponding row
                    if(!rw.containsKey(i)){
                        //if  it doesn't, create a hashset
                        HashSet<Character> hs = new HashSet<>();
                        //add the current character to the hashset
                        hs.add(ch);
                        //insert the newly created hashset into the hashmap
                        rw.put(i, hs);
                    }
                    
                    //if the hashmap already contains a hashset for the present row
                    else{
                        //check if the current character is already seen in the present row
                        if(rw.get(i).contains(ch)){
                            //if so, return false
                            return false;
                        }
                        //otherwise, add the newly seen character to the existing hashmap
                        rw.get(i).add(ch);
                    }
    
                    //check column hm
                    //repeat the above method for column hashmap
                    if(!col.containsKey(j)){
                        HashSet<Character> hs = new HashSet<>();
    
                        hs.add(ch);
    
                        col.put(j, hs);
                    }
    
                    else{
                        //HashSet<Character> hc = col.get(j);
                        if(col.get(j).contains(ch)){
                            return false;
                        }
    
                        col.get(j).add(ch);
                    }
                    
                    //For checking each subsquare, the strategy is to take the present row index i.e., i
                    //and try to obtain the "base" square number
                    //for example, rows 0,1,2 will have exactly those numbered squares

                    //0,0 to 2,2 belong to 0 subsquare, whereas 0,3 to 2,5 belong to second sub square and so on

                    //To infer which sub square a particular (i, j) element belongs to,
                    //We infer base sub square number from i by first dividing with 3 (3 since each subsquare is 3x3 and total subsquares are 9)
                    //and then multiply with 3. Since 0/3, 1/3, 2/3 yield 0 (which is the base square from which we can get the the subsquare numbers for the entire blocks)
                    //we then add (j/3) to the base to get the actual subsquare number
                    int hor = i/3;
                    int ver = j/3;
    
                    int base = hor*3;
                    int sqnum = base+ver;
    
                    //we maintain a hashset for each subsquare and then check if the number is already seen or not
                    if(!sqr.containsKey(sqnum)){
                        HashSet<Character> hs = new HashSet<>();
                        hs.add(ch);    
                        sqr.put(sqnum, hs);
                    }
    
                    else{
                        if(sqr.get(sqnum).contains(ch)){
                            return false;
                        }
    
                        sqr.get(sqnum).add(ch);
                    }

                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        validSudoku vs = new validSudoku();


    }

    
}