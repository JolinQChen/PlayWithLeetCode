import java.util.*;
/**
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 * These child lists may have one or more children of their own, and so on, to produce a multilevel
 * data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are
 * given the head of the first level of the list.
 *
 *
 * */

public class _430_Flatten_a_Multilevel_Doubly_Linked_List {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
    public Node flatten(Node head) {
        Node pointer = head;
        while(pointer!=null){
            if(pointer.child==null) pointer = pointer.next;
            else{
                Node last = findLast(pointer);
                Node storeNext = pointer.next;
                last.next = storeNext;
                if(storeNext!=null) storeNext.prev = last;

                pointer.next = pointer.child;
                pointer.child = null;
                pointer.next.prev = pointer;

                pointer = pointer.next;
            }
        }
        Node p = head;
        return head;
    }

    private Node findLast(Node node){
        Node res = node.child;
        while(res.next!=null) res = res.next;
        return res;
    }
}
