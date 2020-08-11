package _AAInterviews.MS;
import java.util.*;
public class CloneGraph {
    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    Map<Node, Node> map = new HashMap<>();

    private Node cloneHelper(Node node) {
        if(node==null) return null;
        if(map.containsKey(node)) return map.get(node);
        Node newNode = new Node(node.val);
        map.put(node,newNode);
        List<Node> list = new ArrayList<>();
        for(Node next:node.neighbors) {
            list.add(cloneHelper(next));
        }
        newNode.neighbors = list;
        return newNode;
    }
    public Node cloneGraph(Node node) {
        return cloneHelper(node);
    }


}
