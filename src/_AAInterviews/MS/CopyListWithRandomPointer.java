package _AAInterviews.MS;
import java.util.*;

public class CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private Map<Node, Node> map = new HashMap<>(); // original node -> copied node
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        if(map.containsKey(head)) return map.get(head);
        Node copyHead = new Node(head.val);
        map.put(head, copyHead);
        copyHead.next = copyRandomList(head.next);
        copyHead.random = copyRandomList(head.random);

        return copyHead;


    }
}
