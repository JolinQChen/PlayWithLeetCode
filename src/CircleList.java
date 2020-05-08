public class CircleList {

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public class Node {
         private int val;
         private Node next;
         private Node prev;
         private Node(int n){
             val = n;
             next = null;
             prev=null;
         }
    }

    private int size;
    private int capacity;
    private Node beginer;
    public CircleList(int k) {
        capacity = k;
        size = 0;
        beginer = new Node(-1);

    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(size == capacity) return false;
        Node node = new Node(value);
        if(size == 0) {
            beginer.next = node;
            beginer.prev = node;
            node.prev = beginer;
            node.next = beginer;
            size++;
            return true;
        }
        node.prev = beginer;
        node.next = beginer.next;
        node.next.prev = node;
        beginer.next = node;
        size++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(size == 0) return false;
        beginer.prev.prev.next = beginer;
        beginer.prev = beginer.prev.prev.next;
        size--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(size == 0) return -1;
        return beginer.next.val;
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(size == 0) return -1;
        return beginer.prev.val;
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if(size==0) return true;
        return false;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if(size==capacity) return true;
        return false;
    }

    public static void main(String[] args) {
        CircleList list = new CircleList(3);
        list.enQueue(1);
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
