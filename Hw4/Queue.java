package hw4;

public class Queue implements List{
    // Implement Queue using Linked List with tail
    Node head;
    Node tail;
    
    public void push(Node node){ //pushBack
        if (head == null){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            tail = node;
        }
    }
    
    public void pop(){ //popFront
        if (head != null){
            if (head != tail){
                head = head.next;
            }else{
                head = null;
                tail = null;
            }
        }
    }
    
    public Node top(){
        if(head == null) return null;
        else return new Node(head.shares,head.price);
    }
    
}
