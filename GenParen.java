//https://medium.com/@NickMa38/combinations-and-permutations-with-an-intro-to-backtracking-d940683ea9de
//https://leetcode.com/problems/generate-parentheses/

import java.util.ArrayList;
import java.util.List;

public class GenParen {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        //Do it in a recursive way
        //Assign l and r to n since that's how many brackets we're working with
        //imagine a stack where n number of left brackets are stored
        //and whenever the conditions are right, a bracket is popped from stack and added to the string
        //Similarly for r
        recFun("",n,n,res);
        return res;
    }

    static void recFun(String s, int l, int r, List<String> res){
        //This is the base case when l and r become 0
        //At this point, the resulting string must've been formed and pushed into the list
        if(l == 0 && r == 0){
            System.out.println(s);
            res.add(s);
            return;
        }

        //this case applies where the number of left and right brackets that can be employed are equal (this includes the starting case)
        //and l is greater than zero
        if(l == r && l > 0){
            s = s+"(";
            l--;
            recFun(s, l, r, res);
        }

        //this is the core case where we will generate various permutations of valid brackets
        //when l is < r, it can go two ways: another ( bracket can be added, further increasing the deficit between l and r
        //or a ) bracket can be added to decrease the deficit between number of (s and )s.
        else if(l < r && l > 0){
            //Assigning the resultant string to a new variable before passing to the recursive method is for debugging purposes only
            //Otherwise, the debug might unnecessarily take us to the string building operations in the String Builder library
            String a = s+"(";
            recFun(a, l-1, r, res);
            String b = s+")";
            recFun(b, l, r-1, res);
        }

        //This is the case where no more ( brackets can be added
        //So we pretty much add right brackets until r becomes 0 too i.e., no brackets are added
        else if(l < r && l == 0){
            s = s+")";
            recFun(s, l, r-1, res);
        }
    }

    public static void main(String[] args) {
        generateParenthesis(2);
    }

}