import java.util.Arrays;

public class BestTimeBuyAndSellStock {
    //--------------  Solution 1 -------------------//
    // brute force (Time Limit Exceeded)
    public int maxProfit(int[] prices) {
        int N = prices.length;
        int res = 0;  // min, buy and sell on the same day
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                res = Math.max(res, prices[j] - prices[i]);
            }
        }
        return res;
    }

    //--------------  Solution 2  -------------------//
    // most intuitive solution
    public int maxProfit2(int[] prices) {
        // input validation
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // record the min up util now
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i : prices) {
            min = Math.min(min, i);
            res = Math.max(res, i - min);
        }
        return res;
    }

    //--------------  Solution 3  -----------------//
    // == maximal sub-array problem (diff)
    public int maxProfit3(int[] prices) {
        // input validation
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // calculate the result
        int sum = 0;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            int diff = prices[i + 1] - prices[i];  // get the diff
            sum = Math.max(0, sum + diff);  // local
            res = Math.max(res, sum);       // global
        }
        return res;
    }


    ////////////////////  TEST ////////////////////
    private static void test(BestTimeBuyAndSellStock m, int[] A) {
        System.out.println(Arrays.toString(A));
        System.out.println(m.maxProfit(A) + "\n");
    }
    public static void main(String[] args) {
        BestTimeBuyAndSellStock solution = new BestTimeBuyAndSellStock();
        int[] prices1 = {1, 2, 5, 3, 4, 6, 2, 8};
        int[] prices2 = {7, 1, 5, 3, 6, 4};
        test(solution, prices1);
        test(solution, prices2);
    }
}
