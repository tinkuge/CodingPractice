//https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeLists {
    public class ListNode {
        int val;
            ListNode next;
            ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null)
            return null;

        if(l1 == null)
            return l2;

        if(l2 == null)
            return l1;

        ListNode a = l1;
        ListNode b = l2;

        ListNode res = null;
        ListNode prev = null;
        
        //key idea is to keep track of the head, prev node and be aware of the cases where ListNode.next may be null
        //Therefore, always check if ListNode.next is null or not before proceeding to the next node.
        while(true){
            if(a.val <= b.val){
                if(res == null){
                    res = a;
                    prev = a;
                    if(a.next != null){
                        a = a.next;
                    }

                    else{
                        a.next = b;
                        return res;
                    }
                }

                else{
                    prev.next = a;
                    prev = a;
                    if(a.next != null)
                        a = a.next;
                    else{
                        a.next = b;
                        break;
                    }
                }
                
            }
    
            else {
                if(res == null){
                    res = b;
                    prev = b;
                    if(b.next != null){
                        b = b.next;
                    }
                    else{
                        b.next = a;
                        break;
                    }
                }
                else{
                    prev.next = b;
                    prev = b;
                    if(b.next != null)
                        b = b.next;
                    else{
                        b.next = a;
                        break;
                    }
                }
            }

        }

        return res;
    }
}