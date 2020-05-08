public class _1232_CheckStraightLine {
    /* 给几个点，判断在不在一条直线上 */
    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;
        if(len<=2) return true;
        int base_x = coordinates[0][0];
        int base_y = coordinates[0][1];
        int delta_x = coordinates[1][0] - base_x;
        int delta_y = coordinates[1][1] - base_y;
        if(delta_x == 0){
            for(int i=2; i<len; i++){
                int tmp_delta_x = coordinates[i][0]-base_x;
                if(tmp_delta_x!=0) return false;
            }
            return true;
        }
        else if(delta_y == 0){
            for(int i=2; i<len; i++){
                int tmp_delta_y = coordinates[i][1]-base_y;
                if(tmp_delta_y!=0) return false;
            }
            return true;
        }
        else {
            for(int i=2; i<len; i++){
                int tmp_delta_x = coordinates[i][0]-base_x;
                int tmp_delta_y = coordinates[i][1]-base_y;
                if(tmp_delta_x * delta_y != tmp_delta_y * delta_x) return false;
            }
            return true;
        }

    }
}
