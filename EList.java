import java.util.HashMap;

public class EList {
    public class ListNode {
        int val;
         ListNode next;
         ListNode(int x) { val = x; }
        
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null ){
            return null;
        }

        HashMap<Integer, ListNode> hm = new HashMap<>();

        ListNode curr = head;
        ListNode prev = null;

        int i = 0;
        while(curr != null){
            hm.put(i, curr);
            curr = curr.next;
            i++;
        }

        int tlen = i;

        int pos = tlen - n;

        if(pos > 0){
            curr = hm.get(pos);
            //prev may refer to head of the list if pos = 1
            prev = hm.get(pos - 1);
            prev.next = curr.next;
            return head;
        }

        else if(pos == 0){
            curr = hm.get(pos);
            if(curr.next != curr && curr.next != null){
                head = curr.next;
                return head;
            }
            else{
                return null;
            }
            
        }

        else{
            return null;
        }



        //return head;
    }

    public ListNode altremoveNthFromEnd(ListNode head, int n){
        if(head == null){
            return null;
        }

        ListNode nnode = new ListNode(0);
        nnode.next = head;
        ListNode f = nnode;
        ListNode s = nnode;

        //what if the length of list is less than position n
        //

        int i = 0;
        while(i < n && s.next != null){
            s = s.next;
            i++;
        }

        //ListNode prev = head;
        while(s.next != null){
            f = f.next;
            s = s.next;
        }

        //f and s could both point to end of the list
        if(f.next != null){
            f.next = f.next.next;
        }
        
        return nnode.next;

    }

    public static void main(String args[]){
        int[] nums = {1,2,3,4,5};
        EList e = new EList();
        ListNode head = e.new ListNode(nums[0]);
        ListNode ln;
        ListNode prev = head;
        for(int i = 1; i < nums.length; i++){
            ln = e.new ListNode(nums[i]);
            prev.next = ln;
            prev = ln;
        }

        e.altremoveNthFromEnd(head, 2);
    }
}