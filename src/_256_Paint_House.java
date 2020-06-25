public class _256_Paint_House {
    public int minCost(int[][] costs) {
        int houseNum = costs.length;
        if(houseNum==0) return 0;
        for(int i=1; i<houseNum; i++){
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        return Math.min(Math.min(costs[houseNum-1][0],costs[houseNum-1][1]),costs[houseNum-1][2]);
    }

}
