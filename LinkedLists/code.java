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
}