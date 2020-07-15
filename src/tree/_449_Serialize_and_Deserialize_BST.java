package tree;

import java.util.LinkedList;
import java.util.Queue;

public class _449_Serialize_and_Deserialize_BST {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     *
     * Serialization is the process of converting a data structure or object into a sequence of
     * bits so that it can be stored in a file or memory buffer, or transmitted across a network
     * connection link to be reconstructed later in the same or another computer environment.
     *
     * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction
     * on how your serialization/deserialization algorithm should work. You just need to ensure that
     * a binary search tree can be serialized to a string and this string can be deserialized to the
     * original tree structure.
     *
     * The encoded string should be as compact as possible.
     *
     * Note: Do not use class member/global/static variables to store states. Your serialize and
     * deserialize algorithms should be stateless.
     *
     *
     */
    // 以下为针对BT的方法
    // Encodes a tree to a single string.
    public String serializeBT(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        //return serialHelper(root, "");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        if(root==null) return "null,";
        int count = 1;
        while (!queue.isEmpty() && count>0){
            TreeNode cur = queue.poll();
            if(cur!=null){
                count--;
                sb.append(cur.val);
                sb.append(",");
                queue.add(cur.left);
                if(cur.left!=null) count++;
                queue.add(cur.right);
                if(cur.right!=null) count++;
            }
            else {
                sb.append("null,");
            }
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserializeBT(String data) {
        String[] strings = data.split(",");
        if(strings[0].equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int idx = 1;
        while (idx<strings.length){
            TreeNode cur = queue.poll();
            if(!strings[idx].equals("null")){
                cur.left = new TreeNode(Integer.parseInt(strings[idx]));
                queue.add(cur.left);
            }
            idx++;
            if(idx<strings.length && !strings[idx].equals("null")) {
                cur.right = new TreeNode(Integer.parseInt(strings[idx]));
                queue.add(cur.right);
            }
            idx++;
        }
        return root;
    }

    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        _449_Serialize_and_Deserialize_BST test = new _449_Serialize_and_Deserialize_BST();
        System.out.println(test.serializeBT(test.deserializeBT(test.serializeBT(root))));

    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
}
