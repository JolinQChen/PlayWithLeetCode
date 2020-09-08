package _AAInterviews.Wish;
/**
 * 设计一个买/卖一个商品的class，提供两个功能，buy(price, quantity)和sell(price, quantity)，意思就是有人出多少钱买多少个这个商
 * 品，或者有人标价多少钱卖这个商品，返回值是买到或者卖出的数量，例子：
 * sell(10, 20) 返回0，暂时没人买，把这个数据存着
 * buy(5, 20) 返回0，没有人用<=5的价格卖这个商品，把这个订单也存起来
 * sell(4, 25) 返回20，上一行那个买家就买到了20个，然后剩5个存起来
 * buy(12, 30) 返回25，第一行的20个被买了，还有上一行剩的5个也被买了，然后还差5个的订单，存起来
 * 另外，如果有多个卖家用不同的价格卖同一个商品，并且价格都低于当前buy的价格，先买哪个无所谓。反之亦然
 *
 * */


import java.util.*;

/**
 *  概要
 * 用两个treemap 分别存买方(buyMap)和卖方(sellMap)目前剩余的订单，key是价格，value是存货量。
 * 每次buy交易，在sellMap中搜索出价不高于本次买方出价的存货，从价格低的搜起，如果某一价位的商品被买完，及时清空对应的map entry。
 * 最后如果本次购买的量还有剩余（价格不高于出价的商品都买完了），剩余的加入对应的buyMap.
 *
 * 对于sell交易，只需要改为从buyMap中搜索价格不低于本次出价的存货，最后更新对应的sellMap就可以
 *
 * 复杂度：假设有n条交易记录， 用来存放map的空间是O(n), 每次交易都需要遍历一遍当前map, 时间上是O(n^2) 的
 * */
public class BuyerSeller {
    private Map<Integer, Integer> sellMap;
    private Map<Integer, Integer> buyMap;


    public BuyerSeller () {
        sellMap = new TreeMap<>();
        buyMap = new TreeMap<>();
    }

    public int sell(int price, int quantity) {
        int residual = quantity;
        if(buyMap.containsKey(price)) {
            int tmp = buyMap.get(price);
            if(tmp>quantity) {
                buyMap.put(price, tmp-quantity);
                return quantity;
            }
            else {
                residual -= tmp;
                buyMap.remove(price);
                if(residual==0) {
                    return quantity;
                }
            }
        }

        List<Integer> prices = new ArrayList<>();// store prices key entries to be removed
        List<Integer> quantities = new ArrayList<>(); // store corresponding values

        for(int curr_price:buyMap.keySet()) {
            if(curr_price >= price) {
                int tmp = buyMap.get(curr_price);
                if(tmp>=residual) {
                    prices.add(curr_price);
                    quantities.add(tmp - residual);
                    residual = 0;
                    break;
                }
                else {
                    residual -= tmp;
                    prices.add(curr_price);
                    quantities.add(0);
                }
            }
        }

        for(int i=0; i<prices.size(); i++) {
            if(quantities.get(i)>0) buyMap.put(prices.get(i), quantities.get(i));
            else buyMap.remove(prices.get(i));
        }

        if(residual > 0){
            if(sellMap.containsKey(price)) sellMap.put(price,sellMap.get(price)+residual);
            else sellMap.put(price,residual);
        }
        return quantity-residual;
    }
    public int buy(int price, int quantity) {
        int res = quantity; //residual quantity not bought

        if (sellMap.containsKey(price)) {
            int sq = sellMap.get(price);
            if (sq > quantity) {
                sellMap.put(price, sq - quantity);
                return quantity;
            } else {
                res = res - sq;
                sellMap.remove(price);
            }
        }

        List<Integer> keys = new ArrayList<>(); //store price entry to be removed
        List<Integer> values = new ArrayList<>(); //store coresponding values

        for (int p: sellMap.keySet()) {
            if (p <= price) {
                int q = sellMap.get(p);
                if (q > res) {
                    keys.add(p);
                    values.add(q - res);
                    res = 0;
                    break;
                } else {
                    res = res - q;
                    keys.add(p);
                    values.add(0);
                }
            } else {
                break;
            }
        }

        for (int i = 0; i < keys.size(); i++) {
            if (values.get(i) > 0) sellMap.put(keys.get(i), values.get(i));
            else sellMap.remove(keys.get(i));
        }

        if (res > 0) {
            if (buyMap.containsKey(price)) {
                int cur_q = buyMap.get(price);
                buyMap.put(price, res + cur_q);
            } else {
                buyMap.put(price, res);
            }

        }
        return quantity - res;
    }
}
