//https://leetcode.com/problems/non-overlapping-intervals/

/*
The key idea here is to first sort the intervals by their end times.
This doesn't obviously mean that the start times are ordered as well.
But what it does is, it keeps the intervals with durations to the extreme
right. Now go through each interval and if the start time of this interval
does not overlap with end time of previous interval, include it in the count.
Anything that overlaps gets excluded from the count.
*/


import java.util.Arrays;

public class NonOverlap {
    public int eraseOverlapIntervals(int[][] intervals) {
        //Sort the array by the end time of the interval
        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);
        //Automatically include the first sorted interval into the count
        int count = 1;
        //Get the previous interval's end time
        int pmax = intervals[0][1];
        //Start from second element in the interval
        for(int i = 1; i < intervals.length;i++){
            //get start and end times of current interval
            int curmin = intervals[i][0];
            int curmax = intervals[i][1];

            //if the start time of current interval is >= last interval end time
            if(curmin >= pmax){
                //That means it no overlap, in which case, increment the count
                count++;
                //make the current interval's end time as the pmax so that the next interval can be
                //compared against it
                pmax = curmax;
            }
        }
        //In this particular question, it's asking how many overlapping intervals there are
        //Simply subtract it from overall number of intervals
        return intervals.length - count;
    }
}