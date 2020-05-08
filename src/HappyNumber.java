import java.util.*;
public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> checkLoop = new HashSet<>();
        checkLoop.add(n);
        int res = 0;
        while(!checkLoop.contains(res)){
            checkLoop.add(res);
            res = breakSum(n);
            if(res == 1) return true;
            n = res;
            System.out.println(res);
        }
        return false;
    }
    public int breakSum(int n){
        int res = 0;
        while(n!=0){
            int tmp = n%10;
            n /= 10;
            res += tmp*tmp;
        }
        return res;
    }
    public static void main(String[] args){
        HappyNumber hn = new HappyNumber();
        boolean res = hn.isHappy(19);
        System.out.println("this is: " + String.valueOf(res));
    }
}
