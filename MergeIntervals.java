//https://leetcode.com/problems/merge-intervals

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{8,10},{15,18},{2,6}};
        merge(intervals);
    }

    static void merge(int[][] intervals){

        //Sort the given array by start times
        Arrays.sort(intervals, (int[] x,int[] y) -> Integer.compare(x[0], y[0]));

        // Arrays.sort(intervals, new Comparator<int[]>(){
        //     @Override
        //     public int compare(int[] x, int[] y){
        //         return x[0] - y[0];
        //     }
        // });

        //Linkedlist tracks both start and end positions
        LinkedList<int[]> ll = new LinkedList<>();
        //add the first interval from sorted intervals
        ll.add(intervals[0]);
        /*
        * Starting from the next interval, check if the interval
        * start is overlapping with last interval added to linkedlist
        * If it is, then update the end of the interval that is already
        * in the linked list. The update depends on if the current interval's
        * end point is greater than the end point in the linked list. 
        * if there is no overlap, simply add the interval at hand to
        * the linked list. Linked list adds it to the end and updates its
        * last pointer.
        */
        for(int i = 1; i < intervals.length; i++){
            //get the last interval in the linkedlist
            int[] rcnt = ll.getLast();
            //get the current interval in intervals
            int[] cur = intervals[i];

            //if the current's starting point is <=
            //end point of rcnt, update rcnt's end with
            //max of cur and rcnt's end point
            if(cur[0] <= rcnt[1]){
                rcnt[1] = Math.max(rcnt[1], cur[1]);
            }
            else{
                //otherwise, simply add it to linked list
                ll.add(cur);
            }
        }

        //convert linked list to a 2d array
        ll.toArray(new int[ll.size()][2]);
        //System.out.println("Done");
    }
}
