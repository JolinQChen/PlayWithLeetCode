package _All;

public class _309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    // You may not engage in multiple transactions at the same time (ie, you must sell the stock
    // before you buy again).
    //After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
    /**
     *
     * 我们用 f[i] 表示第 i 天结束之后的「累计最大收益」。根据题目描述，由于我们最多只能同时买入（持有）一支股票，
     * 并且卖出股票后有冷冻期的限制，因此我们会有三种不同的状态：
     *
     * 我们目前持有一支股票，对应的「累计最大收益」记为 f[i][0]；
     *
     * 我们目前不持有任何股票，并且处于冷冻期中，对应的「累计最大收益」记为 f[i][1]；
     *
     * 我们目前不持有任何股票，并且不处于冷冻期中，对应的「累计最大收益」记为 f[i][2]。
     *
     * 这里的「处于冷冻期」指的是在第 i 天结束之后的状态。也就是说：如果第 ii 天结束之后处于冷冻期，那么第 i+1 天无法买入股票。
     *
     * 如何进行状态转移呢？在第 i 天时，我们可以在不违反规则的前提下进行「买入」或者「卖出」操作，此时第 ii 天的状态会从
     * 第 i−1 天的状态转移而来；我们也可以不进行任何操作，此时第 i 天的状态就等同于第 i−1 天的状态。那么我们分别对这三
     * 种状态进行分析：
     *
     *
     *
     * */
    public int maxProfit(int[] prices) {

        int sold = Integer.MIN_VALUE, held = Integer.MIN_VALUE, reset = 0;

        for (int price : prices) {
            int preSold = sold;
            sold = held + price; // 手上不持有股票，并且处于冷冻期中的累计最大收益
            held = Math.max(held, reset - price); // 手上持有股票的最大收益
            reset = Math.max(reset, preSold); // 手上不持有股票，并且不处于冷冻期中的累计最大收益
        }

        return Math.max(sold, reset);
    }

    public static void main(String[] args) {
        _309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown test = new _309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown();
        int[] prices = {1,2,3,0,2};
        System.out.println(test.maxProfit(prices));
        //transactions = [buy, sell, cooldown, buy, sell]

    }
}
