public class _50_Pow_x_n {
    //快速幂，递归,辅助
    private double powHelper(double x, long n){
        // n>=0
        if(n == 0) return 1.0;
        double half = powHelper(x,n/2);
        if(n%2 == 1) return half * half * x;
        return half * half;
    }

    public double myPow(double x, int n) {
        long N = (long) n;
        if(N<0){
            N = -N;
            x = 1/x;
        }

        return powHelper(x,N);



    }
    public static void main(String[] args){
        _50_Pow_x_n res = new _50_Pow_x_n();
        double ress = res.myPow((double) 2, 10);
        System.out.println(ress);
    }
}
