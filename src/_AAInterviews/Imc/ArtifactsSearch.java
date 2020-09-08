package _AAInterviews.Imc;

public class ArtifactsSearch {
    public int[] solution(int N, String artifacts, String searched) {
        int countComplete = 0;
        int countInComplete = 0;
        int[][] graph = new int[N][N];
        String[] searchedCells = searched.split(" ");
        for(String search:searchedCells) {
            int r = Integer.parseInt(search.substring(0,search.length()-1))-1;
            int c = (int) (search.charAt(search.length()-1)-'A');
            graph[r][c] = 2; // searched cell, marked as 2
        }

        String[] buries = artifacts.split(",");
        for(String bury : buries) {
            String[] cells = bury.split(" ");
            int r1 = Integer.parseInt(cells[0].substring(0,cells[0].length()-1))-1;
            int c1 = (int) (cells[0].charAt(cells[0].length()-1)-'A');

            int r2 = Integer.parseInt(cells[1].substring(0,cells[1].length()-1))-1;
            int c2 = (int) (cells[1].charAt(cells[1].length()-1)-'A');

            int count = (c2-c1+1) * (r2-r1);
            int find = 0;
            for(int r=r1; r<=r2; r++) {
                for(int c=c1; c<=c2; c++) {
                    if(graph[r][c]==2) find++;
                }
            }
            if(find==count) countComplete++;
            else countInComplete++;
        }
        return new int[]{countComplete,countInComplete};
    }
}
