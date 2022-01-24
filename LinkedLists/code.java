import java.util.*;
public class code{
    public class Node{
        int val;
        Node next;
        Node(int val,Node next){
            this.val=val;
            this.next=next;
        }
        Node(int val){
            this.val=val;
            this.next=null;
        }
    }
    public static void main(String[] args){

    }
    //leetcode 206
    public static Node reverse(Node head){
        if(head==null||head.next==null)return head;
        Node prev=null,curr=head;
        while(curr!=null){
            Node next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
    
    //leetcode 876
    public static Node mid(Node head){
        if(head==null||head.next==null)return head;
        Node slow=head,fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }       
        return slow;
    }
    
    //leetcode 234 
    public static boolean isPallindrome(Node head){
        Node mid=mid(head);
        Node revHead=reverse(mid.next);
        mid.next=null;
       
        Node ptr1=head,ptr2=revHead;
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
}