import javax.naming.InsufficientResourcesException;
import java.util.*;
public class TestSolution {
    static class Pair{
        public TreeNode node;
        public int index;
        public int level;
        public Pair(TreeNode node, int index, int level){
            this.node = node;
            this.index = index;
            this.level = level;
        }
    }
    public static void main(String[] args){
        PriorityQueue<Pair> pq = new PriorityQueue<>(4,
                new Comparator<Pair>() {
                    @Override
                    public int compare(Pair o1, Pair o2) {
                        System.out.println("Comparing "+ o1.node.val + "and "+ o2.node.val + ", o1.level: " + o1.level + " o2.level: "+ o2.level);
                        if(o1.level - o2.level!=0) {


                            return o1.level - o2.level;
                        }
                        else{
                            System.out.println("Compare val");
                            return o1.node.val - o2.node.val;
                        }

                    }
                }
        );
        pq.add(new Pair(new TreeNode(0),1, 1));
        pq.add(new Pair(new TreeNode(2),1, 0));
        pq.add(new Pair(new TreeNode(-1),1, 1));
        //for(Pair pair:pq) System.out.println(pair.node.val);



        PriorityQueue<Integer> pq2 = new PriorityQueue<>(2,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1-o2;
                    }
                }
        );
        pq2.add(1);
        pq2.add(-1);
        pq2.add(0);
        for(int i:pq2) System.out.println(i);
    }
}
