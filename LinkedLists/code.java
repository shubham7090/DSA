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
}