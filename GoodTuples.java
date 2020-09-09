import java.util.HashSet;

public class GoodTuples {
    public static void main(String[] args) {
        int []tups = {4,4,6,1,2,2,2,3};
        //numTup(tups);
        //altNum(tups);
        test(tups);
    }

    static void numTup(int[] tups){
        int tlen = tups.length;

        int goodcount = 0;
        for(int i = 0; i+2 < tlen; i++){
            int simcount = 0;
            if(tups[i] == tups[i+1])
                simcount++;
            
            if(tups[i] == tups[i+2])
                simcount++;
            
            if(tups[i+1] == tups[i+2])
                simcount++;

            if(simcount == 1)
                goodcount++;
        }

        System.out.println(goodcount);
    }

    static void altNum(int[] tups){
        HashSet<Integer> hs;
        int count = 0;
        for(int i = 0; i+2 < tups.length; i++){
            hs = new HashSet<>();
            hs.add(tups[i]);
            hs.add(tups[i+1]);
            hs.add(tups[i+2]);
            if(hs.size() == 2)
                count++;
        }

        System.out.println(count);
    }

    static void test(int[] tups){
        System.out.println(tups[0]^tups[1]);
        System.out.println(tups[0]^tups[3]);
    }
}

/*
Given a list of integers, count the number of 'good tuples' that can be created. A 'good tuple' is defined as consecutive triplets having exactly 2 duplicate elements.
For eg.
nums = [4,4,6,1,2,2,2,3]
Here good tuples are: [4,4,6], [1,2,2], [2,2,3] becaue here in nums[i-1], nums[i], nums[i+1] eaxactly 2 nubers are equal, however [2,2,2] isn't a good tuple because 
nums[i-1]==num[i]==nums[i+1].
Count of good tuples is 3.

Another example:
nums = {4,6,4,1,3,4}
Here there is only one good tuple: [4,6,4]. Count of good tuples is 1.

Note: I could come up with two pointers with sliding window solution for this problem, however codesignal reported it as a poor solution. 
I am wondering if there is any better approach with fewer comparisons, may be using XOR or any other data structure.


*/ 