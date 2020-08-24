package _All;
import com.sun.javaws.IconUtil;

import java.util.*;
public class _428_Serialize_and_Deserialize_N_ary_Tree {
    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(Node root) {
        StringBuilder res = new StringBuilder();
        if(root==null) return "";
        res.append(root.val);
        res.append(";");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size>0) {
                size--;
                Node cur = queue.poll();
                if(cur.children==null || cur.children.size()==0) res.append("null,");
                else {
                    for(Node child:cur.children) {
                        queue.add(child);
                        res.append(child.val);
                        res.append(".");
                    }
                    res.append(",");
                }
            }
            res.append(";");
        }
        System.out.println(res.toString());
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public static Node deserialize(String data) {
        if(data=="") return null;
        String[] strs = data.split(";");
        Node root = new Node(Integer.parseInt(strs[0]), new ArrayList<>());
        Queue<Node> preList = new LinkedList<>();
        preList.add(root);
        for(int i=1; i<strs.length; i++){
//            System.out.println("i: "+i);
            String[] curLayer = strs[i].split(",");
            for(int j=0; j<curLayer.length; j++) {
//                System.out.println("j: "+j);
                Node parent = preList.poll();
                if(!curLayer[j].equals("null")) {
                    String[] curParents = curLayer[j].split("\\.");

//                System.out.println(parent.val);
//                System.out.println(curParents.length);
                    for(int k=0; k<curParents.length; k++) {
//                    System.out.println(curParents[k]);
                        Node tmp = new Node(Integer.parseInt(curParents[k]), new ArrayList<>());
                        parent.children.add(tmp);
                        preList.add(tmp);
//                        System.out.println("parent: "+parent.val + "child: "+tmp.val);
                    }
                }

//                System.out.println(curLayer[j]);

            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2));
        root.children.add(new Node(4,new ArrayList<>()));
        root.children.get(0).children.add(new Node(5));
        root.children.get(0).children.add(new Node(6));
        root.children.get(2).children.add(new Node(7));
        root.children.get(2).children.add(new Node(8));
        String res = serialize(root);
//        System.out.println(res);
        deserialize(res);
    }
}
