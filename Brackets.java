//https://leetcode.com/problems/valid-parentheses

import java.util.Stack;

//Solve it using stack by placing the appropriate bracket into the stack and popping it when the closing bracket is encountered
public class Brackets {
    public static boolean isValid(String s) {
        if(s.length() == 0)
            return true;

        if(s.length()%2 != 0){
            return false;
        }

        Stack<Character> sc = new Stack<>();
        //boolean open = false;        
        //char rec = '\0';

        for(int i = 0; i < s.length(); i++){
            char curr = s.charAt(i);
            if(curr == '('){
                sc.push(')');
            }

            else if(curr == '{'){
                sc.push('}');
            }

            else if(curr == '['){
                sc.push(']');
            }

            else{

                if(!sc.empty()){
                    char c = sc.pop();
                    if(curr != c){
                        return false;
                    }
                }

                else{
                    return false;
                }
                
            }
        }

        if(sc.empty()){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        boolean valid = isValid("{){}");
        System.out.println(valid);
    }
}