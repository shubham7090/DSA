import java.util.*;
public class code{
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
        ListNode(int val){
            this.val=val;
            this.next=null;
        }
    }
    public static void main(String[] args){
        reverse(null);
    }
    //leetcode 206
    public static ListNode reverse(ListNode head){
        if(head==null||head.next==null)return head;
        ListNode prev=null,curr=head;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
    
    //leetcode 876
    public static ListNode mid(ListNode head){
        if(head==null||head.next==null)return head;
        ListNode slow=head,fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }       
        return slow;
    }
    
    //leetcode 234 
    public static boolean isPallindrome(ListNode head){
        ListNode mid=mid(head);
        ListNode revHead=reverse(mid.next);
        mid.next=null;
       
        ListNode ptr1=head,ptr2=revHead;
        boolean ans=true;
        while(ptr1!=null&&ptr2!=null){
            if(ptr1.val!=ptr2.val){
                ans= false;
                break;
            }
            ptr1=ptr1.next;
            ptr2=ptr2.next;
        
        }
        mid.next=reverse(revHead);
        return ans;
    }
    //leetcode 143
    public static void fold(ListNode head){
        ListNode mid=mid(head);
        ListNode revHead=reverse(mid.next);
        mid.next=null;

        ListNode ptr1=head,ptr2=revHead;
        while(ptr1!=null||ptr2!=null){
            ListNode next1=ptr1.next;
            ListNode next2=ptr2.next;
            ptr1.next=ptr2;
            ptr2.next=next1;
            ptr1=next1;
            ptr2=next2;
        }
    }
    //https://www.geeksforgeeks.org/program-to-unfold-a-folded-linked-list/
    public static void unfold(ListNode head){
        if(head==null||head.next==null)return;
        ListNode h1=head,c1=head,h2=head.next,c2=head.next;
        while(c2!=null&&c2.next!=null){
            ListNode fwd=c2.next;
            c1.next=fwd;
            c2.next=fwd.next;
            c1=c1.next;
            c2=c2.next;
        }
        // c1.next=null;
        h2=reverse(h2);
        c1.next=h2;

    }
    //leetcode 21
    public static ListNode mergeSortedLists(ListNode head1,ListNode head2){
        ListNode dummy=new ListNode(-1,null),c=dummy;
        ListNode c1=head1,c2=head2;
        while(c1!=null&&c2!=null){
            if(c1.val<c2.val){
                c.next=c1;
                c1=c1.next;
                c=c.next;
            }else{
                c.next=c2;
                c2=c2.next;
                c=c.next;
            }
        }
        while(c1!=null){
            c.next=c1;
            c1=c1.next;
            c=c.next;
        }
        while(c2!=null){
            c.next=c2;
            c2=c2.next;
            c=c.next;
        }
        return dummy.next;
    }
    //leetcode 23
    public static class Pair implements Comparable<Pair>{
        ListNode n;
        Pair(ListNode n){
            this.n=n;
        }
        public int compareTo(Pair o){
            return this.n.val-o.n.val;
        }
    }
    public static ListNode mergeKSortedLists1(ListNode[] lists){
        //using priority queue
        if(lists.length==0)return null;
        ListNode head=new ListNode(0,null);
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>();
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null)
                pq.add(new Pair(lists[i]));
        }
        ListNode temp=head;
        while(pq.size()>0 ){
            ListNode ele=pq.remove().n;
            if(ele.next!=null)pq.add(new Pair(ele.next));
            ele.next=null;
            temp.next=ele;
            temp=temp.next;
        }
        
        return head.next;
    }
    //leetcode 23
    public static ListNode mergeKSortedLists2helper(ListNode[] lists,int si,int ei){
        if(si>ei)return null;
        if(si==ei)return lists[si];
        int mid=(si+ei)/2;
        ListNode l1=mergeKSortedLists2helper(lists,si,mid);
        ListNode l2=mergeKSortedLists2helper(lists,mid+1,ei);
        return mergeSortedLists(l1, l2);
    }
    public static ListNode mergeKSortedLists2(ListNode[] lists){
        if(lists.length==0)return null;
        return mergeKSortedLists2helper(lists,0,lists.length-1);
        //Time Compexity : nklogk
    }

    //https://www.geeksforgeeks.org/merge-sort-for-linked-list/
    public static void mergeSort(ListNode head){

    }
    //similar to leetcode 328
    public static ListNode oddEven(ListNode head){
        ListNode oddhead =new ListNode(-1);
        ListNode evenhead =new ListNode(-1);
        ListNode e=evenhead,o=oddhead,c=head;
        while(c!=null){
            if(c.val%2==0){
                e.next=c;
                e=e.next;
            }else{
                o.next=c;
                o=o.next;
            }
            c=c.next;
        }
        //first odd then even
        o.next=evenhead.next;
        e.next=null;
        return oddhead.next;
    } 
    //leetcode 445
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        l1=reverse(l1);
        l2=reverse(l2);
        ListNode c1=l1,c2=l2;
        int carry=0;
        ListNode dummy=new ListNode(-1);
        ListNode d=dummy;
        while(c1!=null&&c2!=null){
            int sum=c1.val+c2.val+carry;
            // System.out.println(sum);
            carry=sum/10;
            d.next= new ListNode(sum%10);
            d=d.next;
            c1=c1.next;
            c2=c2.next;
        }
        ListNode c=c1==null?c2:c1;
        while(c!=null){
            int sum=c.val+carry;
            carry=sum/10;
            d.next=new ListNode(sum%10);
            d=d.next;
            c=c.next;
        }
        if(carry!=0)d.next=new ListNode(carry);
        return reverse(dummy.next);
    }

    // leet code 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(-1);
        ListNode c1=l1,c2=l2,d=dummy;
        int carry=0;
        while(c1!=null&&c2!=null){
            int sum=c1.val+c2.val+carry;
            carry=sum/10;
            d.next=new ListNode(sum%10);
            d=d.next;
            c1=c1.next;
            c2=c2.next;
        }
        ListNode c=c1==null?c2:c1;
        while(c!=null){
            int sum=c.val+carry;
            carry=sum/10;
            d.next=new ListNode(sum%10);
            d=d.next;
            c=c.next;
        }
        if(carry!=0)d.next=new ListNode(carry);
        return dummy.next;
    }

    //leetcode 138
    static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

    } 
     //method1 using extra space
    public static Node copyRandomList(Node head) {
        
        HashMap<Node,Node> mp=new HashMap<>();
        Node newHead=new Node(-1);
        Node prev=newHead;
        Node curr=head;
        while(curr!=null){
            prev.next=new Node(curr.val);
            mp.put(curr,prev.next);
            
            prev=prev.next;
            curr=curr.next;
        }
        newHead=newHead.next;
        curr=head;
        prev=newHead;
        while(curr!=null){
            prev.random=mp.get(curr.random);
            prev=prev.next;
            curr=curr.next;
        }
        return newHead;
    }
    // method 2 witout extra space
    public Node copyRandomList2(Node head) {
        if(head==null)return null;
        Node curr=head;
        while(curr!=null){
            Node fwd=curr.next;
            Node naya=new Node(curr.val);
            curr.next=naya;
            naya.next=fwd;
            curr=fwd;   
        }
        curr = head;
        while(curr!=null){
            curr.next.random=curr.random!=null?curr.random.next:null;
            curr=curr.next.next;
            
        }
        curr=head;
        Node nHead=curr.next;
        Node ptr=curr.next;
        while(curr!=null){
            curr.next=curr.next.next;
            if(ptr.next!=null)ptr.next=ptr.next.next;
            curr=curr.next;
            ptr=ptr.next;
        }
        
        return nHead;
    }
    //leetcode 141
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)return false;
        ListNode slow=head,fast=head;
        while(fast!=null){
            slow=slow.next;
            if(fast.next==null)return false;
            fast=fast.next.next;
            if(slow==fast)return true;
        }
        return false;
    }
    //leetcode 142
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null)return null;
        ListNode slow=head,fast=head;
        boolean isCycle=false;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                isCycle=true;
                break;
            }
        }
        if(isCycle==false)return null;
        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
    //leetcode 160
    //there is also a simpler and shorter way of doing it 
    //https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/1741919/JAVA-SOLUTION-with-explanation-also-asked-in-interview
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tail=headA;
        while(tail.next!=null)tail=tail.next;
        tail.next=headB;
        ListNode ans=detectCycle(headA);//leetcode 142
        tail.next=null;
        return ans;
    }
    //https://www.geeksforgeeks.org/subtract-two-numbers-represented-as-linked-lists/
    // ans = https://practice.geeksforgeeks.org/problems/subtraction-in-linked-list/1/#

    //multiply 2 linked lists
    // ans= https://practice.geeksforgeeks.org/problems/multiply-two-linked-lists/1/#

    //leetcode 83
    public static ListNode deleteDuplicates2(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode curr=head;
        while(curr!=null&&curr.next!=null){
            if(curr.val==curr.next.val)curr.next=curr.next.next;
            else curr=curr.next;
        }
        return head;
    }
    //leetcode 82
     public static ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode dummy=new ListNode(-1,head);
        ListNode itr=dummy,curr=head.next;
        while(curr!=null){
            boolean flag=false;
            while(curr!=null&&itr.next.val==curr.val){
                curr=curr.next;
                flag=true;
            }
            if(flag==true)itr.next=curr;
            else {
                itr=itr.next;
            }
            if(curr!=null)curr=curr.next;
        }
        return dummy.next;
    }
    //segregate 0 1 in linked list
    public static ListNode segregate01(ListNode head){
        if(head==null||head.next==null)return head;
        ListNode dummy0=new ListNode(-1);
        ListNode dummy1=new ListNode(-1);
        ListNode prev0=dummy0,prev1=dummy1,curr=head;
        while(curr!=null){
            if(curr.val==0){
                prev0.next=curr;
                prev0=curr;
            }else{
                prev1.next=curr;
                prev1=curr;
            }
            curr=curr.next;
        }
        prev0.next=dummy1.next;
        prev1.next=null;
        return dummy0.next;
    }
    //segregate 0 1 2
    //https://www.geeksforgeeks.org/sort-a-linked-list-of-0s-1s-or-2s/
    public static ListNode segregate012(ListNode head){
        if(head==null||head.next==null)return head;
        ListNode dummy0=new ListNode(-1);
        ListNode dummy1=new ListNode(-1);
        ListNode dummy2=new ListNode(-1);
        ListNode prev0=dummy0,prev1=dummy1,prev2=dummy2,curr=head;
        while(curr!=null){
            if(curr.val==0){
                prev0.next=curr;
                prev0=curr;
            }else if(curr.val==1){
                prev1.next=curr;
                prev1=curr;
            }else{
                prev2.next=curr;
                prev2=curr;
            }
            curr=curr.next;
        }
        prev0.next=dummy1.next;
       if(dummy1.next==null){
            prev0.next=dummy2.next;
        }else prev1.next=dummy2.next;
        
        prev2.next=null;
        return dummy0.next;
    }
}