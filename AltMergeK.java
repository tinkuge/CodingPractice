//https://leetcode.com/problems/merge-k-sorted-lists/

import java.util.Comparator;
import java.util.PriorityQueue;

public class AltMergeK{
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class CompImp implements Comparator<ListNode>{
        @Override

        //returns -1 if a < b, 1 if a > b or 0 if a=b
        public int compare(ListNode a, ListNode b){
            return Integer.compare(a.val, b.val);
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }

        if(lists.length == 1){
            return lists[0];
        }
        
        //Create a new class that overrides compare function of Comparator
        CompImp cip = new CompImp();
        //Pass the comparator class to priority queue while instantiating
        PriorityQueue<ListNode> pq = new PriorityQueue<>(cip);
        
        //Add the head nodes of each listnode to the priority queue
        //The listnodes following the head nodes of merely exist as references that can be inserted 
        //down the line when its predecessor gets popped from the queue
        for(ListNode i: lists){
            //Just in case when the given listnode is empty
            if(i != null)
                pq.add(i);
        }

        //add a dummy node so as to keep track of the start position of the list
        //Irrespective of this node's value, it always precedes any listnode the priority queue may yield
        ListNode head = new ListNode(0);
        ListNode curr = head;
        //see if the priority queue contains an element or not
        //Another way to accomplish this is by using isEmpty() method
        while(pq.peek() != null){
            //remove the top node from the queue
            //and set it as the next element to the current listnode
            curr.next = pq.poll();
            //advance the pointer of the current listnode
            curr = curr.next;

            //the newly added listnode may have a succeding element to it
            //denoted in the ListNode.next field
            //if it's not null, add the listnode to the priority queue
            //and let the pq deal with its place in the tree
            if(curr.next != null)
                pq.add(curr.next);
        }


        return head.next;
    }
}