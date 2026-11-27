class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            int count = 0;

            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    count++;
                }
            }

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + count);
            }
        }

        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            int extra = (budget - b) / minPrice;
            ans = Math.max(ans, dp[b] + extra);
        }

        return ans;
    }
}