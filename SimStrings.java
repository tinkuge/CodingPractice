public class SimStrings {
    public static void main(String[] args) {
        boolean res = checkSim("stingd", "sringe");
        System.out.println(res);
    }

    static boolean checkSim(String s, String t){
        if(s.length() != t.length()){
            return false;
        }

        if(s.equals(t)){
            return true;
        }

        s = s.toLowerCase();
        t = t.toLowerCase();
        int difcount = 0;
        int[] idx = new int[2];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            char d = t.charAt(i);

            if(c != d){
                difcount++;
                if(difcount <= 2){
                    idx[difcount - 1] = i;
                }

                else
                    return false;
            }
        }

        //when only one character differs, we can't swap anything
        if(difcount == 1)
            return false;

        //when difcount is exactly 2
        char[] carr = s.toCharArray();
        char temp = carr[idx[0]];
        carr[idx[0]] = carr[idx[1]];
        carr[idx[1]] = temp;
        
        String res = String.valueOf(carr);

        if(res.equals(t))
            return true;

        return false;
    }
}
//https://www.careercup.com/question?id=6247626241474560
//https://www.geeksforgeeks.org/meta-strings-check-two-strings-can-become-swap-one-string/
//https://leetcode.com/discuss/interview-question/439106/Postmates-or-OA-2019-or-Good-Tuples/395477
//https://leetcode.com/discuss/interview-experience/691128/postmates-interview-process-and-questions
//https://leetcode.com/discuss/interview-question/542850/postmates-senior-software-engg-sf-hq-mar-2020-reject