package _AAInterviews.Citrix;
import java.util.*;

/**
 *
 * */


public class AngryAnimals {
    public static long angryAnimals(int n, int[] a, int[] b){
        List<PriorityQueue<Integer>> e = new ArrayList<>();
        for(int i=0; i<=n; i++){
            e.add(new PriorityQueue<>(
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2-o1;
                        }
                    }
            ));
        }
        for (int i=0; i<a.length; i++){
            if(a[i]<b[i]){
                e.get(b[i]).offer(a[i]);
            }
            else e.get(a[i]).offer(b[i]);
        }
        for(int i:e.get(3)) System.out.println("i "+i);
        long count = 0;
        Deque<Integer> queue = new LinkedList<>();
        for(int i=1; i<=n; i++){
            if(e.get(i).isEmpty()){
                count++;
                queue.offer(i);
                System.out.println(count);
                System.out.println("on i = "+i+" queue size is "+queue.size());
            }
            else{
                while (!queue.isEmpty()&& !e.get(i).isEmpty() && queue.getFirst() <=e.get(i).peek() && queue.getLast() >=e.get(i).peek()){
                    if(queue.getFirst() >= e.get(i).peek()) {

                        e.get(i).poll();

                    }
                    queue.poll();

                    count+=queue.size();
                    System.out.println(count + " now q: "+queue.size());
                }
                queue.offer(i);

                count++;
            }
        }
        int size = queue.size();
        System.out.println("queue size "+queue.size());

        //number of ways for remaining elements

        count+= size*(size-1)/2;

        return count;
    }

    public static void main(String[] args){
        int[] a = {1,2,3};
        int[] b = {3,3,1};
        System.out.println( angryAnimals(3,a,b));

    }
}
