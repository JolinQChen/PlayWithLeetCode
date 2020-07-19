package _All;

import java.util.*;
/**坑爹*/
public class _417_Pacific_Atlantic_Water_Flow {
    private Set<Integer> pacificSet;
    private Set<Integer> atlanticSet;
    private int R;
    private int C;
    private boolean[] pacificVisit;
    private boolean[] atlanticVisit;
    private Set<Integer> common;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        R = matrix.length;
        C = matrix[0].length;
        common = new HashSet<>();
        pacificSet = new HashSet<>();
        atlanticSet = new HashSet<>();
        pacificVisit = new boolean[R*C];
        atlanticVisit = new boolean[R*C];

        List<List<Integer>> res = new ArrayList<>();
        pacificSet.add(0);
        atlanticSet.add(R*C-1);
        searchPacific(matrix,0);
        searchAtlantic(matrix,R*C-1);
        for(int pos:common){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(pos/C);
            tmp.add(pos%C);
            res.add(tmp);
        }
        System.out.println("Atlantic");
        for(int i:atlanticSet) System.out.println(i/C + " , " + i%C);
        System.out.println("Pacific");
        for(int i:pacificSet) System.out.println(i/C + " , " + i%C);
        return res;
    }

    private void searchPacific(int[][] matrix, int pos){
        int r = pos/C;
        int c = pos%C;

        if(pacificSet.contains(pos)){

            if(c+1<C){
                if(!pacificSet.contains(r*C+c+1) && (matrix[r][c+1]>=matrix[r][c] || r==0)) {
                    pacificSet.add(r*C+c+1);
                    searchPacific(matrix,r*C+c+1);
                }

            }
            if(c-1>=0){
                if(!pacificSet.contains(r*C+c-1) && (matrix[r][c-1]>=matrix[r][c] || r==0)) {
                    pacificSet.add(r*C+c-1);
                    searchPacific(matrix,r*C+c-1);
                }

            }
            if(r+1<R){
                if(!pacificSet.contains((r+1)*C+c) && (matrix[r+1][c]>=matrix[r][c] || c==0)){
                    pacificSet.add((r+1)*C+c);
                    searchPacific(matrix,(r+1)*C+c);
                }
            }
            if(r-1>=0){
                if(!pacificSet.contains((r-1)*C+c) && (matrix[r-1][c]>=matrix[r][c] || c==0)){
                    pacificSet.add((r-1)*C+c);
                    searchPacific(matrix,(r-1)*C+c);
                }
            }
        }
    }

    private void searchAtlantic(int[][] matrix, int pos){

        int r = pos/C;
        int c = pos%C;

        if(atlanticSet.contains(pos)){
            if(pacificSet.contains(pos)) {
                common.add(pos);
            }
            //查找右、下是否有高于本座的
            if(c-1>=0){
                if(!atlanticSet.contains(r*C+c-1) && (matrix[r][c-1]>=matrix[r][c] || r==R-1)) {
                    atlanticSet.add(r*C+c-1);
                    searchAtlantic(matrix,r*C+c-1);
                }
            }
            if(c+1<C){
                if(!atlanticSet.contains(r*C+c+1) && (matrix[r][c+1]>=matrix[r][c] || r==R-1)) {
                    atlanticSet.add(r*C+c+1);
                    searchAtlantic(matrix,r*C+c+1);
                }
            }
            if(r-1>=0){
                if(!atlanticSet.contains((r-1)*C+c) && (matrix[r-1][c]>=matrix[r][c] || c==C-1)){
                    atlanticSet.add((r-1)*C+c);
                    searchAtlantic(matrix,(r-1)*C+c);
                }

            }
            if(r+1<R){
                if(!atlanticSet.contains((r+1)*C+c) && (matrix[r+1][c]>=matrix[r][c] || c==C-1)){
                    atlanticSet.add((r+1)*C+c);
                    searchAtlantic(matrix,(r+1)*C+c);
                }

            }
        }

    }
}
