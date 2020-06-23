import java.util.*;
public class _935_Knight_Dialer {
    //构建graph
    private final long mod = 1000000007L;
    private Map<Integer, List<Integer>> map = new HashMap<>();
    private long count;
    public int knightDialer(int N) {
        //初始化
        long[] countLast = new long[10];
        Arrays.fill(countLast,1);
        count = 0;
        map.put(0,Arrays.asList(4,6));
        map.put(1,Arrays.asList(6,8));
        map.put(2,Arrays.asList(7,9));
        map.put(3,Arrays.asList(4,8));
        map.put(4,Arrays.asList(3,9,0));
        map.put(6,Arrays.asList(1,7,0));
        map.put(7,Arrays.asList(6,2));
        map.put(8,Arrays.asList(1,3));
        map.put(9,Arrays.asList(2,4));
        map.put(5,new ArrayList<>());
        map.put(-1, Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        while(N>1){
            long[] tmpLast = new long[10];
            for(int i=0; i<10; i++){
                if(countLast[i]>0){
                    for(int next:map.get(i)){
                        tmpLast[next] += countLast[i];
                        tmpLast[next] %= mod;
                    }
                }
            }
            countLast = tmpLast;

            N--;
            System.out.println("N: "+ N);
            for(long j:countLast) System.out.print(j+"   ");
            System.out.println();
        }
        for(long i:countLast) {

            count += i;
        }


//        helper(-1,N);
        //return (int)(count%(Math.pow(10,9)+7));
        return (int) (count%mod);

    }
    private void helper( int entry, int remain){
        if(remain==0 || (entry==5 && remain==0)) {
            count++;
            return;
        }
        if(entry==5 && remain>1) return;

        for(int next:map.get(entry)){
            helper(next,remain-1);
        }
    }






    public static void main(String[] args){
        _935_Knight_Dialer test = new _935_Knight_Dialer();
        System.out.println(test.knightDialer(100));
    }
}
