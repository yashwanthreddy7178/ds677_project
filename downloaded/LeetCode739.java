package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/10/19 23:46
 *
 * 739.每日温度（中等）
 *
 *  请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

 */
public class LeetCode739 {

    public int[] dailyTemperatures(int[] T){
        if (T==null) return null;
        int len = T.length;
        int[] ans = new int[len];
        //存放元素索引，而不是元素
        Stack<Integer> stack = new Stack<>();

        for (int i=len-1; i>=0; i--){
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]){
                stack.pop();
            }
            //得到索引间距
            ans[i] = stack.empty() ? 0 : (stack.peek()-i);
            //将索引入栈而不是元素
            stack.push(i);
        }
        return ans;
    }

    public int[] dailyTemperatures2(int[] T){
        int len = T.length;
        int[] ans = new int[len];
        //存放元素索引，而不是元素
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++){
            while (!stack.isEmpty() && T[stack.peek()] < T[i]){
                Integer t = stack.pop();
                ans[t] = i - t;
            }
            stack.push(i);
        }
        return ans;
    }
}
