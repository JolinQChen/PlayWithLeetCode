package _All;

import java.util.*;
/**
 *
 * */

public class _241_Different_Ways_to_Add_Parentheses {
    //法一：递归
    /*
    * 可以通过运算符把整个式子分成两部分，两部分再利用递归解决。以 2 * 3 - 4 * 5 为例。
    *
    * 2 和 3 - 4 * 5 两部分，中间是 * 号相连。
    * 2 * 3 和 4 * 5 两部分，中间是 - 号相连。
    * 2 * 3 - 4 和 5 两部分，中间是 * 号相连。
    *
    * 有了两部分的结果，然后再通过中间的符号两两计算加入到最终的结果中即可。
    * */
    //List<Integer> res = new ArrayList<>();
    Set<Character> operator = new HashSet<>();
    public List<Integer> diffWaysToCompute_rec(String input) {
        List<Integer> res = new ArrayList<>();
        if (input.length() == 0) {
            return res;
        }
        operator.add('*');
        operator.add('+');
        operator.add('-');
        int len = input.length();
        int num = 0;
        //考虑是全数字的情况
        int index = 0;
        while (index < len && !operator.contains(input.charAt(index))) {
            num = num * 10 + input.charAt(index) - '0';
            index++;
        }
        //全为数字，没有运算符，直接返回
        if(index == len) {
            res.add(num);
            return res;
        }
        //通过运算符将string分为两部分
        for (int i = index; i < input.length(); i++) {
            //通过运算符将字符串分成两部分
            if (operator.contains(input.charAt(i))) {
                List<Integer> result1 = diffWaysToCompute_rec(input.substring(0, i));
                List<Integer> result2 = diffWaysToCompute_rec(input.substring(i + 1));
                //将两个结果依次运算
                for (int j = 0; j < result1.size(); j++) {
                    for (int k = 0; k < result2.size(); k++) {
                        char op = input.charAt(i);
                        res.add(calculate(result1.get(j), op, result2.get(k)));
                    }
                }
            }
        }
        return res;
    }

    private int calculate(int a, char o, int b){
        //int res = 0;
        if(o=='-') return a-b;
        if(o=='*') return a*b;
        return a+b;
    }

    // 优化方法可以用memo记录已经计算过的值

    // 法2：DP

    public List<Integer> diffWaysToCompute_dp(String input) {
        operator.add('*');
        operator.add('+');
        operator.add('-');
        int len = input.length();
        List<Integer> res = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        if(len==0) return res;
        /**
         * 先做一个预处理，把每个数字提前转为 int 然后存起来，同时把运算符也都存起来。
         * 这样的话我们就有了两个 list，一个保存了所有数字，一个保存了所有运算符。
         * */
        int index = 0;
        int num = 0;
        while(index<len){
            if(operator.contains(input.charAt(index))){
                nums.add(num);
                num = 0;
                operators.add(input.charAt(index));
            }
            else {
                num = num*10+(input.charAt(index)-'0');
            }
            index++;
        }
        nums.add(num);
        int N = nums.size();
        /**dp[i*N+j] 含义是第 i 到第 j 个数字（从 0 开始计数）范围内的表达式的所有解。*/
        List<Integer>[][] dp = new ArrayList[N][N];

        /**一个数字*/
        for(int i=0; i<N; i++){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums.get(i));
            dp[i][i] = tmp;
        }
        /**2个数字到N个数字*/
        for(int n=2; n<=N; n++){
            // n是长度
            for(int i=0; i<N; i++){
                // i是开始下标
                int j = i+n-1; //j是结束下标
                if(j>=N) break;
                List<Integer> result = new ArrayList<>();
                // 分成 i ~ s 和 s+1 ~ j 两部分
                for (int s = i; s < j; s++) {
                    List<Integer> result1 = dp[i][s];
                    List<Integer> result2 = dp[s+1][j];
                    for (int x = 0; x < result1.size(); x++) {
                        for (int y = 0; y < result2.size(); y++) {
                            // 第 s 个数字下标对应是第 s 个运算符
                            char op = operators.get(s);
                            result.add(calculate(result1.get(x), op, result2.get(y)));
                        }
                    }
                }
                dp[i][j] = result;
            }
        }
        return dp[0][N-1];
    }


}
