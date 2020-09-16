package _AAInterviews.Roblox;

public class ListMax {
    private static int list_max_brutal(int[][] operations, int m){
        // 暴力法
        int[] update = new int[m];
        int max = 0;
        int len_opt = operations.length;
        for(int i=0; i<len_opt; i++){
            int a = operations[i][0];
            int b = operations[i][1];
            int k = operations[i][2];
            for(int j=a-1;j<b;j++){
                update[j] += k;
                max = Math.max(update[j], max);
            }
        }
        return max;
    }

    public static int list_max_new(int[][] bookings, int n) {
        int m = bookings.length;
        int[] update = new int[n+1];
        for(int i=0; i<m; i++){
            int a = bookings[i][0];
            int b = bookings[i][1];
            int k = bookings[i][2];
            update[a-1] += k;
            update[b] -= k;
        }
        int max = 0;
        int sum = 0;
        for(int i=0; i<n+1; i++){
            sum += update[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args){
        int[][] operations = {{1,2,10},{2,4,5},{3,5,12}};
        int res = list_max_new(operations, 5);
        System.out.println(res);
    }
}

