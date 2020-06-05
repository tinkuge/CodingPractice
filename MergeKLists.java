
public class MergeKLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Node{
        Node parent;
        Node lchild;
        Node rchild;
        int nodenum;
        ListNode value;
        boolean base = false;
        boolean barren = false;

        Node(){
            parent = null;
            lchild = null;
            rchild = null;
            nodenum = 0;
            value = null;
        }
        

    }
    class LoserTree{
        int total_nodes;
        int numlists;
        //int levels;
        Node root;
        ListNode head;
        ListNode res;
        LoserTree(int n){
            numlists = n;
            if(n%2 == 0){
                total_nodes = 2*(n)-1;
                //numlists = n;
            }
            else{
                total_nodes = 2*(n) + 1;
                //numlists = n+1;
            }
        }
        
        //initialize external nodes
        void initializeBaseTree(ListNode[] lists){
            
            root = new Node();
            root.nodenum = 1;
            int nodenum = root.nodenum;
            root.parent = null;
            root.value = null;
            root.lchild = createTreeNodes(root, lists, 2*nodenum);
            root.rchild = createTreeNodes(root, lists, 2*nodenum+1);

            
        }

        Node createTreeNodes(Node parent,ListNode[] lists, int nodenum){
            if(nodenum > lists.length && nodenum <= total_nodes){
                //as long as there are ListNodes in lists
                
                int listIndex = total_nodes - nodenum - 1;
                Node n = new Node();
                n.base = true;
                n.nodenum = nodenum;
                n.lchild = null;
                n.rchild = null;
                n.parent = parent;
                if(listIndex >= 0 && listIndex < lists.length){
                    //Node n = new Node();
                    n.value = lists[listIndex];
                    System.out.println("Current node value: "+n.value.val);
                    return n;
                }
                else{
                    //Node n
                    n.value = null;
                    return n;
                }

            }

            else{
                Node n = new Node();
                n.nodenum = nodenum;
                n.parent = parent;
                n.lchild = createTreeNodes(n, lists, 2*nodenum);
                n.rchild = createTreeNodes(n, lists, 2*nodenum+1);
                n.value = null;
                return n;
            }
        }

        ListNode play(){
            //Node curr = root;
            //ListNode res = null;
            traverseNodes(root);
            return res;
        }

        void traverseNodes(Node curr){
            if(numlists == 0){
                return;
            }
            if(curr.value == null){
                if(curr.lchild.base){
                    getMin(curr);
                    //go up one level to its parent and try to evaluate from there
                    if(curr.parent == null){
                        if(curr.value.next == null){
                            numlists--;
                        }
                        if(res == null){
                            res = curr.value;
                            head = res;
                            System.out.println("Added to res: "+res.val);
                        }

                        else{
                            res.next = curr.value;
                            res = res.next;
                            System.out.println("Added to res: "+res.val);
                        }

                        curr.value = null;
                        traverseNodes(curr);
                    }

                    else{
                        traverseNodes(curr.parent);
                    }
                    
                }

                //if the nodes aren't base nodes,
                else{
                    if(curr.lchild.value == null){
                        traverseNodes(curr.lchild);
                    }

                    if(curr.rchild.value == null){
                        traverseNodes(curr.rchild);
                    }

                    else if(curr.lchild.value != null || curr.rchild.value != null){
                        getMin(curr);

                        if(curr.parent == null){
                            if(curr.value.next == null){
                                numlists--;
                            }
                            if(res == null){
                                res = curr.value;
                                res.next = null;
                                head = res;
                                System.out.println("Added to res: "+res.val);
                            }
    
                            else{
                                
                                res.next = curr.value;
                                res = res.next;
                                res.next = null;
                                System.out.println("Added to res: "+res.val);
                            }
    
                            curr.value = null;
                            traverseNodes(curr);
                        }

                        else{
                            traverseNodes(curr.parent);
                        }
                    }

                }
                //
                
                //curr.value = min;
            }

            //if curr value not null
            else{
                if(curr.parent == null){
                    if(curr.value.next == null){
                        numlists--;
                    }
                    if(res == null){
                        res = curr.value;
                        head = res;
                        res.next = null;
                        System.out.println("Added to res: "+res.val);
                    }

                    else{
                        
                        res.next = curr.value;
                        res = res.next;
                        res.next = null;
                        System.out.println("Added to res: "+res.val);
                    }

                    curr.value = null;
                    traverseNodes(curr);
                }

                else{
                    traverseNodes(curr.parent);
                }
            }

            //
       }

        void getMin(Node curr){
            Node lchNode = curr.lchild;
            Node rcNode = curr.rchild;
            ListNode lcval = lchNode.value;
            ListNode rcval = rcNode.value;

            if(!lchNode.barren){
                if(!rcNode.barren){
                    if(lcval != null){
                        if(rcval != null){
                            if(lcval.val < rcval.val){
                                curr.value = lcval;
                                if(lchNode.base){
                                    if(lchNode.value.next == null){
                                        lchNode.barren = true;
                                        lchNode.value = null;
                                    }
                                    else{
                                        lchNode.value = lchNode.value.next;
                                    }                            
                                }
        
                                else{
                                    lchNode.value = null;
                                    getMin(lchNode);
                                    //lchNode.value = lchNode.value.next;
        
                                }
                                
                            }
        
                            else{
                                curr.value = rcval;
                                if(rcNode.base){
                                    if(rcNode.value.next == null){
                                        //numlists--;
                                        rcNode.barren = true;
                                        rcNode.value = null;
                                    }
                                    else{
                                        rcNode.value = rcNode.value.next;
                                    }
                                }
        
                                else{
                                    rcNode.value = null;
                                    getMin(rcNode);
                                    //rcNode.value = rcNode.value.next;
                                }
                            }
                        }
        
                        else{
                            curr.value = lcval;
                            if(lchNode.base){
                                if(lchNode.value.next == null){
                                    //numlists--;
                                    lchNode.barren = true;
                                    lchNode.value = null;
                                }
                                else{
                                    lchNode.value = lchNode.value.next;
                                } 
                            }
        
                            else{
                                lchNode.value = null;
                                getMin(lchNode);
        
                            }
                        }
                    }
                }
            }

            //if left node's value is not null
            if(lcval != null){
                if(rcval != null){
                    if(lcval.val < rcval.val){
                        curr.value = lcval;
                        if(lchNode.base){
                            if(lchNode.value.next == null){
                                lchNode.barren = true;
                                lchNode.value = null;
                            }
                            else{
                                lchNode.value = lchNode.value.next;
                            }                            
                        }

                        else{
                            lchNode.value = null;
                            getMin(lchNode);
                            //lchNode.value = lchNode.value.next;

                        }
                        
                    }

                    else{
                        curr.value = rcval;
                        if(rcNode.base){
                            if(rcNode.value.next == null){
                                //numlists--;
                                rcNode.barren = true;
                                rcNode.value = null;
                            }
                            else{
                                rcNode.value = rcNode.value.next;
                            }
                        }

                        else{
                            rcNode.value = null;
                            getMin(rcNode);
                            //rcNode.value = rcNode.value.next;
                        }
                    }
                }

                else{
                    curr.value = lcval;
                    if(lchNode.base){
                        if(lchNode.value.next == null){
                            //numlists--;
                            lchNode.barren = true;
                            lchNode.value = null;
                        }
                        else{
                            lchNode.value = lchNode.value.next;
                        } 
                    }

                    else{
                        lchNode.value = null;
                        getMin(lchNode);

                    }
                }
            }

            //implies lcval is null
            else if(rcval != null){
                curr.value = rcval;
                if(rcNode.base){
                    if(rcNode.value.next == null){
                        rcNode.barren = true;
                        //numlists--;
                        rcNode.value = null;
                    }
                    else{
                        rcNode.value = rcNode.value.next;
                    }
                }

                else{
                    rcNode.value = null;
                    getMin(rcNode);
                }
            }
            //both null
            else{
                
                curr.value = null;
            }
        }
    }

    public static void main(String args[]){
        MergeKLists mk = new MergeKLists();
        ListNode[] lists = new ListNode[3];
        lists[0] = mk.generateListNode(new int[]{1,4,5});
        lists[1] = mk.generateListNode(new int[]{1,3,4});
        lists[2] = mk.generateListNode(new int[]{2,6});

        LoserTree ltree = mk.new LoserTree(lists.length);
        ltree.initializeBaseTree(lists);
        ltree.play();
    }

    ListNode generateListNode(int[] nums){
        ListNode head = null;
        ListNode prev = null;

        for(int i = 0; i < nums.length; i++){
            if(head == null){
                head = new ListNode(nums[i]);
                prev = head;
            }

            else{
                ListNode curr = new ListNode(nums[i]);
                prev.next = curr;
                prev = curr;
            }
        }

        return head;
    }

    
}