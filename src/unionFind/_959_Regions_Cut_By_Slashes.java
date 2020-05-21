package unionFind;

/**
 * In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.
 * These characters divide the square into contiguous regions.
 *
 * (Note that backslash characters are escaped, so a \ is represented as "\\".)
 *
 * Return the number of regions.
 *
 * 翻译：
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 *
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 *
 * 返回区域的数目。
 *
 * Example 1:
 *
 * Input:
 * [
 *   " /",
 *   "/ "
 * ]
 * Output: 2
 * */


/**
 * 解法思路：
 * 创建 4*N*N 个顶点，每个代表一个三角形，按照如上的方式连接它们(预先把一个格子分为四个三角形)。然后，使用并查集算法找到连通块的总数。
 * */
public class _959_Regions_Cut_By_Slashes {
    private int[] parent;
    private int[] rank;
    private int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];
    }
    private void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        int xRootRank = rank[xRoot];
        int yRootRank = rank[yRoot];
        if(xRootRank < yRootRank) parent[xRoot] = yRoot;
        else if(xRootRank > yRootRank) parent[yRoot] = xRoot;
        else {
            parent[xRoot] = yRoot;
            rank[yRoot]++;
        }
    }

    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        parent = new int[N*N*4];
        for(int i=0; i<N*N*4; i++) parent[i]=i;
        rank = new int[N*N*4];
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                int root = 4 * (r*N+c);
                char cur = grid[r].charAt(c);
                if(cur != '\\'){
                    union(root,root+3);
                    union(root+1, root+2);
                }
                if(cur !='/'){
                    union(root, root+1);
                    union(root+2, root+3);
                }
                //上下连通
                if(r<N-1) {
                    union(root+2,4 * ((r+1)*N+c)); //下
                    System.out.println("下");
                }
                if(r>0) {
                    union(root, 4 * ((r-1)*N+c)+2); //上
                    System.out.println("上");
                }
                //左右连通
                if(c>0) {
                    //左
                    union(root+3, 4 * (r*N+c-1)+1);
                    System.out.println("左");
                }
                if(c<N-1) {
                    union(root+1, 4 * (r*N+c+1)+3);//右
                    System.out.println("右");
                }
            }
        }
        int res = 0;
        //找root数量
        for(int i=0; i<N*N*4; i++){
            if(find(i) == i) res++;
        }
        return res;
    }
    public static void main(String[] args){
        _959_Regions_Cut_By_Slashes test = new _959_Regions_Cut_By_Slashes();
        String[] grid = {" /","/ "};
        System.out.println(test.regionsBySlashes(grid));
    }
}
