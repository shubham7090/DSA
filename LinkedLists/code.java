import java.util.*;
public class code{
    public class ListNode{
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
    public ListNode mergeSortedLists(ListNode head1,ListNode head2){
        ListNode dummy=new ListNode(-1),c=dummy;
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
}