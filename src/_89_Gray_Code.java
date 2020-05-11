import java.util.*;
public class _89_Gray_Code {
    //格雷码, 本质是动态规划
    // 镜像法
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>() {{ add(0); }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--)
                res.add(head + res.get(j));
            head *= 2;
        }
        return res;
    }

}
